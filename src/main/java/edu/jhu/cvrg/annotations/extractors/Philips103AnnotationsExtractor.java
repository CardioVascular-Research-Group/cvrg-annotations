package edu.jhu.cvrg.annotations.extractors;
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
* @author Chris Jurado, Brandon Benitez
* 
*/

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.sierraecg.schema.Acquirer;
import org.sierraecg.schema.Drgcategories;
import org.sierraecg.schema.Drgcategory;
import org.sierraecg.schema.Globalmeasurements;
import org.sierraecg.schema.Signalcharacteristics;
import org.sierraecg.schema.TYPEmessagecode;

import edu.jhu.cvrg.annotations.utilities.interfaces.DataAcquisition;
import edu.jhu.cvrg.annotations.utilities.interfaces.GroupMeasurement;
import edu.jhu.cvrg.annotations.utilities.interfaces.OrderInfo;
import edu.jhu.cvrg.annotations.wrapper.philips.Philips103Acquirer;
import edu.jhu.cvrg.annotations.wrapper.philips.Philips103GlobalMeasurements;
import edu.jhu.cvrg.annotations.wrapper.philips.Philips103SignalCharacteristics;

public class Philips103AnnotationsExtractor extends PhilipsAnnotationsExtractor {
	
	private Logger log = Logger.getLogger(Philips103AnnotationsExtractor.class);

	public Map<String, String> extractGlobalElements(Globalmeasurements globalMeasurements) {
		
		log.info("Extracting Global Measurements for Philips 103");
		
		Map<String, String> annotationMap = new HashMap<String, String>();
		
		// The mapping will need to be filled manually since currently we cannot get a list of all of the elements
		// Do not do complex annotation measurements yet.  Stick with the simple ones.
		
		// TODO:  Skipping pacedetectleads and pacepulses for now, return to them later.
		// In Philips 1.03, pacedetectleads is a simple annotation
		
		// Main set of global lead measurements
		
		// TODO: In Philips 1.03, the pacemode is a list of pace modes, return to this later
		//annotationMap.put("pacemode", globalMeasurements.getPacemode());
		
		commomGlobalElements(new Philips103GlobalMeasurements(globalMeasurements), annotationMap);
		
		int subscript = 1;
		// enter the qamessagecodes one by one, as that is how they are in the XML
		for(TYPEmessagecode mCode : globalMeasurements.getQamessagecodes().getQamessagecode()) {
			if(mCode != null) {
				String messageName = "qamessagecode" + subscript;
				annotationMap.put(messageName, mCode.value());
				subscript++;
			}
		}
		
		annotationMap.put("P Onset", globalMeasurements.getPon());
		annotationMap.put("QRS Onset", globalMeasurements.getQrson());
		annotationMap.put("QRS Offset", globalMeasurements.getQrsoff());
		annotationMap.put("T Onset", globalMeasurements.getTon());
		annotationMap.put("T Offset", globalMeasurements.getToff());
		annotationMap.put("QRS Initial Angle", globalMeasurements.getQrsinitangle());
		annotationMap.put("QRS Initial Magnitude", globalMeasurements.getQrsinitmag());
		annotationMap.put("QRS Maximum Angle", globalMeasurements.getQrsmaxangle());
		annotationMap.put("QRS Maximum Magnitude", globalMeasurements.getQrsmaxmag());
		annotationMap.put("QRS Terminal Angle", globalMeasurements.getQrstermangle());
		annotationMap.put("QRS Terminal Magnitude", globalMeasurements.getQrstermmag());
		annotationMap.put("QRS Rotation", globalMeasurements.getQrsrotation());
		annotationMap.put("globalreserved", globalMeasurements.getGlobalreserved());
		
		return annotationMap;
	}

	@Override
	public Map<String, String> extractOrderInfo(OrderInfo orderInfo) {
		
		log.info("Extracting Order Info Philips 103");
		
		Map<String, String> orderMap = new HashMap<String, String>();
		
		orderMap.put("Order Number", orderInfo.getEncounterId());
		orderMap.put("Operator ID", orderInfo.getOperatorid());
		orderMap.put("Order Number", orderInfo.getOrdernumber());
		orderMap.put("Viper Unique Order ID", orderInfo.getViperuniqueorderid());
		orderMap.put("Ordering Clinician Name", orderInfo.getOrderingclinicianname());
		orderMap.put("Ordering Clinician UPIN", orderInfo.getOrderingclinicianUPIN());
		orderMap.put("Reason For Order", orderInfo.getReasonfororder());
		
		int subscript = 1;
		
		if (orderInfo.getDrgcategories() != null) {
			for (Drgcategory drgCategory : ((Drgcategories) orderInfo.getDrgcategories()).getDrgcategory()) {
				if (drgCategory != null) {
					String messageName = "drgcategories" + subscript;
					orderMap.put(messageName, drgCategory.getValue());
					subscript++;
				}
			}
		}
		
		return orderMap;
	}

	@Override
	public Map<String, String> extractDataAcquisition(DataAcquisition dataAcquisition) {
		
		log.info("Extracting Data Acquisition Philips 103");
		
		Map<String, String> dataMappings = new HashMap<String, String>();
		
		// Now get the acquirer block in the XML
		Acquirer acquirer = (Acquirer)dataAcquisition.getAcquirer(); 
		
		dataMappings.put("Editing Operator ID", acquirer.getEditingoperatorid());
		
		dataMappings.put("Institution Location ID", acquirer.getInstitutionlocationid());
		dataMappings.put("Institution Location Name", acquirer.getInstitutionlocationname());
		
		// Retrieve the Signal Characteristics block.  The Sampling rate and number of channels
		// information will not be gathered here, since those are being tracked elsewhere.
		
		Signalcharacteristics signalProperties = (Signalcharacteristics)dataAcquisition.getSignalcharacteristics();
		
		if(signalProperties.getLeadset() != null){
			dataMappings.put("Lead Set", signalProperties.getLeadset().toString());	
		}else{
			dataMappings.put("Lead Set", null);
		}
		
		this.commomDataAcquisition(dataAcquisition, new Philips103Acquirer(acquirer), new Philips103SignalCharacteristics(signalProperties), dataMappings);
		
		//Duplicate information, already extracted on extractOrderInfo method
//		dataMappings.put("Ordering Clinician UPIN", acquirer.getOrderingclinicianUPIN());
//		dataMappings.put("Ordering Clinician Name ", acquirer.getOrderingclinicianname());
		
		return dataMappings;
	}
	
	@Override
	protected Map<String, String> completeGroupMeasurementsExtraction(Map<String, String> annotationMap, GroupMeasurement groupMeasurement){
		
		log.info("Completing Group Measurements Exctraction Philips 103");
		
		annotationMap.put("groupreserved", groupMeasurement.getGroupreserved());
		
		return annotationMap;
	}
}
