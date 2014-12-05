package edu.jhu.cvrg.annotations.processors;

import org.apache.log4j.Logger;

import edu.jhu.cvrg.annotations.extractors.MuseAnnotationsExtractor;
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
* @author Chris Jurado, Brandon Benitez
* 
*/

public class MuseAnnotationsProcessor extends AnnotationsProcessor {
	
	private MuseAnnotationsExtractor extractor;
	
	private static final String MUSE_NAME = "Muse Upload";
	
	public MuseAnnotationsProcessor(String xmlInput, Long documentId, Long userID) throws AnnotationExtractorException{
		super();
		extractor = new MuseAnnotationsExtractor(xmlInput);
		log = Logger.getLogger(MuseAnnotationsProcessor.class);
		
		this.name = MUSE_NAME;
	}
	
	@Override
	public void populateAnnotations() {
		extractor.extractAll();
	}
	
	@Override
	public void processGlobalAnnotations() {
		globalAnnotations = extractor.getGlobalAnnotations();
		
	}

	@Override
	public void processLeadAnnotations() {
		leadsAnnotations = extractor.getLeadAnnotations();
	}
}
