package edu.jhu.cvrg.services.broker.annotations.processors;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import edu.jhu.cvrg.annotations.model.Annotation;
import edu.jhu.cvrg.annotations.model.GlobalAnnotation;
import edu.jhu.cvrg.annotations.model.LeadAnnotation;

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
* @author Chris Jurado
* 
*/
public abstract class AnnotationsProcessor {
	
	protected ArrayList<GlobalAnnotation> globalAnnotationsList;
	protected ArrayList<Annotation[]> groupAnnotationsList;
	protected LinkedHashMap<String, ArrayList<LeadAnnotation>> leadAnnotationsList;
	protected String studyID;
	protected long docID;
	protected String recordName;
	protected String subjectID;
	
	public ArrayList<GlobalAnnotation> getGlobalAnnotations() {
		return globalAnnotationsList;
	}
	
	public ArrayList<Annotation[]> getGroupAnnotations() {
		return groupAnnotationsList;
	}
	
	public LinkedHashMap<String, ArrayList<LeadAnnotation>> getLeadAnnotations() {
		return leadAnnotationsList;
	}

	public abstract void populateAnnotations();
	
	public abstract void processLeadAnnotations();
	
	public abstract void processGlobalAnnotations();
	
}
