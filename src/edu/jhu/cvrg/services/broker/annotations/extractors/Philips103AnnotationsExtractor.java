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
* @author Chris Jurado, Brandon Benitez
* 
*/
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;
import org.sierraecg.schema.*;

import edu.jhu.cvrg.services.broker.annotations.processors.SchillerAnnotationsProcessor;
import edu.jhu.cvrg.services.broker.annotations.utilities.interfaces.*;

public class Philips103AnnotationsExtractor extends PhilipsAnnotationsExtractor {
	
	private Logger log = Logger.getLogger(Philips103AnnotationsExtractor.class);

	protected LinkedHashMap<String, String> extractGlobalElements(Globalmeasurements globalMeasurements) {
		
		log.info("Extracting Global Measurements for Philips 103");
		
		LinkedHashMap<String, String> annotationMap = new LinkedHashMap<String, String>();
		
		// The mapping will need to be filled manually since currently we cannot get a list of all of the elements
		// Do not do complex annotation measurements yet.  Stick with the simple ones.
		
		// TODO:  Skipping pacedetectleads and pacepulses for now, return to them later.
		// In Philips 1.03, pacedetectleads is a simple annotation
		
		// Main set of global lead measurements
		
		// TODO: In Philips 1.03, the pacemode is a list of pace modes, return to this later
		//annotationMap.put("pacemode", globalMeasurements.getPacemode());
		
		annotationMap.put("pacemalf", globalMeasurements.getPacemalf());
		annotationMap.put("pacemisc", globalMeasurements.getPacemisc());
		annotationMap.put("Ectopic Rhythm", globalMeasurements.getEctopicrhythm());
		annotationMap.put("QT Interval Dispersion", globalMeasurements.getQtintdispersion());
		annotationMap.put("Number of Complexes", globalMeasurements.getNumberofcomplexes());
		annotationMap.put("Number of Groups", globalMeasurements.getNumberofgroups());
		
		String beatClassificationValues = "";
		
		// Only one additional check for null is need in the 1.03 version, 
		// as most of these elements are required in that version of the schema
		// The ones that are not are already taken care of in the function that calls this one.
		
		// concatenate all the beat classifications, as that is how they appear in the XML
		for (Integer beatValue : globalMeasurements.getBeatclassification()) {
			beatClassificationValues = beatClassificationValues + " " + beatValue.toString();
		}
		annotationMap.put("beatclassification", beatClassificationValues);
		int subscript = 1;
		
		// enter the qamessagecodes one by one, as that is how they are in the XML
		for(org.sierraecg.schema.TYPEmessagecode mCode : globalMeasurements.getQamessagecodes().getQamessagecode()) {
			if(mCode != null) {
				String messageName = "qamessagecode" + subscript;
				annotationMap.put(messageName, mCode.value());
				subscript++;
			}
		}
		
		annotationMap.put("qaactioncode", globalMeasurements.getQaactioncode().value());
		annotationMap.put("P Onset", globalMeasurements.getPon());
		annotationMap.put("QRS Onset", globalMeasurements.getQrson());
		annotationMap.put("QRS Offset", globalMeasurements.getQrsoff());
		annotationMap.put("T Onset", globalMeasurements.getTon());
		annotationMap.put("T Offset", globalMeasurements.getToff());
		annotationMap.put("P Frontal Axis", globalMeasurements.getPfrontaxis());
		annotationMap.put("P Horizontal Axis", globalMeasurements.getPhorizaxis());
		annotationMap.put("i40 Frontal Axis", globalMeasurements.getI40Frontaxis());
		annotationMap.put("i40 Horizontal Axis", globalMeasurements.getI40Horizaxis());
		annotationMap.put("QRS Frontal Axis", globalMeasurements.getQrsfrontaxis());
		annotationMap.put("QRS Horizontal Axis", globalMeasurements.getQrshorizaxis());
		annotationMap.put("t40 Frontal Axis", globalMeasurements.getT40Frontaxis());
		annotationMap.put("t40 Horizontal Axis", globalMeasurements.getT40Horizaxis());
		annotationMap.put("ST Frontal Axis", globalMeasurements.getStfrontaxis());
		annotationMap.put("ST Horizontal Axis", globalMeasurements.getSthorizaxis());
		annotationMap.put("T Frontal Axis", globalMeasurements.getTfrontaxis());
		annotationMap.put("T Horizontal Axis", globalMeasurements.getThorizaxis());
		annotationMap.put("Atrial Rate", globalMeasurements.getAtrialrate());
		annotationMap.put("Minimum Ventricular Rate", globalMeasurements.getLowventrate());
		annotationMap.put("Mean Ventricular Rate", globalMeasurements.getMeanventrate());
		annotationMap.put("High Ventricular Rate", globalMeasurements.getHighventrate());
		annotationMap.put("Mean PR Interval", globalMeasurements.getMeanprint());
		annotationMap.put("Mean PR Segment", globalMeasurements.getMeanprseg());
		annotationMap.put("Mean QRS Duration", globalMeasurements.getMeanqrsdur());
		annotationMap.put("Mean QT Interval", globalMeasurements.getMeanqtint());
		annotationMap.put("Mean QT Corrected", globalMeasurements.getMeanqtc());
		annotationMap.put("Delta Wave Count", globalMeasurements.getDeltawavecount());
		annotationMap.put("Delta Wave Percent", globalMeasurements.getDeltawavepercent());
		annotationMap.put("bigeminycount", globalMeasurements.getBigeminycount());
		annotationMap.put("bigeminystring", globalMeasurements.getBigeminystring());
		annotationMap.put("trigeminycount", globalMeasurements.getTrigeminycount());
		annotationMap.put("trigeminystring", globalMeasurements.getTrigeminystring());
		annotationMap.put("wenckcount", globalMeasurements.getWenckcount());
		annotationMap.put("wenckstring", globalMeasurements.getWenckstring());
		annotationMap.put("flutterfibcount", globalMeasurements.getFlutterfibcount());
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
	public LinkedHashMap<String, Object> extractOrderInfo(OrderInfo orderInfo) {
		
		log.info("Extracting Order Info Philips 103");
		
		LinkedHashMap<String, Object> orderMap = new LinkedHashMap<String, Object>();
		
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
	protected LinkedHashMap<String, Object> extractDataAcquisition(DataAcquisition dataAcquisition) {
		
		log.info("Extracting Data Acquisition Philips 103");
		
		LinkedHashMap<String, Object> dataMappings = new LinkedHashMap<String, Object>();
		
		dataMappings.put("Database ID", dataAcquisition.getDatabaseid());
		dataMappings.put("Machine", (Machine)dataAcquisition.getMachine());
		
		// Now get the acquirer block in the XML
		Acquirer acquirer = (Acquirer)dataAcquisition.getAcquirer(); 
		
		dataMappings.put("Acquirer Encounter ID", acquirer.getEncounterid());
		dataMappings.put("Acquirer Operator ID", acquirer.getOperatorid());
		dataMappings.put("Editing Operator ID", acquirer.getEditingoperatorid());
		dataMappings.put("Room", acquirer.getRoom());
		dataMappings.put("Department ID", acquirer.getDepartmentid());
		dataMappings.put("Department Name", acquirer.getDepartmentname());
		dataMappings.put("Institution ID", acquirer.getInstitutionid());
		dataMappings.put("Institution Name", acquirer.getInstitutionname());
		dataMappings.put("Institution Location ID", acquirer.getInstitutionlocationid());
		dataMappings.put("Institution Location Name", acquirer.getInstitutionlocationname());
		dataMappings.put("Ordering Clinician", acquirer.getOrderingclinicianname());
		dataMappings.put("Ordering Clinician UPIN", acquirer.getOrderingclinicianUPIN());
		dataMappings.put("Consulting Clinician", acquirer.getReviewingclinician());
		
		// Retrieve the Signal Characteristics block.  The Sampling rate and number of channels
		// information will not be gathered here, since those are being tracked elsewhere.
		
		Signalcharacteristics signalProperties = (Signalcharacteristics)dataAcquisition.getSignalcharacteristics();
		
		dataMappings.put("Signal Resolution", signalProperties.getSignalresolution());
		dataMappings.put("AC Setting", signalProperties.getAcsetting());
		dataMappings.put("Acquisition Type", signalProperties.getAcquisitiontype());
		dataMappings.put("Bits Per Sample", signalProperties.getBitspersample());
		dataMappings.put("Signal Offset", signalProperties.getSignaloffset());
		dataMappings.put("Signal Signed", signalProperties.getSignalsigned());
		dataMappings.put("Lead Set", signalProperties.getLeadset());
		
		return dataMappings;
	}
	
	@Override
	protected LinkedHashMap<String, Object> completeGroupMeasurementsExtraction(LinkedHashMap<String, Object> annotationMap, GroupMeasurement groupMeasurement){
		
		log.info("Completing Group Measurements Exctraction Philips 103");
		
		annotationMap.put("groupreserved", groupMeasurement.getGroupreserved());
		
		return annotationMap;
	}
}
