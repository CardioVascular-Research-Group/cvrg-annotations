package edu.jhu.cvrg.services.broker.annotations.processors;
/*
Copyright 2014 Johns Hopkins University Institute for Computational Medicine

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
/**
* @author Chris Jurado, Brandon Benitez, David Hopkins
* 
*/
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.cvrgrid.schiller.jaxb.beans.Channel;
import org.cvrgrid.schiller.jaxb.beans.ComXiriuzSemaXmlSchillerEDISchillerEDI;
import org.cvrgrid.schiller.jaxb.beans.Event;
import org.cvrgrid.schiller.jaxb.beans.Examdescript;
import org.cvrgrid.schiller.jaxb.beans.Patdata;
import org.cvrgrid.schiller.jaxb.beans.Wavedata;

import edu.jhu.cvrg.dbapi.dto.AnnotationDTO;
import edu.jhu.cvrg.services.broker.annotations.extractors.SchillerAnnotationsExtractor;
import edu.jhu.cvrg.services.broker.annotations.utilities.AnnotationMaps;

public class SchillerAnnotationsProcessor extends AnnotationsProcessor {
	
	private Logger log = Logger.getLogger(SchillerAnnotationsProcessor.class);
	
	private ComXiriuzSemaXmlSchillerEDISchillerEDI schillerEDI;
	private SchillerAnnotationsExtractor schillerAnnotationsExtractor = new SchillerAnnotationsExtractor();
	private ArrayList<Annotation> examdescriptList;
	private ArrayList<Annotation> patdataList;

	public SchillerAnnotationsProcessor(){
		
	}

	@Override
	public void populateAnnotations() {
		log.info("Entering populateAnnotations");
		
		this.extractExamdescript();
		this.extractPatdata();
		this.processLeadAnnotations();
		this.processGlobalAnnotations();

	}

	@Override
	public void processLeadAnnotations() {
		Event allLeadAnnotations = schillerEDI.getEventdata().getEvent();
		
		if(allLeadAnnotations != null) {
			List<Wavedata> leadAnnotationGroup = allLeadAnnotations.getWavedata();
			
			// iterate through multiple wavedata elements within file
			for(Wavedata annotation: leadAnnotationGroup) {

				if (annotation.getType().equalsIgnoreCase("ecg_averages")){	
					List<Channel> channel = annotation.getChannel();
		    		Integer leadIndex = null;
		    		
		    		//iterate through multiple channels within wavedata element
			    	for (Channel subChannel : channel) {
						String leadValue = subChannel.getName();
						
						leadIndex = setLeadIndex(leadValue);
						
						String annType = "ANNOTATION";
						
			    		LinkedHashMap<String, Object> leadMappings = schillerAnnotationsExtractor.extractLeadMeasurements(subChannel);
			    		
			    		ArrayList<Annotation> annotationsToAdd = new ArrayList<Annotation>();

						for(String key : leadMappings.keySet()) {

							String conceptId = "";
							String fullAnnotation = "";
							String prefLabel = "";
							
							Annotation annData = new Annotation(Long.valueOf(userID), 0L, 0L, docID, createdBy, annType, prefLabel, 
									 conceptId != null ? Annotation.ECG_TERMS_ONTOLOGY : null , conceptId,
									 null, leadIndex, null, null, fullAnnotation , Calendar.getInstance(), 
									 null, null, null, null, studyID, recordName, subjectID);
							
							if (AnnotationMaps.getEcgOntologyMap().containsKey(key)){
								conceptId = "http://www.cvrgrid.org/files/ECGOntologyv1.owl" + AnnotationMaps.getEcgOntologyMap().get(key);
							}
							
							annData.setNewStudyID(studyID);
							annData.setNewSubjectID(subjectID);
							annData.setUserID(Long.valueOf(userID));
							annData.setRecordID(docID);
							annData.setNewRecordName(recordName);
							annData.setValue(leadMappings.get(key).toString());
							annData.setName(key);
							annData.setCreatedBy(createdBy);
							annData.setBioportalClassId(conceptId);
							annData.setBioportalReferenceLink("%" + leadValue + "%");  // Just did this to drop lead in database for testing - remove
							annData.setTimestamp(new GregorianCalendar());
							annotationsToAdd.add(annData);
						}
						leadAnnotationsList.put(Integer.toString(leadIndex), annotationsToAdd);
			    	}
				}
			}			
		}
	}

	@Override
	public void processGlobalAnnotations() {
		Event globalAnnotations = schillerEDI.getEventdata().getEvent();
		if(globalAnnotations != null) {
			LinkedHashMap<String, Object> annotationMappings = schillerAnnotationsExtractor.extractCrossleadElements(globalAnnotations);
			String annType = "Global Annotation";

			// iterate through multiple annotation_global elements within file
    		for(String key : annotationMappings.keySet()) {
				if((annotationMappings.get(key) != null)) {
					
					String conceptId = "";
					
					if (AnnotationMaps.getEcgOntologyMap().containsKey(key)){
						conceptId = "http://www.cvrgrid.org/files/ECGOntologyv1.owl" + AnnotationMaps.getEcgOntologyMap().get(key);
					}
					
					Annotation annData = new Annotation();
					annData.setNewStudyID(studyID);
					annData.setNewSubjectID(subjectID);
					annData.setUserID(Long.valueOf(userID));
					annData.setRecordID(docID);
					annData.setNewRecordName(recordName);
					annData.setValue(annotationMappings.get(key).toString());
					annData.setName(key);
					annData.setCreatedBy(createdBy);
					annData.setBioportalClassId(conceptId);
					annData.setAnnotationType(annType);
					annData.setTimestamp(new GregorianCalendar());
					
					crossleadAnnotationsList.add(annData);
				}	
			}	
		}
	}
	
	private void extractExamdescript() {
		Examdescript examdescriptAnn = schillerEDI.getExamdescript();
		
		if(examdescriptAnn != null) {
			LinkedHashMap<String, Object> orderMappings = schillerAnnotationsExtractor.extractExamdescript(examdescriptAnn);
			
			String annType = "COMMENT";
    		log.debug("Size of hashmap = " + orderMappings.size());
			
			for(String key : orderMappings.keySet()) {
				if((orderMappings.get(key) != null)) {
					
					Annotation annData = new Annotation();
					annData.setNewStudyID(studyID);
					annData.setNewSubjectID(subjectID);
					annData.setUserID(Long.valueOf(userID));
					annData.setRecordID(docID);
					annData.setNewRecordName(recordName);
					annData.setValue(orderMappings.get(key).toString());
					annData.setName(key);
					annData.setCreatedBy(createdBy);
					annData.setAnnotationType(annType);
					annData.setTimestamp(new GregorianCalendar());
					
					examdescriptList.add(annData);
				}
			}
		}
	}
	
	private void extractPatdata() {
		Patdata patdataAnn = schillerEDI.getPatdata();
		
		if(patdataAnn != null) {
			LinkedHashMap<String, Object> orderMappings = schillerAnnotationsExtractor.extractPatdata(patdataAnn);
			String annType = "COMMENT";
    		log.debug("Size of hashmap = " + orderMappings.size());
			
			for(String key : orderMappings.keySet()) {
				if((orderMappings.get(key) != null)) {
					Annotation annData = new Annotation();
					annData.setNewStudyID(studyID);
					annData.setNewSubjectID(subjectID);
					annData.setUserID(Long.valueOf(userID));
					annData.setRecordID(docID);
					annData.setNewRecordName(recordName);
					annData.setValue(orderMappings.get(key).toString());
					annData.setName(key);
					annData.setCreatedBy(createdBy);
					annData.setAnnotationType(annType);
					annData.setTimestamp(new GregorianCalendar());
					
					patdataList.add(annData);
				}
			}
		}
	}

	public static int setLeadIndex(String test) {

		int i = 0;
		if (AnnotationMaps.getDynamicLeadNames().containsKey(test)) {
			i = AnnotationMaps.getDynamicLeadNames().get(test);
			return i;
		} else {
			int j = AnnotationMaps.getDynamicLeadNames().size();
			AnnotationMaps.getDynamicLeadNames().put(test, j);
			i = j;
			/* pseudo code continuing process for new Lead index setter...
			 * 
			 * 	dbutility.getLeadListDTO
			 * 		is key in the database?
			 *			y->  1. get index #
			 *     		     2. set i to index #
			 * 			n->  1. insert l.name ... into database
			 *      	     2. get index # for new entry from database
			 *      	     3. set i to index #
			 *      		 4.   ...  
			 *      		 5. profit!
			 *      
			 *  */
		}
		return i;
	}

}
