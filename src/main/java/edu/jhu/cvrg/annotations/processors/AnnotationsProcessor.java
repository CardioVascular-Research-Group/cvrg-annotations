package edu.jhu.cvrg.annotations.processors;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import edu.jhu.cvrg.annotations.utilities.exceptions.AnnotationExtractorException;

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
	
	protected Logger log;
	protected String name;
	
	protected Map<String, String> globalAnnotations;
	protected Map<Integer, Map<String, String>> leadsAnnotations;
	
	
	public AnnotationsProcessor() {
		leadsAnnotations = new HashMap<Integer, Map<String, String>>();
		globalAnnotations = new HashMap<String, String>();
	}
	
	public Map<String, String> getGlobalAnnotations() {
		return globalAnnotations;
	}
	
	public Map<Integer, Map<String, String>> getLeadAnnotations() {
		return leadsAnnotations;
	}

	public abstract void populateAnnotations() throws AnnotationExtractorException;
	
	public abstract void processLeadAnnotations();
	
	public abstract void processGlobalAnnotations();
	
	public void processAll() throws AnnotationExtractorException{
		this.populateAnnotations();
		this.processGlobalAnnotations();
		this.processLeadAnnotations();
	}

	public String getName() {
		return name;
	}
}
