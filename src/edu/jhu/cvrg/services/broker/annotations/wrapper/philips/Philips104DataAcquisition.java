package edu.jhu.cvrg.services.broker.annotations.wrapper.philips;
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

import org.cvrgrid.philips.jaxb.beans.Acquirer;
import org.cvrgrid.philips.jaxb.beans.Dataacquisition;
import org.cvrgrid.philips.jaxb.beans.Machine;
import org.cvrgrid.philips.jaxb.beans.Signalcharacteristics;
import org.cvrgrid.philips.jaxb.beans.TYPEflag;

import edu.jhu.cvrg.services.broker.annotations.utilities.interfaces.DataAcquisition;

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
	public void setDatabaseid(String value) {
		this.dataAcquisition.setDatabaseid(value);
	}

	@Override
	public String getModality() {
		return dataAcquisition.getModality();
	}

	@Override
	public void setModality(String value) {
		this.dataAcquisition.setModality(value);
	}

	@Override
	public Object getMachine() {
		return dataAcquisition.getMachine();
	}

	@Override
	public void setMachine(Object value) {
		this.dataAcquisition.setMachine((Machine)value);
	}

	@Override
	public Object getAcquirer() {
		return dataAcquisition.getAcquirer();
	}

	@Override
	public void setAcquirer(Object value) {
		this.dataAcquisition.setAcquirer((Acquirer)value);
	}

	@Override
	public Object getSignalcharacteristics() {
		
		return dataAcquisition.getSignalcharacteristics();
	}

	@Override
	public void setSignalcharacteristics(Object value) {
		this.dataAcquisition.setSignalcharacteristics((Signalcharacteristics)value);
		
	}

	@Override
	public XMLGregorianCalendar getDate() {
		
		return dataAcquisition.getDate();
	}

	@Override
	public void setDate(XMLGregorianCalendar value) {
		
		this.dataAcquisition.setDate(value);
	}

	@Override
	public XMLGregorianCalendar getTime() {
		
		return dataAcquisition.getTime();
	}

	@Override
	public void setTime(XMLGregorianCalendar value) {
		this.dataAcquisition.setTime(value);
		
	}

	@Override
	public Object getStatflag() {
		
		return dataAcquisition.getStatflag();
	}

	@Override
	public void setStatflag(Object value) {
		dataAcquisition.setStatflag((TYPEflag)value);	
	}
	
	
}
