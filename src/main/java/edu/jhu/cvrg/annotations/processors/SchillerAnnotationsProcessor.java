package edu.jhu.cvrg.annotations.processors;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.cvrgrid.schiller.jaxb.beans.Channel;
import org.cvrgrid.schiller.jaxb.beans.ComXiriuzSemaXmlSchillerEDISchillerEDI;
import org.cvrgrid.schiller.jaxb.beans.Event;
import org.cvrgrid.schiller.jaxb.beans.Examdescript;
import org.cvrgrid.schiller.jaxb.beans.Patdata;
import org.cvrgrid.schiller.jaxb.beans.Wavedata;

import edu.jhu.cvrg.annotations.extractors.SchillerAnnotationsExtractor;
import edu.jhu.cvrg.annotations.utilities.AnnotationMaps;


public class SchillerAnnotationsProcessor extends AnnotationsProcessor {
	
	private ComXiriuzSemaXmlSchillerEDISchillerEDI schillerEDI;
	private SchillerAnnotationsExtractor schillerAnnotationsExtractor;;
	
	private static final String SCHILLER_NAME = "Schiller Upload";
	
	public SchillerAnnotationsProcessor(ComXiriuzSemaXmlSchillerEDISchillerEDI ecg){
		super();
		schillerAnnotationsExtractor = new SchillerAnnotationsExtractor();
		schillerEDI = ecg;
		log = Logger.getLogger(SchillerAnnotationsProcessor.class);
		this.name = SCHILLER_NAME;   
	}

	@Override
	public void populateAnnotations() {
		log.info("Entering populateAnnotations");
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
						
						Map<String, String> leadMappings = schillerAnnotationsExtractor.extractLeadMeasurements(subChannel);
			    		
			    		leadsAnnotations.put(leadIndex, leadMappings);
			    	}
				}
			}			
		}
	}

	@Override
	public void processGlobalAnnotations() {
		
		globalAnnotations = new HashMap<String, String>();
		
		Map<String, String> examDescript = this.extractExamdescript();
		
		if(examDescript != null){
			globalAnnotations.putAll(examDescript);
		}
		
		Map<String, String> patData = this.extractPatdata();
		
		if(patData != null){
			globalAnnotations.putAll(patData);
		}
		
		Event global = schillerEDI.getEventdata().getEvent();
		if(global != null) {
			Map<String, String> annotationMappings = schillerAnnotationsExtractor.extractGlobalElements(global);
			globalAnnotations.putAll(annotationMappings);
		}
	}
	
	private Map<String, String> extractExamdescript() {
		Examdescript examdescriptAnn = schillerEDI.getExamdescript();
		
		Map<String, String> orderMappings = null;
		if(examdescriptAnn != null) {
			orderMappings = schillerAnnotationsExtractor.extractExamdescript(examdescriptAnn);
		}
		
		return orderMappings;
	}
	
	private Map<String, String> extractPatdata() {
		Patdata patdataAnn = schillerEDI.getPatdata();
		
		Map<String, String> orderMappings = null;
		
		if(patdataAnn != null) {
			orderMappings = schillerAnnotationsExtractor.extractPatdata(patdataAnn);
		}
		
		return orderMappings;
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
