package edu.jhu.cvrg.services.broker.annotations.extractors;
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
* @author Chris Jurado, Brandon Benitez, Dave Hopkins
* 
*/
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.cvrgrid.schiller.jaxb.beans.AnnotationGlobal;
import org.cvrgrid.schiller.jaxb.beans.AnnotationLead;
import org.cvrgrid.schiller.jaxb.beans.Channel;
import org.cvrgrid.schiller.jaxb.beans.Event;
import org.cvrgrid.schiller.jaxb.beans.Examdescript;
import org.cvrgrid.schiller.jaxb.beans.Patdata;

import edu.jhu.cvrg.services.broker.annotations.utilities.AnnotationMaps;

public class SchillerAnnotationsExtractor extends AnnotationsExtractor {

	private Logger log = Logger.getLogger(SchillerAnnotationsExtractor.class);
	
	public LinkedHashMap<String, Object> extractGlobalElements(Event globalAnnotations) {
		
		log.info("Extracting Schiller Global Elements.");
		
		LinkedHashMap<String, Object> annotationMappings = new LinkedHashMap<String, Object>();
		List<AnnotationGlobal> globalAnnotationsList = globalAnnotations.getAnnotationGlobal();
		
		for(AnnotationGlobal globalAnnotation: globalAnnotationsList) {
			if (globalAnnotation.getValue() != null){
				String annotationName = globalAnnotation.getName();
				String ontologyName = "";
				
				if (AnnotationMaps.getSchillerAnnotationMap().containsKey(annotationName)){
					ontologyName = AnnotationMaps.getSchillerAnnotationMap().get(annotationName);
				}
				else{
					ontologyName = annotationName;
				}
				String checked = checkForDash(globalAnnotation.getValue());
				annotationMappings.put(ontologyName, checked);
			}
		}
		return annotationMappings;
	}

	public LinkedHashMap<String, Object> extractLeadMeasurements(Channel list) {
		
		log.info("Extracting Schiller Lead Measurements.");
		
		LinkedHashMap<String, Object> annotationMappings = new LinkedHashMap<String, Object>();
		List<AnnotationLead> annotationLeadList = list.getAnnotationLead(); 
		
    	for (AnnotationLead annotationLead : annotationLeadList ) {
			if (annotationLead.getValue() != null){
				String annotationName = annotationLead.getName();
				String ontologyName = "";
				
				if (AnnotationMaps.getSchillerAnnotationMap().containsKey(annotationName)){
					ontologyName = AnnotationMaps.getSchillerAnnotationMap().get(annotationName);
				}
				else{
					ontologyName = annotationName;
				}
				String checked = checkForDash(annotationLead.getValue());
				annotationMappings.put(ontologyName, checked);
			}
    	}
		return annotationMappings;	
	}
	
	LinkedHashMap<String, Object> extractExamdescript(Examdescript examDescript) {
		
		LinkedHashMap<String, Object> descriptMap = new LinkedHashMap<String, Object>();
		
		descriptMap.put("Exam Start Date", examDescript.getStartdatetime().getDate());
		descriptMap.put("Exam Start Time", examDescript.getStartdatetime().getTime());
		descriptMap.put("Rec Type", examDescript.getRectype());
		
		String acqDev = examDescript.getAquiringdevice().getHardware().getVendor() + " " 
				+ examDescript.getAquiringdevice().getHardware().getModel();
		
		descriptMap.put("Acquiring Device", acqDev);
		
		if (examDescript.getJobId().trim().length() != 0){
			descriptMap.put("Job ID", examDescript.getJobId());
		}
		
		descriptMap.put("User ID", examDescript.getUserId());
		
		if (examDescript.getCaseId().trim().length() != 0){
			descriptMap.put("Case ID", examDescript.getCaseId());
		}
		
		return descriptMap;
	}
	
	LinkedHashMap<String, Object> extractPatdata(Patdata patData) {
		LinkedHashMap<String, Object> patdataAnnsMap = new LinkedHashMap<String, Object>();
		
		patdataAnnsMap.put("Patient ID", patData.getId());
		patdataAnnsMap.put("Patient Lastname", patData.getLastname());
		patdataAnnsMap.put("Patient Firstname", patData.getFirstname());
		patdataAnnsMap.put("Patient Birthdate", patData.getBirthdate());
		patdataAnnsMap.put("Patient Gender", patData.getGender());
		patdataAnnsMap.put("Patient Ethnic", patData.getEthnic());
		String weight = patData.getWeight().getValue() + patData.getWeight().getUnit();
		patdataAnnsMap.put("Patient Weight", weight);
		String height = patData.getHeight().getValue() + patData.getHeight().getUnit();
		patdataAnnsMap.put("Patient Height", height);
		
		if (patData.getPacemaker().getValue() != null){
			patdataAnnsMap.put("Pacemaker", patData.getPacemaker().getValue());
		}
		
		return patdataAnnsMap;
	}
	
	private static String checkForDash(String test) {
		if (test.trim().equalsIgnoreCase("-")){
			test = "0";
		}
		return test;
	}

}
