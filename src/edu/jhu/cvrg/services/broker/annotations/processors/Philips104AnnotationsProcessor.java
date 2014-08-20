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
* @author Chris Jurado, Brandon Benitez, Michael Shipway
* 
*/
import java.util.ArrayList;

import edu.jhu.cvrg.dbapi.dto.AnnotationDTO;

public class Philips104AnnotationsProcessor extends PhilipsAnnotationsProcessor {

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setRestingecgdata() {
		// TODO Auto-generated method stub

	}

	@Override
	protected Object getRestingecgdata() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ArrayList<Annotation> getOrderInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ArrayList<Annotation> getDataAcquisitions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void extractOrderInformation() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void processDataAcquisition() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void processGroupAnnotations() {
		// TODO Auto-generated method stub

	}

	@Override
	public void populateAnnotations() {
		// TODO Auto-generated method stub

	}

	@Override
	public void processLeadAnnotations() {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Annotation> getLeadAnnotations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Annotation> getGroupAnnotations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Annotation> getGlobalAnnotations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void processGlobalAnnotations() {
		// TODO Auto-generated method stub

	}

}
