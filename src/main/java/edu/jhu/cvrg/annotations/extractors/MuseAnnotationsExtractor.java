package edu.jhu.cvrg.annotations.extractors;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import edu.jhu.cvrg.annotations.utilities.LeadEnum;
import edu.jhu.cvrg.annotations.utilities.exceptions.AnnotationExtractorException;
import edu.jhu.cvrg.annotations.wrapper.muse.QRSTime;
import edu.jhu.icm.parser.Base64;

public class MuseAnnotationsExtractor extends AnnotationsExtractor {

	private Element restingECGElement;
	private Element qrsTimeTypesElement;
	private List waveformElements;
	private Element measurementMatrixElement;
	
	private static Map<String, String> RESTING_ECG_TAG2EXTRACT;
	private static Map<String, String> WAVEFORM_TAG2EXTRACT;
	private static Map<String, String> LEAD_TAG2EXTRACT;
	
	private static Map<Integer, String> GLOBAL_MEASUREMENTS;
	private static Map<Integer, String> PER_LEAD_MEASUREMENT;
	//I, II, V1, V2, V3, V4, V5, V6, III, AVR,AVL, AVF
	private static LeadEnum[] MEASUREMENT_LEAD_ORDER = {LeadEnum.I, LeadEnum.II, LeadEnum.V1, LeadEnum.V2, LeadEnum.V3, LeadEnum.V4, LeadEnum.V5, LeadEnum.V6, LeadEnum.III, LeadEnum.aVR, LeadEnum.aVL, LeadEnum.aVF};
	
	
	private Map<Integer, Map<String, String>> leadAnnotations = new HashMap<Integer, Map<String, String>>();
	private Map<String, String> globalAnnotations = new HashMap<String, String>();
	
	static {
		RESTING_ECG_TAG2EXTRACT = new HashMap<String, String>();
		RESTING_ECG_TAG2EXTRACT.put("SystolicBP", 		"Systolic_Blood_Pressure");
		RESTING_ECG_TAG2EXTRACT.put("DiastolicBP", 		"Diastolic_Blood_Pressure");
		RESTING_ECG_TAG2EXTRACT.put("VentricularRate", 	"Mean_Ventricular_Rate");
		RESTING_ECG_TAG2EXTRACT.put("AtrialRate", 		"Atrial_Rate");
		RESTING_ECG_TAG2EXTRACT.put("PRInterval", 		"PR_Interval");
		RESTING_ECG_TAG2EXTRACT.put("QRSDuration", 		"QRS_Wave_Duration");
		RESTING_ECG_TAG2EXTRACT.put("QTInterval", 		"QT_Interval");
		RESTING_ECG_TAG2EXTRACT.put("QTCorrected", 		"QT_Corrected");
		RESTING_ECG_TAG2EXTRACT.put("PAxis", 			"P_Frontal_Axis");
		RESTING_ECG_TAG2EXTRACT.put("RAxis", 			"R_Frontal_Axis");
		RESTING_ECG_TAG2EXTRACT.put("TAxis", 			"T_Frontal_Axis");
		RESTING_ECG_TAG2EXTRACT.put("QRSCount", 		"QRS_Count");
		RESTING_ECG_TAG2EXTRACT.put("QOnset", 			"Q_Wave_Onset");
		RESTING_ECG_TAG2EXTRACT.put("POnset", 			"P_Wave_Onset");
		RESTING_ECG_TAG2EXTRACT.put("POffset", 			"P_Wave_Offset");
		RESTING_ECG_TAG2EXTRACT.put("TOffset", 			"T_Wave_Offset");
		RESTING_ECG_TAG2EXTRACT.put("ECGSampleBase", 	"ECG_Sample_Base");
		RESTING_ECG_TAG2EXTRACT.put("ECGSampleExponent","ECG_Sample_Exponent");
		RESTING_ECG_TAG2EXTRACT.put("QTcFrederica", 	"QT_Corrected_Fridericias_Formula");
		
		WAVEFORM_TAG2EXTRACT = new HashMap<String, String>();
		WAVEFORM_TAG2EXTRACT.put("WaveformType", 		"Waveform_Type");
		WAVEFORM_TAG2EXTRACT.put("WaveformStartTime", 	"Waveform_Start_Time");
		WAVEFORM_TAG2EXTRACT.put("SampleType", 			"Waveform_Sample_Type");
		WAVEFORM_TAG2EXTRACT.put("SampleBase", 			"Waveform_Sample_Base");
		WAVEFORM_TAG2EXTRACT.put("SampleExponent", 		"Waveform_Sample_Exponent");
		WAVEFORM_TAG2EXTRACT.put("HighPassFilter", 		"High_Pass_Filter");
		WAVEFORM_TAG2EXTRACT.put("LowPassFilter", 		"Low_Pass_Filter");
		
		LEAD_TAG2EXTRACT = new HashMap<String, String>();
		LEAD_TAG2EXTRACT.put("LeadByteCountTotal", 		"Lead_Byte_Count_Total");
		LEAD_TAG2EXTRACT.put("LeadTimeOffset", 			"Lead_Time_Offset");
		LEAD_TAG2EXTRACT.put("LeadAmplitudeUnitsPerBit","Lead_Amplitude_Units_Per_Bit");
		LEAD_TAG2EXTRACT.put("LeadAmplitudeUnits", 		"Lead_Amplitude_Units");
		LEAD_TAG2EXTRACT.put("LeadHighLimit", 			"Lead_High_Limit");
		LEAD_TAG2EXTRACT.put("LeadLowLimit", 			"Lead_Low_Limit");
		LEAD_TAG2EXTRACT.put("LeadOffsetFirstSample", 	"Lead_Offset_First_Sample");
		LEAD_TAG2EXTRACT.put("LeadOff", 				"Lead_Offset");
		LEAD_TAG2EXTRACT.put("BaselineSway", 			"Baseline_Sway");
		LEAD_TAG2EXTRACT.put("ExcessiveACNoise", 		"Excessive_AC_Noise");
		LEAD_TAG2EXTRACT.put("MuscleNoise", 			"Muscle_Noise");
		LEAD_TAG2EXTRACT.put("LeadDataCRC32", 			"Lead_Data_CRC_32");
		
		GLOBAL_MEASUREMENTS = new HashMap<Integer, String>();
		GLOBAL_MEASUREMENTS.put(Integer.valueOf(1), "P-wave onset in median beat (in samples)"); 
		GLOBAL_MEASUREMENTS.put(Integer.valueOf(2), "P-wave offset in median beat"); 
		GLOBAL_MEASUREMENTS.put(Integer.valueOf(3), "Q-Onset in median beat"); 
		GLOBAL_MEASUREMENTS.put(Integer.valueOf(4), "Q-Offset in median beat"); 
		GLOBAL_MEASUREMENTS.put(Integer.valueOf(5), "T-Onset in median beat"); 
		GLOBAL_MEASUREMENTS.put(Integer.valueOf(6), "T-Offset in median beat"); 
		GLOBAL_MEASUREMENTS.put(Integer.valueOf(7), "Number of QRS Complexes"); 
		GLOBAL_MEASUREMENTS.put(Integer.valueOf(8), "QRS Duration"); 
		GLOBAL_MEASUREMENTS.put(Integer.valueOf(9), "QT Interval"); 
		GLOBAL_MEASUREMENTS.put(Integer.valueOf(10), "QT Corrected"); 
		GLOBAL_MEASUREMENTS.put(Integer.valueOf(11), "PR Interval"); 
		GLOBAL_MEASUREMENTS.put(Integer.valueOf(12), "Ventricular Rate"); 
		GLOBAL_MEASUREMENTS.put(Integer.valueOf(13), "Average R-R Interval"); 
		
		PER_LEAD_MEASUREMENT = new HashMap<Integer, String>();
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(1), "P Wave amplitude at P-onset"); //PONA 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(2), "P wave amplitude"); //PAMP 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(3), "P wave duration"); //PDUR 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(4), "P wave area"); //bmPAR 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(5), "P wave intrinsicoid (time from P onset to peak of P)"); //bmPI 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(6), "P Prime amplitude"); //P'AMP 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(7), "P Prime duration"); //P'DUR 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(8), "P Prime area"); //bmPPAR 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(9), "P Prime intrinsicoid (time from P onset to peak of P')"); //bmPPI 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(10), "Q wave amplitude"); //QAMP
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(11), "Q wave duration"); //QDUR 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(12), "Q wave area"); //bmQAR 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(13), "Q intrinsicoid (time from Q onset to peak of Q)"); //bmQI 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(14), "R amplitude"); //RAMP
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(15), "R duration"); //RDUR 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(16), "R wave area"); //bmRAR 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(17), "R intrinsicoid (time from R onset to peak of R)"); //bmRI 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(18), "S amplitude"); //SAMP
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(19), "S duration"); //SDUR 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(20), "S wave area"); //bmSAR 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(21), "S intrinsicoid (time from Q onset to peak of S)"); //bmSI 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(22), "R Prime amplitude"); //R'AMP
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(23), "R Prime duration"); //R'DUR 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(24), "R Prime wave area"); //bmRPAR 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(25), "R Prime intrinsicoid (time from Q onset to peak of R Prime)"); //bmRPI 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(26), "S Prime Amplitude"); //S'AMP
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(27), "S Prime Duration"); //S'DUR 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(28), "S Prime wave area"); //bmSPAR 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(29), "S intriniscoid (time from Q onset to peak of S prime)"); //bmSPI 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(30), "STJ point, End of QRS Point Amplitude"); //STJ
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(31), "STM point, Middle of the ST Segment Amplitude"); //STM 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(32), "STE point, End of ST Segment Amplitude"); //STE 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(33), "Maximum of STJ, STM, STE Amplitudes"); //MXSTA 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(34), "Minimum of STJ and STM Amplitudes"); //MNSTA 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(35), "Special T-Wave amplitude"); //SPTA 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(36), "Total QRS area"); //QRSA 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(37), "QRS Deflection"); //QRSDEF 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(38), "Maximum R Amplitude (R or R Prime)"); //MAXRA 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(39), "Maximum S Amplitude (S or S Prime)"); //MAXSA 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(40), "T amplitude"); //TAMP
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(41), "T duration"); //TDUR 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(42), "T wave area"); //bmTAR 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(43), "T intriniscoid (time from STE to peak of T)"); //bmTI 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(44), "T Prime amplitude"); //T'AMP
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(45), "T Prime duration"); //TPDUR 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(46), "T Prime area"); //bmTPAR 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(47), "T Prime intriniscoid (time from STE to peak of T)"); //bmTPI 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(48), "T Amplitude at T offset"); //TEND
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(49), "P wave area, includes P and P Prime"); //PAREA 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(50), "QRS area"); //QRSAR
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(51), "T wave area, include T and T Prime"); //TAREA 
		PER_LEAD_MEASUREMENT.put(Integer.valueOf(52), "QRS intriniscoid (see below)"); //QRSINT
//		PER_LEAD_MEASUREMENT.put(Integer.valueOf(53-0), ""); //[53]BITFLG \\ Bitmask sum of (values) decoded as follows:
//		PER_LEAD_MEASUREMENT.put(Integer.valueOf(53-1), ""); //Bit 1 (2) :TTAL- Peak of T > ST measurement
//		PER_LEAD_MEASUREMENT.put(Integer.valueOf(53-2), ""); //Bit 2 (4) :STDOWN- ST Segment Depressed
//		PER_LEAD_MEASUREMENT.put(Integer.valueOf(53-3), ""); //Bit 3 (8) :STELEV- ST Segment Elevated
//		PER_LEAD_MEASUREMENT.put(Integer.valueOf(53-4), ""); //Bit 4 (16) :JELEV- J point Elevated by 100uV
//		PER_LEAD_MEASUREMENT.put(Integer.valueOf(53-5), ""); //Bit 5 (32) :DLTWV- Delta-Wave Detected
//		PER_LEAD_MEASUREMENT.put(Integer.valueOf(53-6), ""); //Bit 6 (64) :STINJ- ST Segment Elevated
//		PER_LEAD_MEASUREMENT.put(Integer.valueOf(53-7), ""); //Bit 7 (128):PPDEEP- P Prime Area was 1000uV*ms
		
	}
	
	public MuseAnnotationsExtractor(String xmlInput) throws AnnotationExtractorException{
		try {
			Document xmlDoc  = buildDOM(xmlInput);
		
			restingECGElement = xmlDoc.getRootElement().getChild("RestingECGMeasurements");
			
			qrsTimeTypesElement = xmlDoc.getRootElement().getChild("QRSTimesTypes");
			
			waveformElements = xmlDoc.getRootElement().getChildren("Waveform");
			
			measurementMatrixElement = xmlDoc.getRootElement().getChild("MeasurementMatrix");
			
		} catch (JDOMException e) {
			throw new AnnotationExtractorException(e.getMessage(), e);
		}
	}
	
	public void extractAll(){
		globalAnnotations.putAll(extractRestingECGAnnotation());
		globalAnnotations.putAll(extractWaveformMetadata());
		try {
			extractMeasurementMatrix();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	private Map<String, String> extractRestingECGAnnotation() {
		
		LinkedHashMap<String, String> annotationMap = new LinkedHashMap<String, String>();
		
		if(restingECGElement != null){
			// get each child element and store it in the appropriate variable
			List allChildren = restingECGElement.getChildren();
			
			if(!(allChildren.isEmpty())) {
				for (Object child : allChildren) {
					Element childElement = (Element) child;
				    if(childElement != null) {	
				    	// determine which tag it is and add it to the appropriate variable
				    	if(RESTING_ECG_TAG2EXTRACT.containsKey(childElement.getName())){
				    		String value = childElement.getText();
				    		if(value != null){
				    			annotationMap.put(RESTING_ECG_TAG2EXTRACT.get(childElement.getName()), value);
				    		}
				    	}
				    }
				}
			}
		}
		
		return annotationMap;
	}
	/**
	 * ACTUALLY NOT USED
	 * */
	private void extractQRSTimesTypes() {
		
		List<QRSTime> qrsTimes = new ArrayList<QRSTime>();
		
		if(qrsTimeTypesElement != null){
			String globalRR, qtrGGR;
			
			// get each child element and store it in the appropriate variable
			List allChildren = qrsTimeTypesElement.getChildren();
			
			if(!(allChildren.isEmpty())) {
				for (Object child : allChildren) {
					Element childElement = (Element) child;
				    if(childElement != null) {	
				    	// determine which tag it is and add it to the appropriate variable
						if(childElement.getName().equals("QRS")) {
							// TODO:  Capture the QRS data types
							List qrsDataMembers = childElement.getChildren();
							for(Object qrsData : qrsDataMembers) {
								Element qrsElement = (Element) qrsData;
								QRSTime newQRS = new QRSTime(qrsElement.getChildText("Number"), qrsElement.getChildText("Type"), qrsElement.getChildText("Time"));
								qrsTimes.add(newQRS);
							}
						}
						else if(childElement.getName().equals("GlobalRR")) {
							globalRR = childElement.getText();
						}
						else if(childElement.getName().equals("QTRGGR")) {
							qtrGGR = childElement.getText();
						}
				    }
				}
			}
		}
	}
	
	private void extractMeasurementMatrix() throws IOException{
		
		if(measurementMatrixElement != null){
			String base64String = measurementMatrixElement.getText();
			
			if(base64String != null){
				byte[] encodedBytes = base64String.getBytes();
				
				byte[] uncodedDataByte = Base64.decode( encodedBytes );
				ByteBuffer bb = ByteBuffer.allocate(2);
				bb.order(ByteOrder.nativeOrder());
				int globalIndex = 0;
				for (int t = 0, len = uncodedDataByte.length; t < len; t+=2) {
					if(globalIndex < 18){
						double doubleVal = (double)(((uncodedDataByte[t+1])<<8) | (uncodedDataByte[t] & 0xFF));
						//doubleVal = doubleVal * 4.88;
						if(GLOBAL_MEASUREMENTS.containsKey(globalIndex)){
							globalAnnotations.put(GLOBAL_MEASUREMENTS.get(globalIndex), String.valueOf(doubleVal));
						}
						globalIndex++;
					}else{
						Integer leadId = (int)(uncodedDataByte[t] & 0xFF);
						Integer measurementId = (int)(uncodedDataByte[t+1] & 0xFF);
						double doubleVal = (double)(((uncodedDataByte[t+3])<<8) | (uncodedDataByte[t+2] & 0xFF));
						t+=2;
						
						Map<String, String> leadMap = leadAnnotations.get(MEASUREMENT_LEAD_ORDER[leadId].ordinal());
						
						if(leadMap == null){
							leadAnnotations.put(MEASUREMENT_LEAD_ORDER[leadId].ordinal(), new HashMap<String, String>());
						}
						
						if(PER_LEAD_MEASUREMENT.containsKey(measurementId)){
							leadMap.put(PER_LEAD_MEASUREMENT.get(measurementId), String.valueOf(doubleVal));
						}
					}
				}
			}
		}
	}
	
	
	private LinkedHashMap<String, String> extractWaveformMetadata() {
		
		LinkedHashMap<String, String> annotationMap = new LinkedHashMap<String, String>();

		if(waveformElements != null){
		
			Element waveform = null;
			Iterator waveformIter = waveformElements.iterator();
			while(waveformIter.hasNext()) {
				waveform = (Element)waveformIter.next();
				Element waveformType = waveform.getChild("WaveformType");
				
				// Check to make sure there are valid waveforms, then send the Waveform tag to the appropriate object for parsing
				if((waveformType != null) && (waveformType.getText().equals("Rhythm"))) {
					break;
				}
			}
			
			if(waveform != null){
				ArrayList<String> ACFilters = new ArrayList<String>();
				
				// get each child element and store it in the appropriate variable
				List allChildren = waveform.getChildren();
				
				if(!(allChildren.isEmpty())) {
					for (Object child : allChildren) {
						Element childElement = (Element) child;
					    if(childElement != null) {	
					    	
					    	if(WAVEFORM_TAG2EXTRACT.containsKey(childElement.getName())){
					    		String value = childElement.getText();
					    		if(value != null){
					    			annotationMap.put(WAVEFORM_TAG2EXTRACT.get(childElement.getName()), value);
					    		}
					    		
					    	}else if(childElement.getName().equals("ACFilter")) {
								// TODO:  Get each AC Filter and add it to the list of total AC Filters
								ACFilters.add(childElement.getText());
								
							}else if(childElement.getName().equals("LeadData")) {
								// TODO:  Get a specific Lead and then add it to the LeadData hashmap
								this.extractLeadData(childElement);
							}else if(childElement.getName().equals("PaceSpikes")) {
								// TODO:  Get everything under the PaceSpikes element and place it in an object
								// TODO:  Figure out a way to handle this complex type
							}
					    }
					}
				}
				
				if(!(ACFilters.isEmpty())) {
					int counter = 1;
					
					for(String filter : ACFilters) {
						annotationMap.put("AC_Filter #" + counter, filter);
					}
				}
			}
		}
		
		return annotationMap;
	}
	
	
	
	private void extractLeadData(Element leadData) {
		Integer leadID = null;
		
		LinkedHashMap<String, String> singleLeadAnnotationMap = new LinkedHashMap<String, String>();
		
		// get each child element and store it in the appropriate variable
		List allChildren = leadData.getChildren();
				
		if(!(allChildren.isEmpty())) {
			for (Object child : allChildren) {
				Element childElement = (Element) child;
			    if(childElement != null) {	
				    	// determine which tag it is and add it to the appropriate variable
			    	if(LEAD_TAG2EXTRACT.containsKey(childElement.getName())){
			    		String value = childElement.getText();
			    		if(value != null){
			    			singleLeadAnnotationMap.put(LEAD_TAG2EXTRACT.get(childElement.getName()), value);
			    		}
			    		
			    	}else if(childElement.getName().equals("LeadID")) {
						leadID = LeadEnum.valueOf(childElement.getText()).ordinal();
					}
				}
			}
		}
		
		this.leadAnnotations.put(leadID, singleLeadAnnotationMap);
	}
	
	/**
	 * Helper method to build a <code>jdom.org.Document</code> from an 
	 * XML document represented as a String
	 * @param  xmlDocAsString  <code>String</code> representation of an XML
	 *         document with a document declaration.
	 *         e.g., <?xml version="1.0" encoding="UTF-8"?>
	 *                  <root><stuff>Some stuff</stuff></root>
	 * @return Document from an XML document represented as a String
	 */
	public static Document buildDOM(String xmlDocAsString) 
	        throws JDOMException {
		Document doc = null;
	    SAXBuilder builder = new SAXBuilder();
	    Reader stringreader = new StringReader(xmlDocAsString);
	    try {
	    	doc = builder.build(stringreader);
	    } catch(IOException ioex) {
	    	ioex.printStackTrace();
	    }
	    return doc;
	}

	public Map<Integer, Map<String, String>> getLeadAnnotations() {
		return leadAnnotations;
	}

	public Map<String, String> getGlobalAnnotations() {
		return globalAnnotations;
	}
}
