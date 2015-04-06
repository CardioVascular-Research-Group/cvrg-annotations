package edu.jhu.cvrg.annotations.wrapper.philips;
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
import javax.xml.datatype.XMLGregorianCalendar;

import org.cvrgrid.philips.jaxb.beans.Dataacquisition;

import edu.jhu.cvrg.annotations.utilities.interfaces.DataAcquisition;

public class Philips104DataAcquisition implements DataAcquisition{

	private Dataacquisition dataAcquisition;
	
	public Philips104DataAcquisition(Dataacquisition dataAcquisition){
		this.dataAcquisition = dataAcquisition;
	}

	@Override
	public String getDatabaseid() {		
		return dataAcquisition.getDatabaseid();
	}

	@Override
	public String getModality() {
		return dataAcquisition.getModality();
	}

	@Override
	public String getMachineId() {
		return dataAcquisition.getMachine().getMachineid();
	}

	@Override
	public Object getAcquirer() {
		return dataAcquisition.getAcquirer();
	}

	@Override
	public Object getSignalcharacteristics() {
		return dataAcquisition.getSignalcharacteristics();
	}

	@Override
	public XMLGregorianCalendar getDate() {
		return dataAcquisition.getDate();
	}

	@Override
	public XMLGregorianCalendar getTime() {
		return dataAcquisition.getTime();
	}

	@Override
	public Object getStatflag() {
		return dataAcquisition.getStatflag();
	}
}
