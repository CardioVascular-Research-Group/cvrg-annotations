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
		
		annotationMap.put("Pace_Mode_Alpha", globalMeasurements.getPacemalf());
		annotationMap.put("Pace_Mode_Ischemic", globalMeasurements.getPacemisc());
		annotationMap.put("Ectopic Rhythm", globalMeasurements.getEctopicrhythm());
		annotationMap.put("QT_Dispersion", globalMeasurements.getQtintdispersion());
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
		annotationMap.put("Atrial_Rate", globalMeasurements.getAtrialrate());
		annotationMap.put("Minimum_Ventricular_Rate", globalMeasurements.getLowventrate());
		annotationMap.put("Mean Ventricular Rate", globalMeasurements.getMeanventrate());
		annotationMap.put("Maximum Ventricular Rate", globalMeasurements.getHighventrate());
		annotationMap.put("PR_Interval", globalMeasurements.getMeanprint());
		annotationMap.put("PR_Segment", globalMeasurements.getMeanprseg());
		annotationMap.put("QRS_Wave_Duration", globalMeasurements.getMeanqrsdur());
		annotationMap.put("QT_Interval", globalMeasurements.getMeanqtint());
		annotationMap.put("QT_Corrected", globalMeasurements.getMeanqtc());
		annotationMap.put("Delta Wave Count", globalMeasurements.getDeltawavecount());
		annotationMap.put("Delta Wave Percent", globalMeasurements.getDeltawavepercent());
		annotationMap.put("Bigeminy_Premature_Ventricular_Contraction_Count", globalMeasurements.getBigeminycount());
		annotationMap.put("Bigeminy_Premature_Ventricular_Contraction_String", globalMeasurements.getBigeminystring());
		annotationMap.put("Trigeminy_Premature_Ventricular_Contraction_Count", globalMeasurements.getTrigeminycount());
		annotationMap.put("Trigeminy_Premature_Ventricular_Contraction_String", globalMeasurements.getTrigeminystring());
		annotationMap.put("Wenckebach_Count", globalMeasurements.getWenckcount());
		annotationMap.put("Wenckebach_String", globalMeasurements.getWenckstring());
		annotationMap.put("Atrial_Flutter_Fibrillation_Count", globalMeasurements.getFlutterfibcount());
		annotationMap.put("Beat_Classification", globalMeasurements.getBeatclassification());
		
	}

	protected void commomDataAcquisition(DataAcquisition dataAcquisition, Acquirer acquirer, SignalCharacteristics signalProperties, Map<String, String> annotationMap){
		
		annotationMap.put("Machine", dataAcquisition.getMachineId());
		annotationMap.put("Database", dataAcquisition.getDatabaseid());
		
		annotationMap.put("Acquirer Encounter ID", acquirer.getEncounterId());
		annotationMap.put("Acquirer Operator ID", acquirer.getOperatorId());
		annotationMap.put("Room", acquirer.getRoom());
		annotationMap.put("Department ID", acquirer.getDepartmentid());
		annotationMap.put("Department Name", acquirer.getDepartmentname());
		annotationMap.put("Institution ID", acquirer.getInstitutionid());
		annotationMap.put("Institution Name", acquirer.getInstitutionname());
		
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

		annotationMap.put("P_Wave_Amplitude", leadMeasurement.getPamp());
		annotationMap.put("P_Wave_Duration", leadMeasurement.getPdur());
		annotationMap.put("P_Wave_Area", leadMeasurement.getParea());
		annotationMap.put("PP_Wave_Amplitude", leadMeasurement.getPpamp());
		annotationMap.put("PP_Wave_Duration", leadMeasurement.getPpdur());
		annotationMap.put("PPP_Wave_Duration", leadMeasurement.getPpppdur());
		annotationMap.put("PP_Wave_Area", leadMeasurement.getPparea());
		annotationMap.put("PPP_Wave_Area", leadMeasurement.getPppparea());
		annotationMap.put("Q_Wave_Amplitude", leadMeasurement.getQamp());
		annotationMap.put("Q_Wave_Duration", leadMeasurement.getQdur());
		annotationMap.put("R_Wave_Amplitude", leadMeasurement.getRamp());
		annotationMap.put("R_Wave_Duration", leadMeasurement.getRdur());
		annotationMap.put("S_Wave_Amplitude", leadMeasurement.getSamp());
		annotationMap.put("S_Wave_Duration", leadMeasurement.getSdur());
		annotationMap.put("RR_Wave_Amplitude", leadMeasurement.getRpamp());
		annotationMap.put("RR_Wave_Duration", leadMeasurement.getRpdur());
		annotationMap.put("SS_Wave_Amplitude", leadMeasurement.getSpamp());
		annotationMap.put("SS_Wave_Duration", leadMeasurement.getSpdur());
		annotationMap.put("Ventricular Activation Time", leadMeasurement.getVat());
		annotationMap.put("QRS_Wave_Complex_Amplitude", leadMeasurement.getQrsppk());
		annotationMap.put("QRS_Wave_Complex_Duration", leadMeasurement.getQrsdur());
		annotationMap.put("QRS_Wave_Complex_Area", leadMeasurement.getQrsarea());
		annotationMap.put("ST Segment Onset", leadMeasurement.getSton());
		annotationMap.put("ST Segment Midpoint", leadMeasurement.getStmid());
		annotationMap.put("ST Segment Offset", leadMeasurement.getStend());
		annotationMap.put("ST_Segment_Duration", leadMeasurement.getStdur());
		annotationMap.put("ST Segment Slope", leadMeasurement.getStslope());
		
		if(leadMeasurement.getStshape() != null){
			annotationMap.put("ST Segment Shape", leadMeasurement.getStshape().toString());	
		}
		
		annotationMap.put("T_Wave_Amplitude", leadMeasurement.getTamp());
		annotationMap.put("T_Wave_Duration", leadMeasurement.getTdur());
		annotationMap.put("T_Wave_Area", leadMeasurement.getTarea());
		annotationMap.put("TT_Wave_Amplitude", leadMeasurement.getTpamp());
		annotationMap.put("TTT_Wave_Amplitude", leadMeasurement.getTptpdur());
		annotationMap.put("TT_Wave_Duration", leadMeasurement.getTpdur());
		annotationMap.put("TT_Wave_Area", leadMeasurement.getTparea());
		annotationMap.put("TTT_Wave_Area", leadMeasurement.getTptparea());
		annotationMap.put("PR_Interval", leadMeasurement.getPrint());
		annotationMap.put("PR_Segment", leadMeasurement.getPrseg());
		annotationMap.put("QT_Interval", leadMeasurement.getQtint());

		return annotationMap;
	}
	
	protected abstract Map<String, String> extractOrderInfo(OrderInfo orderInfo);
	
	protected abstract Map<String, String> extractDataAcquisition(DataAcquisition dataAnnotations);
	
	/**
	 * Unused
	 */
	public Map<String, String> extractGroupMeasurements(GroupMeasurement groupMeasurement){
		
		log.info("Extracting Group Measurements for Philips");
		
		Map<String, String> annotationMap = new HashMap<String, String>();
		
		annotationMap.put("Member Count", groupMeasurement.getMembercount());
		annotationMap.put("Member Percent", groupMeasurement.getMemberpercent());
		annotationMap.put("Longest Run", groupMeasurement.getLongestrun());
		//DUPLICATED = QRS_Wave_Duration
		annotationMap.put("Mean QRS Duration", groupMeasurement.getMeanqrsdur());
		//DUPLICATED = Minimum_Ventricular_Rate
		annotationMap.put("Minimum Ventricular Rate", groupMeasurement.getLowventrate());
		//DUPLICATED = Mean_Ventricular_Rate
		annotationMap.put("Mean Ventricular Rate", groupMeasurement.getMeanventrate());
		annotationMap.put("Maximum Ventricular Rate", groupMeasurement.getHighventrate());
		annotationMap.put("Ventricular Rate Standard Deviation", groupMeasurement.getVentratestddev());
		annotationMap.put("Mean RR Interval", groupMeasurement.getMeanrrint());
		annotationMap.put("Atrial Rate", groupMeasurement.getAtrialrate());
		annotationMap.put("Atrial Rate Standard Deviation", groupMeasurement.getAtrialratestddev());
		annotationMap.put("avgpcount", groupMeasurement.getAvgpcount());
		annotationMap.put("notavgbeats", groupMeasurement.getNotavgpbeats());
		annotationMap.put("Minimum PR Interval", groupMeasurement.getLowprint());
		//DUPLICATED = PR_Interval
		annotationMap.put("Mean PR Interval", groupMeasurement.getMeanprint());
		annotationMap.put("High PR Interval", groupMeasurement.getHighprint());
		annotationMap.put("PR Interval Standard Deviation", groupMeasurement.getPrintstddev());
		//DUPLICATED = PR_Segment
		annotationMap.put("Mean PR Segment", groupMeasurement.getMeanprseg());
		//DUPLICATED = QT_Interval
		annotationMap.put("Mean QT Interval", groupMeasurement.getMeanqtint());
		annotationMap.put("Mean QT Segment", groupMeasurement.getMeanqtseg());
		
		return completeGroupMeasurementsExtraction(annotationMap, groupMeasurement);
	}
	
	protected abstract Map<String, String> completeGroupMeasurementsExtraction(Map<String, String> annotationMap, GroupMeasurement annotation);

}
