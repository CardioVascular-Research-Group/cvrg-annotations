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
import org.sierraecg.schema.*;

import edu.jhu.cvrg.services.broker.annotations.utilities.exceptions.IncorrectPhilipsVersionException;
import edu.jhu.cvrg.services.broker.annotations.utilities.interfaces.RestingECGData;

public class Philips103RestingECGData implements RestingECGData {
	
	private Restingecgdata restingECGData;
	
	public Restingecgdata getRestingecgdata(){
		return restingECGData;
	}
	
	public void setRestingecgdata(Restingecgdata restingECGData){
		this.restingECGData = restingECGData;
	}
	
	public Philips103RestingECGData(Restingecgdata restingECGData){
		this.restingECGData = restingECGData;
	}

	@Override
	public Object getDocumentinfo() {
		return restingECGData.getDocumentinfo();
	}

	@Override
	public void setDocumentinfo(Object value) {
		restingECGData.setDocumentinfo((Documentinfo)value);
	}

	@Override
	public Object getUserdefines() {
		return restingECGData.getUserdefines();
	}

	@Override
	public void setUserdefines(Object value) {
		restingECGData.setUserdefines((Userdefines)value);

	}

	@Override
	public Object getOrderinfo() {
		return restingECGData.getOrderinfo();
	}

	@Override
	public void setOrderinfo(Object value) {
		restingECGData.setOrderinfo((Orderinfo)value);
	}

	@Override
	public Object getOtherECGs() {
		try {
			throw new IncorrectPhilipsVersionException();
		} catch (IncorrectPhilipsVersionException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void setOtherECGs(Object value) {
		try {
			throw new IncorrectPhilipsVersionException();
		} catch (IncorrectPhilipsVersionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object getReportinfo() {
		return restingECGData.getReportinfo();
	}

	@Override
	public void setReportinfo(Object value) {
		restingECGData.setReportinfo((Reportinfo)value);
	}

	@Override
	public Object getDataacquisition() {
		return restingECGData.getDataacquisition();
	}

	@Override
	public void setDataacquisition(Object value) {
		restingECGData.setDataacquisition((Dataacquisition)value);
	}

	@Override
	public Object getPatient() {
		return restingECGData.getPatient();
	}

	@Override
	public void setPatient(Object value) {
		restingECGData.setPatient((Patient)value);
	}

	@Override
	public Object getInternalmeasurements() {
		try {
			throw new IncorrectPhilipsVersionException();
		} catch (IncorrectPhilipsVersionException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void setInternalmeasurements(Object value) {
		try {
			throw new IncorrectPhilipsVersionException();
		} catch (IncorrectPhilipsVersionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object getInterpretations() {
		return restingECGData.getInterpretations();
	}

	@Override
	public void setInterpretations(Object value) {
		restingECGData.setInterpretations((Interpretations)value);
	}

	@Override
	public Object getWaveforms() {
		return restingECGData.getWaveforms();
	}

	@Override
	public void setWaveforms(Object value) {
		restingECGData.setWaveforms((Waveforms)value);
	}

	@Override
	public String getCrc() {
		return restingECGData.getCrc();
	}

	@Override
	public void setCrc(String value) {
		restingECGData.setCrc(value);
	}

	@Override
	public Object getStatus() {
		return restingECGData.getStatus();
	}

	@Override
	public void setStatus(Object value) {
		restingECGData.setStatus((TYPErestingecgstatus)value);
	}

	@Override
	public String getLang() {
		return restingECGData.getLang();
	}

	@Override
	public void setLang(String value) {
		restingECGData.setLang(value);
	}

	@Override
	public String getLocale() {
		return restingECGData.getLocale();
	}

	@Override
	public void setLocale(String value) {
		restingECGData.setLocale(value);
	}

}
