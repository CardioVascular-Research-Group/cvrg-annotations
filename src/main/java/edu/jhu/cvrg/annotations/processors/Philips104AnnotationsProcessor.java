package edu.jhu.cvrg.annotations.processors;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.cvrgrid.philips.jaxb.beans.Crossleadmeasurements;
import org.cvrgrid.philips.jaxb.beans.Dataacquisition;
import org.cvrgrid.philips.jaxb.beans.Groupmeasurement;
import org.cvrgrid.philips.jaxb.beans.Groupmeasurements;
import org.cvrgrid.philips.jaxb.beans.Internalmeasurements;
import org.cvrgrid.philips.jaxb.beans.Leadmeasurement;
import org.cvrgrid.philips.jaxb.beans.Leadmeasurements;
import org.cvrgrid.philips.jaxb.beans.Orderinfo;
import org.cvrgrid.philips.jaxb.beans.Restingecgdata;

import edu.jhu.cvrg.annotations.extractors.Philips104AnnotationsExtractor;
import edu.jhu.cvrg.annotations.wrapper.philips.Philips104DataAcquisition;
import edu.jhu.cvrg.annotations.wrapper.philips.Philips104Groupmeasurement;
import edu.jhu.cvrg.annotations.wrapper.philips.Philips104LeadMeasurement;
import edu.jhu.cvrg.annotations.wrapper.philips.Philips104OrderInfo;
import edu.jhu.cvrg.annotations.wrapper.philips.Philips104RestingECGData;

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

public class Philips104AnnotationsProcessor extends PhilipsAnnotationsProcessor {

	private Philips104AnnotationsExtractor annotationRetriever;
	
	public Philips104AnnotationsProcessor(Restingecgdata newECG) {
		super();
		this.restingECGData = new Philips104RestingECGData(newECG);
		this.annotationRetriever = new Philips104AnnotationsExtractor();
		log = Logger.getLogger(Philips104AnnotationsProcessor.class);
	}
	
	@Override
	protected void extractOrderInformation() {
		Orderinfo orderinfoAnn = (Orderinfo) restingECGData.getOrderinfo();
		
		if(orderinfoAnn != null) {
			Map<String, String> orderMappings = annotationRetriever.extractOrderInfo(new Philips104OrderInfo(orderinfoAnn));
			globalAnnotations.putAll(orderMappings);
		}
	}

	@Override
	protected void processDataAcquisition() {
		Dataacquisition dataAcquisAnn = (Dataacquisition) restingECGData.getDataacquisition();
		
		//  This one is does not have a check for null since Data Acquisition is a required tag in the Schema
		Map<String, String> dataMappings = annotationRetriever.extractDataAcquisition(new Philips104DataAcquisition(dataAcquisAnn));
		globalAnnotations.putAll(dataMappings);
	
	}
	
	/**
	 * Unused
	 */
	@Override
	protected void processGroupAnnotations() {
		Groupmeasurements allGroupAnnotations = ((Internalmeasurements) restingECGData.getInternalmeasurements()).getGroupmeasurements();
		
		if(allGroupAnnotations != null) {
			List<Groupmeasurement> groupAnnotation = allGroupAnnotations.getGroupmeasurement();
			
			int index = 0;
			for(Groupmeasurement annotation : groupAnnotation) {
				Map<String, String> groupMappings = annotationRetriever.extractGroupMeasurements(new Philips104Groupmeasurement(annotation));
				this.groupAnnotations.put(String.valueOf(index), groupMappings);
				index++;
			}
		}
		
	}

	@Override
	public void processLeadAnnotations() {
		Leadmeasurements allLeadAnnotations = ((Internalmeasurements) restingECGData.getInternalmeasurements()).getLeadmeasurements();
		
		if(allLeadAnnotations != null) {
			List<Leadmeasurement> leadAnnotationGroup = allLeadAnnotations.getLeadmeasurement();
			
			Integer leadIndex = 0;
			
			for(Leadmeasurement annotation: leadAnnotationGroup) {
				Map<String, String> leadMappings = annotationRetriever.extractLeadMeasurements(new Philips104LeadMeasurement(annotation));
				
				leadsAnnotations.put(leadIndex, leadMappings);
				leadIndex++;
			}
		}
		
	}

	@Override
	public void processGlobalAnnotations() {
		Crossleadmeasurements crossAnnotations = ((Internalmeasurements) restingECGData.getInternalmeasurements()).getCrossleadmeasurements();
		
		if(crossAnnotations != null) {
			Map<String, String> annotationMappings = annotationRetriever.extractGlobalElements(crossAnnotations);
			globalAnnotations.putAll(annotationMappings);
		}
		
	}


}
