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

import edu.jhu.cvrg.services.broker.annotations.utilities.interfaces.*;

public abstract class PhilipsAnnotationsExtractor extends AnnotationsExtractor {
	
	protected Logger log = Logger.getLogger(PhilipsAnnotationsExtractor.class);

	public LinkedHashMap<String, Object> extractLeadMeasurements(LeadMeasurement leadMeasurement){
		
		log.info("Extracting Lead Elements for Philips");
			
		LinkedHashMap<String, Object> annotationMap = new LinkedHashMap<String, Object>();

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
		annotationMap.put("ST Segment Shape", leadMeasurement.getStshape());
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
	
	protected abstract LinkedHashMap<String, Object> extractOrderInfo(OrderInfo orderInfo);
	
	protected abstract LinkedHashMap<String, Object> extractDataAcquisition(DataAcquisition dataAnnotations);
	
	public LinkedHashMap<String, Object> extractGroupMeasurements(GroupMeasurement groupMeasurement){
		
		log.info("Extracting Group Measurements for Philips");
		
		LinkedHashMap<String, Object> annotationMap = new LinkedHashMap<String, Object>();
		
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
	
	protected abstract LinkedHashMap<String, Object> completeGroupMeasurementsExtraction(LinkedHashMap<String, Object> annotationMap, GroupMeasurement annotation);

}
