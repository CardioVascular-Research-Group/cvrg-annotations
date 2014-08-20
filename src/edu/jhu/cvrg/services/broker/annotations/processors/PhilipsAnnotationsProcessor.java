package edu.jhu.cvrg.services.broker.annotations.processors;
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
import java.util.ArrayList;

import edu.jhu.cvrg.annotations.model.Annotation;
import edu.jhu.cvrg.services.broker.annotations.utilities.interfaces.RestingECGData;

public abstract class PhilipsAnnotationsProcessor extends AnnotationsProcessor{

	protected RestingECGData restingECGData;
	protected String userID;

	protected ArrayList<Annotation> orderAnnotationsList = new ArrayList<Annotation>();
	protected ArrayList<Annotation> dataAcquisitionList = new ArrayList<Annotation>();
	
	public ArrayList<Annotation> getOrderInfo() {
		return orderAnnotationsList;
	}
	
	public ArrayList<Annotation> getDataAcquisitions() {
		return dataAcquisitionList;
	}
	
	protected abstract void extractOrderInformation();
	
	protected abstract void processDataAcquisition();
	
	protected abstract void processGroupAnnotations();
	
}
