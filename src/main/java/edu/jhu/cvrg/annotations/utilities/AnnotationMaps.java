package edu.jhu.cvrg.annotations.utilities;
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
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AnnotationMaps {

	private static Map<String, Integer> dynamicLeadNames = new ConcurrentHashMap<String, Integer>(){
		
		private static final long serialVersionUID = 6205600584630757507L;
			{
				/*put("I", 0);
				put("II", 1);
				put("III", 2);
				put("aVR", 3);
				put("aVL", 4);
				put("aVF", 5);
				put("V1", 6);
				put("V2", 7);
				put("V3", 8);
				put("V4", 9);
				put("V5", 10);
				put("V6", 11);
				put("VX", 12);
				put("VY", 13);
				put("VZ", 14);*/
	        }
    };
    
    private static final Map<String, String> schilAnoMap = new HashMap<String, String>(){
		private static final long serialVersionUID = 6205600584630757507L;
			{
				put("AXIS_QRS", "QRS_Wave_Complex_Axis");
				put("QRS", 		"QRS_Wave_Duration");
				put("QT", 		"QT_Interval");
				put("QTC", 		"QT_Corrected");
				put("Q_DUR", 	"Q_Wave_Duration");
				put("Q_AMPL", 	"Q_Wave_Amplitude");
				put("R_DUR", 	"R_Wave_Duration");
				put("R_AMPL", 	"R_Wave_Amplitude");
				put("S_DUR", 	"S_Wave_Duration");
				put("S_AMPL", 	"S_Wave_Amplitude");
				
				put("BP_D", 		"Blood_Pressure_Diastolic");
				put("BP_S", 		"Bigeminy_Premature_Ventricular_Contraction_String");
				put("HR", 			"Heart_Rate");
				put("PQ", 			"PR_Interval");
				put("AXIS_P", 		"P_Wave_Axis");
				put("P", 			"P_Wave");
				put("P_OFF", 		"P_Wave_Offset");
				put("P_ON", 		"P_Wave_Onset");
				put("QRS_OFF", 		"QRS_Wave_Complex_Offset");
				put("QRS_ON", 		"QRS_Wave_Complex_Onset");
				put("RR", 			"RR_Interval");
				put("T_OFF", 		"T_Wave_Offset");
				put("P_NEG_AMPL", 	"PP_Wave_Amplitude");
				put("P_POS_AMPL", 	"P_Wave_Amplitude");
				put("R-_AMPL", 		"RR_Wave_Amplitude");
				put("R-_DUR", 		"RR_Wave_Duration");
				put("S-_AMPL", 		"SS_Wave_Amplitude");
				put("S-_DUR", 		"SS_Wave_Duration");
				put("ST_INTEG", 	"ST_Segment_Duration");
				put("T_NEG_AMPL", 	"TT_Wave_Amplitude");
				put("T_POS_AMPL", 	"T_Wave_Amplitude");
				
	        }
    };

    private static final Map<String, String> ecgOntoMap = new HashMap<String, String>(){
		private static final long serialVersionUID = 3239200211633843945L;
			{
				put("PR_Interval", "#ECG_000000341");
				put("QT_Interval", "#ECG_000000682");
				put("QT_Corrected", "#ECG_000000701");
				put("QT_Corrected_Fridericias_Formula", "#ECG_000000040");
				put("Q_Wave_Duration", "#ECG_000000551");
				put("Q_Wave_Amplitude", "#ECG_000000652");
				put("R_Wave_Amplitude", "#ECG_000000750");
				put("R_Wave_Duration", "#ECG_000000597");
				put("S_Wave_Amplitude", "#ECG_000000107");
				put("S_Wave_Duration", "#ECG_000000491");
				put("QRS_Wave_Duration", "#ECG_000000072");
				put("QRS_Wave_Complex_Axis", "#ECG_000000838");
	        }
    };

	public static Map<String, Integer> getDynamicLeadNames() {
		return dynamicLeadNames;
	}

	public static Map<String, String> getSchillerAnnotationMap() {
		return schilAnoMap;
	}

	public static Map<String, String> getEcgOntologyMap() {
		return ecgOntoMap;
	}
    
}