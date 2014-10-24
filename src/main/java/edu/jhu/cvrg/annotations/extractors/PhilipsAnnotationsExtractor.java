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

import edu.jhu.cvrg.annotations.utilities.interfaces.Acquirer;
import edu.jhu.cvrg.annotations.utilities.interfaces.DataAcquisition;
import edu.jhu.cvrg.annotations.utilities.interfaces.GlobalMeasurements;
import edu.jhu.cvrg.annotations.utilities.interfaces.GroupMeasurement;
import edu.jhu.cvrg.annotations.utilities.interfaces.LeadMeasurement;
import edu.jhu.cvrg.annotations.utilities.interfaces.OrderInfo;
import edu.jhu.cvrg.annotations.utilities.interfaces.SignalCharacteristics;

public abstract class PhilipsAnnotationsExtractor extends AnnotationsExtractor {
	
	protected Logger log = Logger.getLogger(PhilipsAnnotationsExtractor.class);

	protected void commomGlobalElements(GlobalMeasurements globalMeasurements, Map<String, String> annotationMap) {
		
		annotationMap.put("pacemalf", globalMeasurements.getPacemalf());
		annotationMap.put("pacemisc", globalMeasurements.getPacemisc());
		annotationMap.put("Ectopic Rhythm", globalMeasurements.getEctopicrhythm());
		annotationMap.put("QT Interval Dispersion", globalMeasurements.getQtintdispersion());
		annotationMap.put("Number of Complexes", globalMeasurements.getNumberofcomplexes());
		annotationMap.put("Number of Groups", globalMeasurements.getNumberofgroups());
		
		// Only one additional check for null is need in the 1.03 version, 
		// as most of these elements are required in that version of the schema
		// The ones that are not are already taken care of in the function that calls this one.
		if(globalMeasurements.getQaactioncode() != null) {
			annotationMap.put("qaactioncode", globalMeasurements.getQaactioncode());
		}
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
		annotationMap.put("Maximum Ventricular Rate", globalMeasurements.getHighventrate());
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
		annotationMap.put("beatclassification", globalMeasurements.getBeatclassification());
		
	}

	protected void commomDataAcquisition(DataAcquisition dataAcquisition, Acquirer acquirer, SignalCharacteristics signalProperties, Map<String, String> annotationMap){
		
		annotationMap.put("Machine", dataAcquisition.getMachineId());
		annotationMap.put("Database ID", dataAcquisition.getDatabaseid());
		
		annotationMap.put("Acquirer Encounter ID", acquirer.getEncounterId());
		annotationMap.put("Acquirer Operator ID", acquirer.getOperatorId());
		annotationMap.put("Room", acquirer.getRoom());
		annotationMap.put("Department ID", acquirer.getDepartmentid());
		annotationMap.put("Department Name", acquirer.getDepartmentname());
		annotationMap.put("Institution ID", acquirer.getInstitutionid());
		annotationMap.put("Institution Name", acquirer.getInstitutionname());
		annotationMap.put("Ordering Clinician UPIN/ID", acquirer.getOrderingclinicianUPINId());
		annotationMap.put("Ordering Clinician Name ", acquirer.getOrderingclinicianName());
		annotationMap.put("Consulting Clinician", acquirer.getConsultingclinician());
		
		annotationMap.put("Signal Resolution", signalProperties.getSignalresolution());
		annotationMap.put("AC Setting", signalProperties.getAcsetting());
		annotationMap.put("Bits Per Sample", String.valueOf(signalProperties.getBitspersample()));
		annotationMap.put("Signal Offset", signalProperties.getSignaloffset());
		annotationMap.put("Acquisition Type", signalProperties.getAcquisitiontype());	
		annotationMap.put("Signal Signed", signalProperties.getSignalsigned());	
	
	}
	
	public Map<String, String> extractLeadMeasurements(LeadMeasurement leadMeasurement){
		
		log.info("Extracting Lead Elements for Philips");
			
		Map<String, String> annotationMap = new HashMap<String, String>();

		annotationMap.put("P Amplitude", leadMeasurement.getPamp());
		annotationMap.put("P Duration", leadMeasurement.getPdur());
		annotationMap.put("P Area", leadMeasurement.getParea());
		annotationMap.put("P' Amplitude", leadMeasurement.getPpamp());
		annotationMap.put("P' Duration", leadMeasurement.getPpdur());
		annotationMap.put("P'' Duration", leadMeasurement.getPpppdur());
		annotationMap.put("P' Area", leadMeasurement.getPparea());
		annotationMap.put("P'' Area", leadMeasurement.getPppparea());
		annotationMap.put("Q Amplitude", leadMeasurement.getQamp());
		annotationMap.put("Q Duration", leadMeasurement.getQdur());
		annotationMap.put("R Amplitude", leadMeasurement.getRamp());
		annotationMap.put("R Duration", leadMeasurement.getRdur());
		annotationMap.put("S Amplitude", leadMeasurement.getSamp());
		annotationMap.put("S Duration", leadMeasurement.getSdur());
		annotationMap.put("R' Amplitude", leadMeasurement.getRpamp());
		annotationMap.put("R' Duration", leadMeasurement.getRpdur());
		annotationMap.put("S' Amplitude", leadMeasurement.getSpamp());
		annotationMap.put("S' Duration", leadMeasurement.getSpdur());
		annotationMap.put("Ventricular Activation Time", leadMeasurement.getVat());
		annotationMap.put("QRS Amplitude", leadMeasurement.getQrsppk());
		annotationMap.put("QRS Duration", leadMeasurement.getQrsdur());
		annotationMap.put("QRS Area", leadMeasurement.getQrsarea());
		annotationMap.put("ST Segment Onset", leadMeasurement.getSton());
		annotationMap.put("ST Segment Midpoint", leadMeasurement.getStmid());
		annotationMap.put("ST Segment Offset", leadMeasurement.getStend());
		annotationMap.put("ST Segment Duration", leadMeasurement.getStdur());
		annotationMap.put("ST Segment Slope", leadMeasurement.getStslope());
		//TODO Check this return
		annotationMap.put("ST Segment Shape", leadMeasurement.getStshape().toString());
		annotationMap.put("T Amplitude", leadMeasurement.getTamp());
		annotationMap.put("T Duration", leadMeasurement.getTdur());
		annotationMap.put("T Area", leadMeasurement.getTarea());
		annotationMap.put("T' Amplitude", leadMeasurement.getTpamp());
		annotationMap.put("T'' Amplitude", leadMeasurement.getTptpdur());
		annotationMap.put("T' Duration", leadMeasurement.getTpdur());
		annotationMap.put("T' Area", leadMeasurement.getTparea());
		annotationMap.put("T'' Area", leadMeasurement.getTptparea());
		annotationMap.put("PR Interval", leadMeasurement.getPrint());
		annotationMap.put("PR Segment", leadMeasurement.getPrseg());
		annotationMap.put("QT Interval", leadMeasurement.getQtint());

		return annotationMap;
	}
	
	protected abstract Map<String, String> extractOrderInfo(OrderInfo orderInfo);
	
	protected abstract Map<String, String> extractDataAcquisition(DataAcquisition dataAnnotations);
	
	public Map<String, String> extractGroupMeasurements(GroupMeasurement groupMeasurement){
		
		log.info("Extracting Group Measurements for Philips");
		
		Map<String, String> annotationMap = new HashMap<String, String>();
		
		annotationMap.put("Member Count", groupMeasurement.getMembercount());
		annotationMap.put("Member Percent", groupMeasurement.getMemberpercent());
		annotationMap.put("Longest Run", groupMeasurement.getLongestrun());
		annotationMap.put("Mean QRS Duration", groupMeasurement.getMeanqrsdur());
		annotationMap.put("Minimum Ventricular Rate", groupMeasurement.getLowventrate());
		annotationMap.put("Mean Ventricular Rate", groupMeasurement.getMeanventrate());
		annotationMap.put("Maximum Ventricular Rate", groupMeasurement.getHighventrate());
		annotationMap.put("Ventricular Rate Standard Deviation", groupMeasurement.getVentratestddev());
		annotationMap.put("Mean RR Interval", groupMeasurement.getMeanrrint());
		annotationMap.put("Atrial Rate", groupMeasurement.getAtrialrate());
		annotationMap.put("Atrial Rate Standard Deviation", groupMeasurement.getAtrialratestddev());
		annotationMap.put("avgpcount", groupMeasurement.getAvgpcount());
		annotationMap.put("notavgbeats", groupMeasurement.getNotavgpbeats());
		annotationMap.put("Minimum PR Interval", groupMeasurement.getLowprint());
		annotationMap.put("Mean PR Interval", groupMeasurement.getMeanprint());
		annotationMap.put("High PR Interval", groupMeasurement.getHighprint());
		annotationMap.put("PR Interval Standard Deviation", groupMeasurement.getPrintstddev());
		annotationMap.put("Mean PR Segment", groupMeasurement.getMeanprseg());
		annotationMap.put("Mean QT Interval", groupMeasurement.getMeanqtint());
		annotationMap.put("Mean QT Segment", groupMeasurement.getMeanqtseg());
		
		return completeGroupMeasurementsExtraction(annotationMap, groupMeasurement);
	}
	
	protected abstract Map<String, String> completeGroupMeasurementsExtraction(Map<String, String> annotationMap, GroupMeasurement annotation);

}
