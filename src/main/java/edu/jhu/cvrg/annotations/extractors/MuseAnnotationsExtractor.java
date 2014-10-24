package edu.jhu.cvrg.annotations.extractors;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
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
import edu.jhu.cvrg.annotations.wrapper.muse.QRSTime;

public class MuseAnnotationsExtractor extends AnnotationsExtractor {

	private Element restingECGElement;
	private Element qrsTimeTypesElement;
	private List waveformElements;
	
	private static Map<String, String> RESTING_ECG_TAG2EXTRACT;
	private static Map<String, String> WAVEFORM_TAG2EXTRACT;
	private static Map<String, String> LEAD_TAG2EXTRACT;
	
	
	private Map<Integer, Map<String, String>> leadAnnotations = new HashMap<Integer, Map<String, String>>();
	private Map<String, String> globalAnnotations = new HashMap<String, String>();
	
	static {
		RESTING_ECG_TAG2EXTRACT = new HashMap<String, String>();
		RESTING_ECG_TAG2EXTRACT.put("SystolicBP", 		"Systolic_Blood_Pressure");
		RESTING_ECG_TAG2EXTRACT.put("DiastolicBP", 		"Diastolic_Blood_Pressure");
		RESTING_ECG_TAG2EXTRACT.put("VentricularRate", 	"Ventricular_Rate");
		RESTING_ECG_TAG2EXTRACT.put("AtrialRate", 		"Atrial_Rate");
		RESTING_ECG_TAG2EXTRACT.put("PRInterval", 		"PR_Interval");
		RESTING_ECG_TAG2EXTRACT.put("QRSDuration", 		"QRS_Duration");
		RESTING_ECG_TAG2EXTRACT.put("QTInterval", 		"QT_Interval");
		RESTING_ECG_TAG2EXTRACT.put("QTCorrected", 		"QT_Corrected");
		RESTING_ECG_TAG2EXTRACT.put("PAxis", 			"P_Frontal_Axis");
		RESTING_ECG_TAG2EXTRACT.put("RAxis", 			"R_Frontal_Axis");
		RESTING_ECG_TAG2EXTRACT.put("TAxis", 			"T_Frontal_Axis");
		RESTING_ECG_TAG2EXTRACT.put("QRSCount", 		"QRS_Count");
		RESTING_ECG_TAG2EXTRACT.put("QOnset", 			"Q_Onset");
		RESTING_ECG_TAG2EXTRACT.put("POnset", 			"P_Onset");
		RESTING_ECG_TAG2EXTRACT.put("POffset", 			"P_Offset");
		RESTING_ECG_TAG2EXTRACT.put("TOffset", 			"T_Offset");
		RESTING_ECG_TAG2EXTRACT.put("ECGSampleBase", 	"ECG_Sample_Base");
		RESTING_ECG_TAG2EXTRACT.put("ECGSampleExponent","ECG_Sample_Exponent");
		RESTING_ECG_TAG2EXTRACT.put("QTcFrederica", 	"QT_Corrected_Fridericias_Formula");
		
		WAVEFORM_TAG2EXTRACT = new HashMap<String, String>();
		WAVEFORM_TAG2EXTRACT.put("WaveformType", 		"Waveform_Type");
		WAVEFORM_TAG2EXTRACT.put("WaveformStartTime", 	"Waveform_Start_Time");
		WAVEFORM_TAG2EXTRACT.put("SampleType", 			"Sample_Type");
		WAVEFORM_TAG2EXTRACT.put("SampleBase", 			"Sample_Base");
		WAVEFORM_TAG2EXTRACT.put("SampleExponent", 		"Sample_Exponent");
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
		
	}
	
	public MuseAnnotationsExtractor(String xmlInput) {
		try {
			Document xmlDoc  = buildDOM(xmlInput);
		
			restingECGElement = xmlDoc.getRootElement().getChild("RestingECGMeasurements");
			if(restingECGElement == null) {
				throw new JDOMException("Unable to parse the RestingECGMeasuremens element!");
			}
			
			qrsTimeTypesElement = xmlDoc.getRootElement().getChild("QRSTimesTypes");
			if(qrsTimeTypesElement == null) {
				throw new JDOMException("Unable to parse the QRSTimesTypes element!");
			}
			
			waveformElements = xmlDoc.getRootElement().getChildren("Waveform");
			if(waveformElements.isEmpty()) {
				throw new JDOMException("Unable to find any Waveform elements of any kind!");
			}
			
		} catch (JDOMException e) {
			e.printStackTrace();
		}
	}
	
	public void extractAll(){
		globalAnnotations.putAll(extractRestingECGAnnotation());
		globalAnnotations.putAll(extractWaveformMetadata());
	}

	
	private Map<String, String> extractRestingECGAnnotation() {
		
		LinkedHashMap<String, String> annotationMap = new LinkedHashMap<String, String>();
		
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
		return annotationMap;
	}
	/**
	 * ACTUALLY NOT USED
	 * */
	private void extractQRSTimesTypes() {
		
		List<QRSTime> qrsTimes = new ArrayList<QRSTime>();
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
	
	
	private LinkedHashMap<String, String> extractWaveformMetadata() {
		
		LinkedHashMap<String, String> annotationMap = new LinkedHashMap<String, String>();
		
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
