package edu.jhu.cvrg.services.broker.annotations.extractors;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.Map;

import edu.jhu.cvrg.dbapi.dto.AnnotationDTO;
import edu.jhu.cvrg.services.nodeConversionService.annotation.LeadEnum;
import edu.jhu.cvrg.services.nodeConversionService.annotation.muse.LeadMeta;
import edu.jhu.cvrg.waveform.utility.WebServiceUtility;

public class MuseAnnotationsExtractor extends AnnotationsExtractor {

	public LinkedHashMap<String, ArrayList<AnnotationDTO>> getLeadAnnotations() {
		LinkedHashMap<String, ArrayList<AnnotationDTO>> finalMap = new LinkedHashMap<String, ArrayList<AnnotationDTO>>();
		LinkedHashMap<String, LeadMeta> leadMap = wholeWaveformMeta.getLeadAnnotations();
		
		for(String key : leadMap.keySet()) {
			LeadMeta tempLead = leadMap.get(key);
			
			LinkedHashMap<String, String> allTerms = tempLead.getAllAnnotations();
			
			ArrayList<AnnotationDTO> termsList = createAnnotations(allTerms, LeadEnum.valueOf(key));
			finalMap.put(key, termsList);
		}
		
		return finalMap;
	}
	
	
	private ArrayList<AnnotationDTO> createAnnotations(LinkedHashMap<String, String> annotationEntries) {
		return createAnnotations(annotationEntries, null);
	}
	
	private ArrayList<AnnotationDTO> createAnnotations(LinkedHashMap<String, String> annotationEntries, LeadEnum leadIndexEnum) {
		ArrayList<AnnotationDTO> newList = new ArrayList<AnnotationDTO>();
		String annType;
		Integer iLeadIndex = null;
		
		if(leadIndexEnum != null) {
			annType = "ANNOTATION";
			switch(leadIndexEnum) {
				case I:
					iLeadIndex = 0;
					break;
				case II:
					iLeadIndex = 1;
					break;
				case III:
					iLeadIndex = 2;
					break;
				case aVR:
					iLeadIndex = 3;
					break;
				case aVL:
					iLeadIndex = 4;
					break;
				case aVF:
					iLeadIndex = 5;
					break;
				case V1:
					iLeadIndex = 6;
					break;
				case V2:
					iLeadIndex = 7;
					break;
				case V3:
					iLeadIndex = 8;
					break;
				case V4:
					iLeadIndex = 9;
					break;
				case V5:
					iLeadIndex = 10;
					break;
				case V6:
					iLeadIndex = 11;
					break;
				case VX:
					iLeadIndex = 12;
					break;
				case VY:
					iLeadIndex = 13;
					break;
				case VZ:
					iLeadIndex = 14;
					break;
				default:
					// unknown lead, best to put it on the whole ECG for now
					iLeadIndex = null;
			}
		}
		else {
			annType = "COMMENT";
		}
		
		for(String key : annotationEntries.keySet()) {
			if(annotationEntries.get(key) != null) {
				
				// TODO:  A lookup table should be used instead of this, but for now this will do since
				// we only have a few Muse annotations that have matching Bioportal terms
				String conceptId = "";
				if(annotationEntries.get(key).toString().equals("PR_Interval")){
					conceptId = "http://www.cvrgrid.org/files/ECGTermsv1.owl#ECG_000000341"; 
				}else if(annotationEntries.get(key).toString().equals("QT_Interval")){
					conceptId = "http://www.cvrgrid.org/files/ECGTermsv1.owl#ECG_000000682"; 
				}else if(annotationEntries.get(key).toString().equals("QT_Corrected")) {
					conceptId = "http://www.cvrgrid.org/files/ECGTermsv1.owl#ECG_000000701";
				}else if(annotationEntries.get(key).toString().equals("QT_Corrected_Fridericias_Formula")) {
					conceptId = "http://www.cvrgrid.org/files/ECGTermsv1.owl#ECG_000000040";
				}
				
				String prefLabel = "";
				String fullAnnotation = "";
				
				if(!(conceptId.equals(""))) {
					Map<String, String> saOntDetails = WebServiceUtility.lookupOntology(AnnotationDTO.ECG_TERMS_ONTOLOGY, conceptId, "definition", "prefLabel");
					prefLabel = saOntDetails.get("prefLabel");
					fullAnnotation = saOntDetails.get("definition");
				}
				else {
					prefLabel = key;
				}
				
				AnnotationDTO annData = new AnnotationDTO(Long.valueOf(userID)/*userid*/, 0L/*groupID*/, 0L/*companyID*/, docID, createdBy/*createdBy*/, annType, prefLabel, 
						 conceptId != null ? AnnotationDTO.ECG_TERMS_ONTOLOGY : null , conceptId,
						 null/*bioportalRef*/, iLeadIndex, null/*unitMeasurement*/, null/*description*/, fullAnnotation , Calendar.getInstance(), 
						 null, null, null, null, //start and end are the same than this is a single point annotation 
						 studyID/*newStudyID*/, recordName/*newRecordName*/, subjectID/*newSubjectID*/);
				

				annData.setNewStudyID(studyID);
				annData.setNewSubjectID(subjectID);
				annData.setUserID(Long.valueOf(userID));
				annData.setRecordID(docID);
				annData.setNewRecordName(recordName);
				annData.setValue(annotationEntries.get(key).toString());
				annData.setName(key);
				annData.setCreatedBy(createdBy);
				annData.setTimestamp(new GregorianCalendar());
				
				
				newList.add(annData);
			}
		}
		
		return newList;
	}
	
}
