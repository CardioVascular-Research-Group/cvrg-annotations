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
* @author Chris Jurado, Brandon Benitez, Michael Shipway
* 
*/
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.sierraecg.schema.Dataacquisition;
import org.sierraecg.schema.Globalmeasurements;
import org.sierraecg.schema.Groupmeasurement;
import org.sierraecg.schema.Groupmeasurements;
import org.sierraecg.schema.Leadmeasurement;
import org.sierraecg.schema.Leadmeasurements;
import org.sierraecg.schema.Orderinfo;
import org.sierraecg.schema.Restingecgdata;

import edu.jhu.cvrg.annotations.model.Annotation;
import edu.jhu.cvrg.annotations.model.GlobalAnnotation;
import edu.jhu.cvrg.annotations.model.LeadAnnotation;
import edu.jhu.cvrg.services.broker.annotations.extractors.Philips103AnnotationsExtractor;
import edu.jhu.cvrg.services.broker.annotations.utilities.interfaces.OrderInfo;
import edu.jhu.cvrg.services.broker.annotations.wrapper.philips.Philips103RestingECGData;

public class Philips103AnnotationsProcessor extends PhilipsAnnotationsProcessor {

	private Logger log = Logger.getLogger(Philips103AnnotationsProcessor.class);
	
	private ArrayList<GlobalAnnotation[]> groupAnnotationsList;
	private ArrayList<LeadAnnotation[]> leadAnnotationsList;
	private Philips103AnnotationsExtractor annotationRetriever;
	private long orderinfoRuntime;
	private long dataacquisitionRuntime;
	private long crossleadAnnotationsRuntime;
	private long groupAnnotationsRuntime;
	private long leadmeasurementsRuntime;
	private Restingecgdata restingECG;
	
	private final String createdBy = "Philips Upload";

	public Philips103AnnotationsProcessor(Restingecgdata newECG, String newStudyID, String newUserID, String newRecordName, String newSubjectID) {
		
		restingECGData = new Philips103RestingECGData(newECG);
		annotationRetriever = new Philips103AnnotationsExtractor();
		orderAnnotationsList = new ArrayList<Annotation>();
		dataAcquisitionList = new ArrayList<Annotation>();
		globalAnnotationsList = new ArrayList<Annotation>();
		groupAnnotationsList = new ArrayList<Annotation[]>();
		leadAnnotationsList = new ArrayList<Annotation[]>();
		
		studyID = newStudyID;
		userID = newUserID;
		recordName = newRecordName;
		subjectID = newSubjectID;
	}

	public void setRestingecgdata(Restingecgdata restingECGData) {
		((Philips103RestingECGData) this.restingECGData).setRestingecgdata(restingECGData);
	}

	public Restingecgdata getRestingecgdata() {
		return (Restingecgdata) restingECGData;
	}

	@Override
	protected void extractOrderInformation() {
		long orderinfoStarttime = java.lang.System.currentTimeMillis();
		Orderinfo orderInfo = (Orderinfo) restingECGData.getOrderinfo();
		if(orderInfo != null) {
			LinkedHashMap<String, Object> orderMappings = annotationRetriever.extractOrderInfo((OrderInfo)orderInfo);
			
			for(String key : orderMappings.keySet()) {
				if((orderMappings.get(key) != null)) {
					Annotation annotationDTO = new Annotation();
					annotationDTO.setIsComment(true); // TODO:  Rename this to isNonLeadAnnotation instead
					annotationDTO.setIsSinglePoint(true);
					annotationDTO.setStudyID(studyID);
					annotationDTO.setSubjectID(subjectID);
					annotationDTO.setUserID(userID);
					annotationDTO.setDatasetName(recordName);
					annotationDTO.setAnnotation(orderMappings.get(key).toString());
					annotationDTO.setConceptLabel(key);
					annotationDTO.setCreator(createdBy);
					
					Random randomNum = new Random();
					
					long randomID = java.lang.System.currentTimeMillis() * (long)randomNum.nextInt(10000);
					String ms = String.valueOf(randomID);  // used for GUID
					annData.setUniqueID(ms);
					
					
					orderAnnotationsList.add(annData);
				}
			}
		}
		long orderinfoEndtime = java.lang.System.currentTimeMillis();
		
		orderinfoRuntime = orderinfoEndtime - orderinfoStarttime;
	}

	@Override
	protected void processDataAcquisition() {
	long dataacquisitionStarttime = java.lang.System.currentTimeMillis();

		
		Dataacquisition dataAcquisAnn = restingECG.getDataacquisition();

		//  This one is does not have a check for null since Data Acquisition is a required tag in the Schema
		LinkedHashMap<String, Object> dataMappings = annotationRetriever.extractDataAcquisition(dataAcquisAnn);
		
		log.info("Size of hashmap = " + dataMappings.size());
		
		for(String key : dataMappings.keySet()) {
			if((dataMappings.get(key) != null)) {
				AnnotationData annData = new AnnotationData();
				annData.setIsComment(true); // TODO:  Rename this to isNonLeadAnnotation instead
				annData.setIsSinglePoint(true);
				annData.setStudyID(studyID);
				annData.setSubjectID(subjectID);
				annData.setUserID(userID);
				annData.setDatasetName(recordName);
				annData.setAnnotation(dataMappings.get(key).toString());
				annData.setConceptLabel(key);
				annData.setCreator(createdBy);
				
				Random randomNum = new Random();
				
				long randomID = java.lang.System.currentTimeMillis() * (long)randomNum.nextInt(10000);
				String ms = String.valueOf(randomID);  // used for GUID
				annData.setUniqueID(ms);
				
				
				dataAcquisitionList.add(annData);
			}
		}
		
		long dataacquisitionEndtime = java.lang.System.currentTimeMillis();
		
		dataacquisitionRuntime = dataacquisitionEndtime - dataacquisitionStarttime;

	}

	@Override
	protected void processGroupAnnotations() {
long groupStarttime = java.lang.System.currentTimeMillis();
		
		Groupmeasurements groupAnnotations = restingECG.getMeasurements().getGroupmeasurements();
		
		List<Groupmeasurement> groupAnnotation = groupAnnotations.getGroupmeasurement();
		
		for(Groupmeasurement annotation : groupAnnotation) {
			LinkedHashMap<String, Object> groupMappings = annotationRetriever.extractGroupMeasurements(annotation);
			AnnotationData[] annotationsToAdd = new AnnotationData[groupMappings.size()];
			int index = 0;
			
			for(String key : groupMappings.keySet()) {
				AnnotationData annData = new AnnotationData();
				annData.setIsComment(true); // TODO:  Rename this to isNonLeadAnnotation instead
				annData.setIsSinglePoint(true);
				annData.setStudyID(studyID);
				annData.setSubjectID(subjectID);
				annData.setUserID(userID);
				annData.setDatasetName(recordName);
				annData.setAnnotation(groupMappings.get(key).toString());
				annData.setConceptLabel(key);
				annData.setCreator(createdBy);
				
				Random randomNum = new Random();
				
				long randomID = java.lang.System.currentTimeMillis() * (long)randomNum.nextInt(10000);
				String ms = String.valueOf(randomID);  // used for GUID
				annData.setUniqueID(ms);
				
				
				annotationsToAdd[index] = annData;
				
				index++;
			}
			
			groupAnnotationsList.add(annotationsToAdd);
			
			long groupEndtime = java.lang.System.currentTimeMillis();
			
			groupAnnotationsRuntime = groupEndtime - groupStarttime;
		}

	}

	@Override
	public void populateAnnotations() {
		this.extractOrderInformation();
		this.processDataAcquisition();
		// Note:  Checks for null in each individual method are not needed.  These are required
		// in the schema for this version of Philips 
		if(restingECG.getMeasurements() != null) {
			this.processGlobalAnnotations();
			this.processGroupAnnotations();		// This is not being used temporarily until we decide how to fit this into the schema.  Do NOT remove this method for any reason
			this.processLeadAnnotations();
		}
		
		log.info("The total runtime for parsing order information is " + orderinfoRuntime);
		log.info("The total runtime for parsing data acquisitions is " + dataacquisitionRuntime);
		log.info("The total runtime for parsing cross lead measurements is " + crossleadAnnotationsRuntime);
		log.info("The total runtime for parsing group measurements is " + groupAnnotationsRuntime);
		log.info("The total runtime for parsing lead measurements is " + leadmeasurementsRuntime);
	}

	@Override
	public void processLeadAnnotations() {
	long leadStarttime = java.lang.System.currentTimeMillis();
		
		Leadmeasurements allLeadAnnotations = restingECG.getMeasurements().getLeadmeasurements();
		
		List<Leadmeasurement> leadAnnotationGroup = allLeadAnnotations.getLeadmeasurement();
		int leadIndex = 0;
		
		for(Leadmeasurement annotation: leadAnnotationGroup) {
			LinkedHashMap<String, Object> leadMappings = annotationRetriever.extractLeadMeasurements(annotation);
			AnnotationData[] annotationsToAdd = new AnnotationData[leadMappings.size()];
			int arrayIndex = 0;
			
			for(String key : leadMappings.keySet()) {
				log.info("Annotation Name = " + key + " and value = " + leadMappings.get(key).toString());
				AnnotationData annData = new AnnotationData();
				annData.setIsComment(true); // TODO:  Rename this to isNonLeadAnnotation instead
				annData.setIsSinglePoint(true);
				annData.setStudyID(studyID);
				annData.setSubjectID(subjectID);
				annData.setUserID(userID);
				annData.setDatasetName(recordName);
				annData.setAnnotation(leadMappings.get(key).toString());
				annData.setConceptLabel(key);
				annData.setCreator(createdBy);
				annData.setLeadIndex(leadIndex);
				
				Random randomNum = new Random();
				
				long randomID = java.lang.System.currentTimeMillis() * (long)randomNum.nextInt(10000);
				String ms = String.valueOf(randomID);  // used for GUID
				annData.setUniqueID(ms);
				
				
				annotationsToAdd[arrayIndex] = annData;
				
				arrayIndex++;
			}
			
			leadAnnotationsList.add(annotationsToAdd);
			leadIndex++;
		}
		
		long leadEndtime = java.lang.System.currentTimeMillis();
		
		leadmeasurementsRuntime = leadEndtime - leadStarttime;
	}

	@Override
	public ArrayList<LeadAnnotation> getLeadAnnotations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Annotation> getGroupAnnotations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<GlobalAnnotation> getGlobalAnnotations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void processGlobalAnnotations() {
		
		log.info("Processing Philips 103 Global Annotations");
		
		long crossleadStartTime = java.lang.System.currentTimeMillis();
		
		Globalmeasurements globalMeasurements = restingECG.getMeasurements().getGlobalmeasurements();
		
		LinkedHashMap<String, String> annotationMappings = annotationRetriever.extractGlobalElements(globalAnnotations);
		
		for(String key : annotationMappings.keySet()) {
			if(annotationMappings.get(key) != null) {
				Annotation annotation = new GlobalAnnotation();
				annotation.setIsComment(true); // TODO:  Rename this to isNonLeadAnnotation instead
				annotation.setIsSinglePoint(true);
				annotation.setStudyID(studyID);
				annotation.setSubjectID(Long.valueOf(subjectID));
				annotation.setUserID(Long.valueOf(userID));
				annotation.setDatasetName(recordName);
				annotation.setAnnotation(annotationMappings.get(key).toString());
				annotation.setConceptLabel(key);
				annotation.setCreator(createdBy);
				
				Random randomNum = new Random();
				
				long randomID = java.lang.System.currentTimeMillis() * (long)randomNum.nextInt(10000);
				String ms = String.valueOf(randomID);  // used for GUID
				annotation.setUniqueID(ms);
				
				
				globalAnnotationsList.add(annotation);
			}
		}
		
		long crossleadEndTime = java.lang.System.currentTimeMillis();
		
		crossleadAnnotationsRuntime = crossleadEndTime - crossleadStartTime;

	}

}
