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
* @author Chris Jurado
* 
*/
import java.util.HashMap;
import java.util.Map;

import edu.jhu.cvrg.annotations.utilities.interfaces.RestingECGData;

public abstract class PhilipsAnnotationsProcessor extends AnnotationsProcessor{

	protected long orderinfoRuntime;
	protected long dataacquisitionRuntime;
	protected long crossleadAnnotationsRuntime;
	protected long groupAnnotationsRuntime;
	protected long leadmeasurementsRuntime;
	
	protected RestingECGData restingECGData;
	protected Map<String, String> orderAnnotations;
	protected Map<String, String> dataAcquisitions;
	
	protected Map<String, Map<String, String>> groupAnnotations;
	
	private static final String PHILIPS_NAME = "Philips Upload"; 
	
	public PhilipsAnnotationsProcessor() {
		super();
		orderAnnotations = new HashMap<String, String>();
		dataAcquisitions = new HashMap<String, String>();
		groupAnnotations = new HashMap<String, Map<String, String>>();
		
		this.name = PHILIPS_NAME;
	}
	
	public Map<String, String>  getOrderInfo() {
		return orderAnnotations;
	}
	
	public Map<String, String>  getDataAcquisitions() {
		return dataAcquisitions;
	}
	
	public Map<String, Map<String, String>>  getGroupAnnotations() {
		return groupAnnotations;
	}
	
	protected abstract void extractOrderInformation();
	
	protected abstract void processDataAcquisition();
	
	protected abstract void processGroupAnnotations();
	
	@Override
	public void populateAnnotations() {
		this.extractOrderInformation();
		this.processDataAcquisition();
	}

	@Override
	public void processAll() {
		this.extractOrderInformation();
		this.processDataAcquisition();
		// Note:  Checks for null in each individual method are not needed.  These are required
		// in the schema for this version of Philips 
		if(restingECGData.getInternalmeasurements() != null) {
			this.processGlobalAnnotations();
			//this.processGroupAnnotations();		// This is not being used temporarily until we decide how to fit this into the schema.  Do NOT remove this method for any reason
			this.processLeadAnnotations();
		}
		
		log.info("The total runtime for parsing order information is " + orderinfoRuntime);
		log.info("The total runtime for parsing data acquisitions is " + dataacquisitionRuntime);
		log.info("The total runtime for parsing global/crossLead measurements is " + crossleadAnnotationsRuntime);
		log.info("The total runtime for parsing group measurements is " + groupAnnotationsRuntime);
		log.info("The total runtime for parsing lead measurements is " + leadmeasurementsRuntime);
	}
}
