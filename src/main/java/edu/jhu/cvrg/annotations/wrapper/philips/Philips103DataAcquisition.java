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

import org.sierraecg.schema.Acquirer;
import org.sierraecg.schema.Dataacquisition;
import org.sierraecg.schema.Signalcharacteristics;
import org.sierraecg.schema.TYPEflag;

import edu.jhu.cvrg.annotations.utilities.exceptions.IncorrectPhilipsVersionException;
import edu.jhu.cvrg.annotations.utilities.interfaces.DataAcquisition;

public class Philips103DataAcquisition implements DataAcquisition {
	
	private Dataacquisition dataAcquisition;
	
	public Philips103DataAcquisition(Dataacquisition dataAcquisition){
		this.dataAcquisition = dataAcquisition;
	}

	@Override
	public String getDatabaseid() {
		return dataAcquisition.getEmsdatabaseid();
	}

	@Override
	public String getModality() {
		try {
			throw new IncorrectPhilipsVersionException();
		} catch (IncorrectPhilipsVersionException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getMachineId() {
		return dataAcquisition.getMachine().getMachineid();
	}

	@Override
	public Acquirer getAcquirer() {
		return dataAcquisition.getAcquirer();
	}

	@Override
	public Signalcharacteristics getSignalcharacteristics() {
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
	public TYPEflag getStatflag() {
		return dataAcquisition.getStatflag();
	}
}
