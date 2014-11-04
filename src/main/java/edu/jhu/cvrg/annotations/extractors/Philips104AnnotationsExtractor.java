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
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.cvrgrid.philips.jaxb.beans.Acquirer;
import org.cvrgrid.philips.jaxb.beans.Crossleadmeasurements;
import org.cvrgrid.philips.jaxb.beans.Drgcategories;
import org.cvrgrid.philips.jaxb.beans.Drgcategory;
import org.cvrgrid.philips.jaxb.beans.Orderinfo;
import org.cvrgrid.philips.jaxb.beans.Orderinfo.Other;
import org.cvrgrid.philips.jaxb.beans.Signalcharacteristics;
import org.cvrgrid.philips.jaxb.beans.TYPEmessagecode;

import edu.jhu.cvrg.annotations.utilities.interfaces.DataAcquisition;
import edu.jhu.cvrg.annotations.utilities.interfaces.GroupMeasurement;
import edu.jhu.cvrg.annotations.utilities.interfaces.OrderInfo;
import edu.jhu.cvrg.annotations.wrapper.philips.Philips104Acquirer;
import edu.jhu.cvrg.annotations.wrapper.philips.Philips104GlobalMeasurements;
import edu.jhu.cvrg.annotations.wrapper.philips.Philips104SignalCharacteristics;

public class Philips104AnnotationsExtractor extends PhilipsAnnotationsExtractor {
	
	private Logger log = Logger.getLogger(Philips104AnnotationsExtractor.class);

	public Map<String, String> extractGlobalElements(Crossleadmeasurements crossLeadMeasurements) {
		
		log.info("Extracting Global Elements for Philips 104");
		
		Map<String, String> annotationMap = new HashMap<String, String>();
		
		// The mapping will need to be filled manually since currently we cannot get a list of all of the elements
		// Do not do complex annotation measurements yet.  Stick with the simple ones.
		
		// TODO:  Skipping pacedetectleads and pacepulses for now, return to them later.
		
		// Main set of cross lead measurements
		annotationMap.put("pacemode", crossLeadMeasurements.getPacemode());
		
		commomGlobalElements(new Philips104GlobalMeasurements(crossLeadMeasurements), annotationMap);
		
		int subscript = 1;
		// enter the qamessagecodes one by one, as that is how they are in the XML
		for(TYPEmessagecode mCode : crossLeadMeasurements.getQamessagecodes().getQamessagecode()) {
			if(mCode != null) {
				String messageName = "qamessagecode" + subscript;
				annotationMap.put(messageName, mCode.value());
				subscript++;
			}
		}
		
		
		
		if(crossLeadMeasurements.getLeadreversalcode() != null){
			annotationMap.put("leadreversalcode", crossLeadMeasurements.getLeadreversalcode().toString());
		}else{
			annotationMap.put("leadreversalcode", null);
		}
		
		if(crossLeadMeasurements.getAtrialrhythm() != null){
			annotationMap.put("Atrial Rhythm", crossLeadMeasurements.getAtrialrhythm().toString());
		}else{
			annotationMap.put("Atrial Rhythm", null);
		}
		
		annotationMap.put("atrialratepowerspect", crossLeadMeasurements.getAtrialratepowerspect());
		annotationMap.put("randomrrpercent", crossLeadMeasurements.getRandomrrpercent());
		annotationMap.put("regularrrpercent", crossLeadMeasurements.getRegularrrpercent());
		annotationMap.put("biggestrrgrouppercent", crossLeadMeasurements.getBiggestrrgrouppercent());
		annotationMap.put("bigemrrintvlacf", crossLeadMeasurements.getBigemrrintvlacf());
		annotationMap.put("trigemrrintvlacf", crossLeadMeasurements.getTrigemrrintvlacf());
		
		if(crossLeadMeasurements.getVentrhythm() != null){
			annotationMap.put("Ventricular Rhythm", crossLeadMeasurements.getVentrhythm().toString());	
		}else{
			annotationMap.put("Ventricular Rhythm", null);
		}
		if(crossLeadMeasurements.getBiggestrrgroupvar() != null){
			annotationMap.put("biggestrrgroupvar", crossLeadMeasurements.getBiggestrrgroupvar().toString());	
		}else{
			annotationMap.put("biggestrrgroupvar", null);
		}
		if(crossLeadMeasurements.getNrrgroups()!=null){
			annotationMap.put("nrrgroups", crossLeadMeasurements.getNrrgroups().toString());	
		}else{
			annotationMap.put("nrrgroups", null);
		}
		if(crossLeadMeasurements.getFibfreqmhz() != null){
			annotationMap.put("fibfreqmhz", crossLeadMeasurements.getFibfreqmhz().toString());	
		}else{
			annotationMap.put("fibfreqmhz", null);
		}
		if(crossLeadMeasurements.getFibampnv() != null){
			annotationMap.put("fibampnv", crossLeadMeasurements.getFibampnv().toString());	
		}else{
			annotationMap.put("fibampnv", null);
		}
		if(crossLeadMeasurements.getFibwidthmhz() != null){
			annotationMap.put("fibwidthmhz", crossLeadMeasurements.getFibwidthmhz().toString());
		}else{
			annotationMap.put("fibwidthmhz", null);
		}
		if(crossLeadMeasurements.getFibmedianfreqmhz() != null){
			annotationMap.put("fibmedianfreqmhz", crossLeadMeasurements.getFibmedianfreqmhz().toString());	
		}else{
			annotationMap.put("fibmedianfreqmhz", null);
		}
		if(crossLeadMeasurements.getAfltcyclelen() != null){
			annotationMap.put("afltcyclelen", crossLeadMeasurements.getAfltcyclelen().toString());	
		}else{
			annotationMap.put("afltcyclelen", null);
		}
		if(crossLeadMeasurements.getAfltacfpeakwidth() != null){
			annotationMap.put("afltacfpeakwidth", crossLeadMeasurements.getAfltacfpeakwidth().toString());	
		}else{
			annotationMap.put("afltacfpeakwidth", null);
		}
		
		annotationMap.put("afltacfpeak", crossLeadMeasurements.getAfltacfpeak());
		annotationMap.put("constantpshapepct", crossLeadMeasurements.getConstantpshapepct());
		annotationMap.put("atrialrateirregpct", crossLeadMeasurements.getAtrialrateirregpct());
		
		// the vector loop mxs elements
		annotationMap.put("Transverse P Clockwise Rotation", crossLeadMeasurements.getTranspcwrot());
		annotationMap.put("Transverse P Initial Angle", crossLeadMeasurements.getTranspinitangle());
		annotationMap.put("Transverse P Initial Magnitude", crossLeadMeasurements.getTranspinitmag());
		annotationMap.put("Transverse P Maximum Angle", crossLeadMeasurements.getTranspmaxangle());
		annotationMap.put("Transverse P Maximum Magnitude", crossLeadMeasurements.getTranspmaxmag());
		annotationMap.put("Transverse P Terminal Angle", crossLeadMeasurements.getTransptermangle());
		annotationMap.put("Transverse P Terminal Magnitude", crossLeadMeasurements.getTransptermmag());
		annotationMap.put("Transverse QRS Clockwise Rotation", crossLeadMeasurements.getTransqrscwrot());
		annotationMap.put("Transverse QRS Initial Angle", crossLeadMeasurements.getTransqrsinitangle());
		annotationMap.put("Transverse QRS Initial Magnitude", crossLeadMeasurements.getTransqrsinitmag());
		annotationMap.put("Transverse QRS Maximum Angle", crossLeadMeasurements.getTransqrsmaxangle());
		annotationMap.put("Transverse QRS Maximum Magnitude", crossLeadMeasurements.getTransqrsmaxmag());
		annotationMap.put("Transverse QRS Terminal Angle", crossLeadMeasurements.getTransqrstermangle());
		annotationMap.put("Transverse QRS Terminal Magnitude", crossLeadMeasurements.getTransqrstermmag());
		annotationMap.put("Transverse T Clockwise Rotation", crossLeadMeasurements.getTranstcwrot());
		annotationMap.put("Transverse T Initial Angle", crossLeadMeasurements.getTranstinitangle());
		annotationMap.put("Transverse T Initial Magnitude", crossLeadMeasurements.getTranstinitmag());
		annotationMap.put("Transverse T Maximum Angle", crossLeadMeasurements.getTranstmaxangle());
		annotationMap.put("Transverse T Maximum Magnitude", crossLeadMeasurements.getTranstmaxmag());
		annotationMap.put("Transverse T Terminal Angle", crossLeadMeasurements.getTransttermangle());
		annotationMap.put("Transverse T Terminal Magnitude", crossLeadMeasurements.getTransttermmag());
		annotationMap.put("Frontal P Clockwise Rotation", crossLeadMeasurements.getFrontpcwrot());
		annotationMap.put("Frontal P Initial Angle", crossLeadMeasurements.getFrontpinitangle());
		annotationMap.put("Frontal P Initial Magnitude", crossLeadMeasurements.getFrontpinitmag());
		annotationMap.put("Frontal P Maximum Angle", crossLeadMeasurements.getFrontpmaxangle());
		annotationMap.put("Frontal P Maximum Magnitude", crossLeadMeasurements.getFrontpmaxmag());
		annotationMap.put("Frontal P Terminal Angle", crossLeadMeasurements.getFrontptermangle());
		annotationMap.put("Frontal P Terminal Magnitude", crossLeadMeasurements.getFrontptermmag());
		annotationMap.put("Frontal QRS Clockwise Rotation", crossLeadMeasurements.getFrontqrscwrot());
		annotationMap.put("Frontal QRS Initial Angle", crossLeadMeasurements.getFrontqrsinitangle());
		annotationMap.put("Frontal QRS Initial Magnitude", crossLeadMeasurements.getFrontqrsinitmag());
		annotationMap.put("Frontal QRS Maximum Angle", crossLeadMeasurements.getFrontqrsmaxangle());
		annotationMap.put("Frontal QRS Maximum Magnitude", crossLeadMeasurements.getFrontqrsmaxmag());
		annotationMap.put("Frontal QRS Terminal Angle", crossLeadMeasurements.getFrontqrstermangle());
		annotationMap.put("Frontal QRS Terminal Magnitude", crossLeadMeasurements.getFrontqrstermmag());
		annotationMap.put("Frontal T Clockwise Rotation", crossLeadMeasurements.getFronttcwrot());
		annotationMap.put("Frontal T Initial Angle", crossLeadMeasurements.getFronttinitangle());
		annotationMap.put("Frontal T Initial Magnitude", crossLeadMeasurements.getFronttinitmag());
		annotationMap.put("Front T Maximum Angle", crossLeadMeasurements.getFronttmaxangle());
		annotationMap.put("Front T Maximum Magnitude", crossLeadMeasurements.getFronttmaxmag());
		annotationMap.put("Front T Terminal Angle", crossLeadMeasurements.getFrontttermangle());
		annotationMap.put("Front T Terminal Magnitude", crossLeadMeasurements.getFrontttermmag());
		annotationMap.put("Saggital P Clockwise Rotation", crossLeadMeasurements.getSagpcwrot());
		annotationMap.put("Saggital P Initial Angle", crossLeadMeasurements.getSagpinitangle());
		annotationMap.put("Saggital P Initial Magnitude", crossLeadMeasurements.getSagpinitmag());
		annotationMap.put("Saggital P Maximum Angle", crossLeadMeasurements.getSagpmaxangle());
		annotationMap.put("Saggital P Maximum Magnitude", crossLeadMeasurements.getSagpmaxmag());
		annotationMap.put("Saggital P Terminal Angle", crossLeadMeasurements.getSagptermangle());
		annotationMap.put("Saggital P Terminal Magnitude", crossLeadMeasurements.getSagptermmag());
		annotationMap.put("Saggital QRS Clockwise Rotation", crossLeadMeasurements.getSagqrscwrot());
		annotationMap.put("Saggital QRS Initial Angle", crossLeadMeasurements.getSagqrsinitangle());
		annotationMap.put("Saggital QRS Initial Magnitude", crossLeadMeasurements.getSagqrsinitmag());
		annotationMap.put("Saggital QRS Maximum Angle", crossLeadMeasurements.getSagqrsmaxangle());
		annotationMap.put("Saggital QRS Maximum Magnitude", crossLeadMeasurements.getSagqrsmaxmag());
		annotationMap.put("Saggital QRS Terminal Angle", crossLeadMeasurements.getSagqrstermangle());
		annotationMap.put("Saggital QRS Terminal Magnitude", crossLeadMeasurements.getSagqrstermmag());
		annotationMap.put("Saggital T Clockwise Rotation", crossLeadMeasurements.getSagtcwrot());
		annotationMap.put("Saggital T Initial Angle", crossLeadMeasurements.getSagtinitangle());
		annotationMap.put("Saggital T Initial Magnitude", crossLeadMeasurements.getSagtinitmag());
		annotationMap.put("Saggital T Maximum Angle", crossLeadMeasurements.getSagtmaxangle());
		annotationMap.put("Saggital T Maximum Magnitude", crossLeadMeasurements.getSagtmaxmag());
		annotationMap.put("Saggital T Terminal Angle", crossLeadMeasurements.getSagttermangle());
		annotationMap.put("Saggital T Terminal Magnitude", crossLeadMeasurements.getSagttermmag());
		
		if(crossLeadMeasurements.getPreexcitation() != null){
			annotationMap.put("preexcitation", crossLeadMeasurements.getPreexcitation().toString());	
		}else{
			annotationMap.put("preexcitation", null);
		}
		
		// TODO:  Get beat annotations once the schema has been changed to accomdate for them
		if(crossLeadMeasurements.getAnalysiserror() != null){
			annotationMap.put("analysiserror", crossLeadMeasurements.getAnalysiserror().toString());	
		}else{
			annotationMap.put("analysiserror", null);
		}
		
		annotationMap.put("analysiserrormessage", crossLeadMeasurements.getAnalysiserrormessage());
		
		// TODO:  Get namedmeasurements, if any.
		
		return annotationMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> extractOrderInfo(OrderInfo orderInfo) {
		
		log.info("Extracting Order Info for Philips 104");
		
		Map<String, String> orderInfoMap = new HashMap<String, String>();
		
		orderInfoMap.put("Order Number", orderInfo.getOrdernumber());
		orderInfoMap.put("Unique Order ID", orderInfo.getUniqueorderid());
		orderInfoMap.put("Order Billing Code", orderInfo.getOrderbillingcode());
		orderInfoMap.put("Order Remarks", orderInfo.getOrderremarks());
		orderInfoMap.put("Reason For Order", orderInfo.getReasonfororder());
		
		int subscript = 1;
		
		if (orderInfo.getDrgcategories() != null) {
			for (Drgcategory drgCategory : ((Drgcategories) orderInfo.getDrgcategories()).getDrgcategory()) {
				if (drgCategory != null) {
					String messageName = "drgcategories" + subscript;
					orderInfoMap.put(messageName, drgCategory.getValue());
					subscript++;
				}
			}
		}
		
		orderInfoMap.put("Order Status", orderInfo.getOrderstatus());
		orderInfoMap.put("Inbox", orderInfo.getInbox());
		
		subscript = 1;
		
		if (orderInfo.getOther() != null) {
			for (Other other : (List<Orderinfo.Other>) orderInfo.getOther()) {
				if (other != null) {
					String messageName = "drgcategories" + subscript;
					orderInfoMap.put(messageName, other.getValue());
					subscript++;
				}
			}
		}
		
		return orderInfoMap;
	}

	@Override
	public Map<String, String> extractDataAcquisition(DataAcquisition dataAcquisition) {
		
		log.info("Extracting Data Acquisition for Philips 104");
		
		Map<String, String> dataMappings = new HashMap<String, String>();
		
		dataMappings.put("Database ID", dataAcquisition.getDatabaseid());
		dataMappings.put("Modality", dataAcquisition.getModality());
		
		// Now get the acquirer block in the XML
		Acquirer acquirerAnn = (Acquirer)dataAcquisition.getAcquirer(); 
		
		if(acquirerAnn.getOperator() != null){
			dataMappings.put("Acquirer Operator Name", acquirerAnn.getOperator().getValue());
		}
		
		dataMappings.put("Bed", acquirerAnn.getBed());
		
		dataMappings.put("Facility ID", acquirerAnn.getFacilityid());
		dataMappings.put("Facility Name", acquirerAnn.getFacilityname());
		
		if(acquirerAnn.getFellow() != null){
			dataMappings.put("Fellow ID", acquirerAnn.getFellow().getId());
			dataMappings.put("Fellow Name", acquirerAnn.getFellow().getValue());
		}
		if(acquirerAnn.getAttendingclinician() != null){
			dataMappings.put("Attending Clinician ID", acquirerAnn.getAttendingclinician().getId());
			dataMappings.put("Attending Clinician Name", acquirerAnn.getAttendingclinician().getValue());
		}
		if(acquirerAnn.getReferringclinician() != null){
			dataMappings.put("Referring Clinician ID", acquirerAnn.getReferringclinician().getId());
			dataMappings.put("Referring Clinician Name", acquirerAnn.getReferringclinician().getValue());
		}
		
		// Retrieve the Signal Characteristics block.  The Sampling rate and number of channels
		// information will not be gathered here, since those are being tracked elsewhere.
		
		Signalcharacteristics signalProperties = (Signalcharacteristics)dataAcquisition.getSignalcharacteristics();
		
		dataMappings.put("High Pass Frequency", String.valueOf(signalProperties.getHipass()));
		dataMappings.put("Low Pass Frequency", signalProperties.getLowpass().toString());
		dataMappings.put("Notch Filtered", signalProperties.getNotchfiltered().toString());
		dataMappings.put("Notch Filter Frequency", signalProperties.getNotchfilterfreqs());
		
		if(signalProperties.getArtfiltered() != null){
			dataMappings.put("Filtered ART", signalProperties.getArtfiltered().toString());	
		}else{
			dataMappings.put("Filtered ART", null);
		}
		
		dataMappings.put("Other Acquisition Information", signalProperties.getOtheracquisitioninfo());
		dataMappings.put("Electrode Placement", signalProperties.getElectrodeplacement().toString());
		dataMappings.put("Other Placement Information", signalProperties.getOtherplacementinfo());
		if(signalProperties.getDerivedleads() != null){
			dataMappings.put("Number of Derived Leads", signalProperties.getDerivedleads().toString());	
		}else{
			dataMappings.put("Number of Derived Leads", null);
		}
		
		this.commomDataAcquisition(dataAcquisition, new Philips104Acquirer(acquirerAnn), new Philips104SignalCharacteristics(signalProperties), dataMappings);
		
		if(acquirerAnn.getOrderingclinician() != null){
			dataMappings.put("Ordering Clinician ID", acquirerAnn.getOrderingclinician().getId());
			dataMappings.put("Ordering Clinician Name ", acquirerAnn.getOrderingclinician().getValue());
		}
		
		return dataMappings;
	}
	
	@Override
	protected Map<String, String> completeGroupMeasurementsExtraction(Map<String, String> annotationMap, GroupMeasurement groupMeasurement){
		
		log.info("Completing Global Measurements for Philips 104");
		
		annotationMap.put("comppausecount", groupMeasurement.getComppausecount());
		
		return annotationMap;
	}
}
