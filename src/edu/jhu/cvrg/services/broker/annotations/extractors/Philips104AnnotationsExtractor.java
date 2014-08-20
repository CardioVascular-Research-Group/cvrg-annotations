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
import java.util.List;

import org.apache.log4j.Logger;
import org.cvrgrid.philips.jaxb.beans.Acquirer;
import org.cvrgrid.philips.jaxb.beans.Crossleadmeasurements;
import org.cvrgrid.philips.jaxb.beans.Drgcategories;
import org.cvrgrid.philips.jaxb.beans.Drgcategory;
import org.cvrgrid.philips.jaxb.beans.Machine;
import org.cvrgrid.philips.jaxb.beans.Orderinfo;
import org.cvrgrid.philips.jaxb.beans.Orderinfo.Other;
import org.cvrgrid.philips.jaxb.beans.Signalcharacteristics;
import org.cvrgrid.philips.jaxb.beans.TYPEmessagecode;

import edu.jhu.cvrg.services.broker.annotations.utilities.interfaces.DataAcquisition;
import edu.jhu.cvrg.services.broker.annotations.utilities.interfaces.GroupMeasurement;
import edu.jhu.cvrg.services.broker.annotations.utilities.interfaces.OrderInfo;

public class Philips104AnnotationsExtractor extends PhilipsAnnotationsExtractor {
	
	private Logger log = Logger.getLogger(Philips104AnnotationsExtractor.class);

	protected LinkedHashMap<String, Object> extractGlobalElements(Crossleadmeasurements crossLeadMeasurements) {
		
		log.info("Extracting Global Elements for Philips 104");
		
		LinkedHashMap<String, Object> annotationMap = new LinkedHashMap<String, Object>();
		
		// The mapping will need to be filled manually since currently we cannot get a list of all of the elements
		// Do not do complex annotation measurements yet.  Stick with the simple ones.
		
		// TODO:  Skipping pacedetectleads and pacepulses for now, return to them later.
		
		// Main set of cross lead measurements
		annotationMap.put("pacemode", crossLeadMeasurements.getPacemode());
		annotationMap.put("pacemalf", crossLeadMeasurements.getPacemalf());
		annotationMap.put("pacemisc", crossLeadMeasurements.getPacemisc());
		annotationMap.put("ectopicrhythm", crossLeadMeasurements.getEctopicrhythm());
		annotationMap.put("QT Interval Dispersion", crossLeadMeasurements.getQtintdispersion());
		annotationMap.put("numberofcomplexes", crossLeadMeasurements.getNumberofcomplexes());
		annotationMap.put("numberofgroups", crossLeadMeasurements.getNumberofgroups());
		
		String beatClassificationValues = "";
		
		// concatenate all the beat classifications, as that is how they appear in the XML
		for (Integer beatValue : crossLeadMeasurements.getBeatclassification()) {
			beatClassificationValues = beatClassificationValues + " " + beatValue;
		}
		annotationMap.put("beatclassification", beatClassificationValues);
		int subscript = 1;
		
		// enter the qamessagecodes one by one, as that is how they are in the XML
		for(TYPEmessagecode mCode : crossLeadMeasurements.getQamessagecodes().getQamessagecode()) {
			if(mCode != null) {
				String messageName = "qamessagecode" + subscript;
				annotationMap.put(messageName, mCode.value());
				subscript++;
			}
		}
		
		// additional checks for null are needed on some of these retrievals.  Some of them require methods other
		// than toString()
		if(crossLeadMeasurements.getQaactioncode() != null) {
			annotationMap.put("qaactioncode", crossLeadMeasurements.getQaactioncode().value());
		}
		annotationMap.put("P Frontal Axis", crossLeadMeasurements.getPfrontaxis());
		annotationMap.put("P Horizontal Axis", crossLeadMeasurements.getPhorizaxis());
		annotationMap.put("i40 Frontal Axis", crossLeadMeasurements.getI40Frontaxis());
		annotationMap.put("i40 Horizontal Axis", crossLeadMeasurements.getI40Horizaxis());
		annotationMap.put("QRS Frontal Axis", crossLeadMeasurements.getQrsfrontaxis());
		annotationMap.put("QRS Horizontal Axis", crossLeadMeasurements.getQrshorizaxis());
		annotationMap.put("t40 Frontal Axis", crossLeadMeasurements.getT40Frontaxis());
		annotationMap.put("t40 Horizontal Axis", crossLeadMeasurements.getT40Horizaxis());
		annotationMap.put("ST Frontal Axis", crossLeadMeasurements.getStfrontaxis());
		annotationMap.put("ST Horizontal Axis", crossLeadMeasurements.getSthorizaxis());
		annotationMap.put("T Frontal Axis", crossLeadMeasurements.getTfrontaxis());
		annotationMap.put("T Horizontal Axis", crossLeadMeasurements.getThorizaxis());
		annotationMap.put("Atrial Rate", crossLeadMeasurements.getAtrialrate());
		annotationMap.put("Minimum Ventreicular Rate", crossLeadMeasurements.getLowventrate());
		annotationMap.put("Mean Ventricular Rate", crossLeadMeasurements.getMeanventrate());
		annotationMap.put("Maximum Ventricular Rate", crossLeadMeasurements.getHighventrate());
		annotationMap.put("Mean PR Interval", crossLeadMeasurements.getMeanprint());
		annotationMap.put("Mean PR Segment", crossLeadMeasurements.getMeanprseg());
		annotationMap.put("Mean QRS Duration", crossLeadMeasurements.getMeanqrsdur());
		annotationMap.put("Mean QT Interval", crossLeadMeasurements.getMeanqtint());
		annotationMap.put("Mean QT Corrected", crossLeadMeasurements.getMeanqtc());
		annotationMap.put("Delta Wave Count", crossLeadMeasurements.getDeltawavecount());
		annotationMap.put("Delta Wave Percent", crossLeadMeasurements.getDeltawavepercent());
		annotationMap.put("bigeminycount", crossLeadMeasurements.getBigeminycount());
		annotationMap.put("bigeminystring", crossLeadMeasurements.getBigeminystring());
		annotationMap.put("trigeminycount", crossLeadMeasurements.getTrigeminycount());
		annotationMap.put("trigeminystring", crossLeadMeasurements.getTrigeminystring());
		annotationMap.put("wenckcount", crossLeadMeasurements.getWenckcount());
		annotationMap.put("wenckstring", crossLeadMeasurements.getWenckstring());
		annotationMap.put("flutterfibcount", crossLeadMeasurements.getFlutterfibcount());
		
		annotationMap.put("leadreversalcode", crossLeadMeasurements.getLeadreversalcode());
		
		annotationMap.put("Atrial Rhythm", crossLeadMeasurements.getAtrialrhythm());
		annotationMap.put("atrialratepowerspect", crossLeadMeasurements.getAtrialratepowerspect());
		annotationMap.put("Ventricular Rhythm", crossLeadMeasurements.getVentrhythm());
		annotationMap.put("randomrrpercent", crossLeadMeasurements.getRandomrrpercent());
		annotationMap.put("regularrrpercent", crossLeadMeasurements.getRegularrrpercent());
		annotationMap.put("biggestrrgrouppercent", crossLeadMeasurements.getBiggestrrgrouppercent());
		annotationMap.put("biggestrrgroupvar", crossLeadMeasurements.getBiggestrrgroupvar());
		annotationMap.put("nrrgroups", crossLeadMeasurements.getNrrgroups());
		annotationMap.put("bigemrrintvlacf", crossLeadMeasurements.getBigemrrintvlacf());
		annotationMap.put("trigemrrintvlacf", crossLeadMeasurements.getTrigemrrintvlacf());
		annotationMap.put("fibfreqmhz", crossLeadMeasurements.getFibfreqmhz());
		annotationMap.put("fibampnv", crossLeadMeasurements.getFibampnv());
		annotationMap.put("fibwidthmhz", crossLeadMeasurements.getFibwidthmhz());
		annotationMap.put("fibmedianfreqmhz", crossLeadMeasurements.getFibmedianfreqmhz());
		annotationMap.put("afltcyclelen", crossLeadMeasurements.getAfltcyclelen());
		annotationMap.put("afltacfpeak", crossLeadMeasurements.getAfltacfpeak());
		annotationMap.put("afltacfpeakwidth", crossLeadMeasurements.getAfltacfpeakwidth());
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
		
		annotationMap.put("preexcitation", crossLeadMeasurements.getPreexcitation());
		
		// TODO:  Get beat annotations once the schema has been changed to accomdate for them
		
		annotationMap.put("analysiserror", crossLeadMeasurements.getAnalysiserror());
		annotationMap.put("analysiserrormessage", crossLeadMeasurements.getAnalysiserrormessage());
		
		// TODO:  Get namedmeasurements, if any.
		
		return annotationMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected LinkedHashMap<String, Object> extractOrderInfo(OrderInfo orderInfo) {
		
		log.info("Extracting Order Info for Philips 104");
		
		LinkedHashMap<String, Object> orderInfoMap = new LinkedHashMap<String, Object>();
		
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
	protected LinkedHashMap<String, Object> extractDataAcquisition(DataAcquisition dataAcquisition) {
		
		log.info("Extracting Data Acquisition for Philips 104");
		
		LinkedHashMap<String, Object> dataMappings = new LinkedHashMap<String, Object>();
		
		dataMappings.put("Database ID", dataAcquisition.getDatabaseid());
		dataMappings.put("Modality", dataAcquisition.getModality());
		
		if(dataAcquisition.getMachine() != null){
			dataMappings.put("Machine", ((Machine)dataAcquisition.getMachine()).getMachineid());
		}
		
		// Now get the acquirer block in the XML
		Acquirer acquirerAnn = (Acquirer)dataAcquisition.getAcquirer(); 
		
		dataMappings.put("Acquirer Encounter ID", acquirerAnn.getEncounterid());
		if(acquirerAnn.getOperator() != null){
			dataMappings.put("Operator ID", acquirerAnn.getOperator().getId());
			dataMappings.put("Operator Name", acquirerAnn.getOperator().getValue());
		}
		
		dataMappings.put("Room", acquirerAnn.getRoom());
		dataMappings.put("Bed", acquirerAnn.getBed());
		dataMappings.put("Department ID", acquirerAnn.getDepartmentid());
		dataMappings.put("Department Name", acquirerAnn.getDepartmentname());
		dataMappings.put("Institution ID", acquirerAnn.getInstitutionid());
		dataMappings.put("Institution Name", acquirerAnn.getInstitutionname());
		dataMappings.put("Facility ID", acquirerAnn.getFacilityid());
		dataMappings.put("Facility Name", acquirerAnn.getFacilityname());
		
		if(acquirerAnn.getFellow() != null){
			dataMappings.put("Ordering Clinician ID", acquirerAnn.getOrderingclinician().getId());
			dataMappings.put("Ordering Clinician Name ", acquirerAnn.getOrderingclinician().getValue());
		}
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
		if(acquirerAnn.getConsultingclinician() != null){
			dataMappings.put("Consulting Clinician ID", acquirerAnn.getConsultingclinician().getId());
			dataMappings.put("Consulting Clinician Name", acquirerAnn.getConsultingclinician().getValue());	
		}
		
		// Retrieve the Signal Characteristics block.  The Sampling rate and number of channels
		// information will not be gathered here, since those are being tracked elsewhere.
		
		Signalcharacteristics signalProperties = (Signalcharacteristics)dataAcquisition.getSignalcharacteristics();
		
		dataMappings.put("Signal Resolution", signalProperties.getResolution());
		dataMappings.put("High Pass Frequency", signalProperties.getHipass());
		dataMappings.put("Low Pass Frequency", signalProperties.getLowpass());
		dataMappings.put("AC Setting", signalProperties.getAcsetting());
		dataMappings.put("Notch Filtered", signalProperties.getNotchfiltered());
		dataMappings.put("Notch Filter Frequency", signalProperties.getNotchfilterfreqs());
		dataMappings.put("Filtered ART", signalProperties.getArtfiltered());
		dataMappings.put("Acquisition Type", signalProperties.getAcquisitiontype());
		dataMappings.put("Other Acquisition Information", signalProperties.getOtheracquisitioninfo());
		dataMappings.put("Bits Per Sample", signalProperties.getBitspersample());
		dataMappings.put("Signal Offset", signalProperties.getSignaloffset());
		dataMappings.put("Signal Signed", signalProperties.getSignalsigned());
		dataMappings.put("Electrode Placement", signalProperties.getElectrodeplacement());
		dataMappings.put("Other Placement Information", signalProperties.getOtherplacementinfo());
		dataMappings.put("Number of Derived Leads", signalProperties.getDerivedleads());
		
		return dataMappings;
	}
	
	@Override
	protected LinkedHashMap<String, Object> completeGroupMeasurementsExtraction(LinkedHashMap<String, Object> annotationMap, GroupMeasurement groupMeasurement){
		
		log.info("Completing Global Measurements for Philips 104");
		
		annotationMap.put("comppausecount", groupMeasurement.getComppausecount());
		
		return annotationMap;
	}
}
