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
* @author Chris Jurado, Brandon Benitez, Michael Shipway
* 
*/
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.sierraecg.schema.Dataacquisition;
import org.sierraecg.schema.Globalmeasurements;
import org.sierraecg.schema.Groupmeasurement;
import org.sierraecg.schema.Groupmeasurements;
import org.sierraecg.schema.Leadmeasurement;
import org.sierraecg.schema.Leadmeasurements;
import org.sierraecg.schema.Measurements;
import org.sierraecg.schema.Orderinfo;
import org.sierraecg.schema.Restingecgdata;

import edu.jhu.cvrg.annotations.extractors.Philips103AnnotationsExtractor;
import edu.jhu.cvrg.annotations.wrapper.philips.Philips103DataAcquisition;
import edu.jhu.cvrg.annotations.wrapper.philips.Philips103GroupMeasurement;
import edu.jhu.cvrg.annotations.wrapper.philips.Philips103LeadMeasurement;
import edu.jhu.cvrg.annotations.wrapper.philips.Philips103OrderInfo;
import edu.jhu.cvrg.annotations.wrapper.philips.Philips103RestingECGData;

public class Philips103AnnotationsProcessor extends PhilipsAnnotationsProcessor {

	private Philips103AnnotationsExtractor annotationRetriever;
	
	public Philips103AnnotationsProcessor(Restingecgdata newECG) {
		super();
		this.restingECGData = new Philips103RestingECGData(newECG);
		this.annotationRetriever = new Philips103AnnotationsExtractor();
		log = Logger.getLogger(Philips103AnnotationsProcessor.class);
	}

	@Override
	protected void extractOrderInformation() {
		long orderinfoStarttime = java.lang.System.currentTimeMillis();
		
		Orderinfo orderInfo = (Orderinfo) restingECGData.getOrderinfo();
		if(orderInfo != null) {
			Map<String, String> orderMappings = annotationRetriever.extractOrderInfo(new Philips103OrderInfo(orderInfo));
			globalAnnotations.putAll(orderMappings);
		}
		
		orderinfoRuntime = java.lang.System.currentTimeMillis() - orderinfoStarttime;
	}

	@Override
	protected void processDataAcquisition() {
		long dataacquisitionStarttime = java.lang.System.currentTimeMillis();
		
		Dataacquisition dataAcquisAnn = (Dataacquisition) restingECGData.getDataacquisition();

		if(dataAcquisAnn != null){
			//  This one is does not have a check for null since Data Acquisition is a required tag in the Schema
			Map<String, String> dataMappings = annotationRetriever.extractDataAcquisition(new Philips103DataAcquisition(dataAcquisAnn));
			globalAnnotations.putAll(dataMappings);
		}
		
		dataacquisitionRuntime = java.lang.System.currentTimeMillis() - dataacquisitionStarttime;

	}
	/**
	 * Unused
	 */
	
	@Override
	protected void processGroupAnnotations() {
		long groupStarttime = java.lang.System.currentTimeMillis();
		
		Groupmeasurements groupAnnotations = ((Measurements)restingECGData.getInternalmeasurements()).getGroupmeasurements();
		List<Groupmeasurement> groupAnnotation = groupAnnotations.getGroupmeasurement();
		
		int index = 0;
		for(Groupmeasurement annotation : groupAnnotation) {
			Map<String, String> groupMappings = annotationRetriever.extractGroupMeasurements(new Philips103GroupMeasurement(annotation));
			
			this.groupAnnotations.put(String.valueOf(index), groupMappings);
			index++;
		}

		groupAnnotationsRuntime = java.lang.System.currentTimeMillis() - groupStarttime;
		
		log.info("The total runtime for parsing global/crossLead measurements is " + crossleadAnnotationsRuntime);
	}

	@Override
	public void processLeadAnnotations() {
	long leadStarttime = java.lang.System.currentTimeMillis();
		
		Leadmeasurements allLeadAnnotations = ((Measurements)restingECGData.getInternalmeasurements()).getLeadmeasurements();
		
		List<Leadmeasurement> leadAnnotationGroup = allLeadAnnotations.getLeadmeasurement();
		Integer leadIndex = 0;
		
		for(Leadmeasurement annotation: leadAnnotationGroup) {
			Map<String, String> leadMappings = annotationRetriever.extractLeadMeasurements(new Philips103LeadMeasurement(annotation));
			
			leadsAnnotations.put(leadIndex, leadMappings);
			leadIndex++;
		}
		
		leadmeasurementsRuntime = java.lang.System.currentTimeMillis() - leadStarttime;
		
		log.info("The total runtime for parsing lead measurements is " + leadmeasurementsRuntime);
	}
	
	@Override
	public void processGlobalAnnotations() {
		
		log.info("Processing Philips 103 Global Annotations");
		
		long crossleadStartTime = java.lang.System.currentTimeMillis();
		
		Globalmeasurements globalMeasurements = ((Measurements)restingECGData.getInternalmeasurements()).getGlobalmeasurements();
		
		Map<String, String> annotationMappings = annotationRetriever.extractGlobalElements(globalMeasurements);
		
		globalAnnotations.putAll(annotationMappings);
		
		crossleadAnnotationsRuntime = java.lang.System.currentTimeMillis() - crossleadStartTime;
		
		log.info("The total runtime for parsing group measurements is " + groupAnnotationsRuntime);
	}




}
