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
import org.cvrgrid.philips.jaxb.beans.Dataacquisition;
import org.cvrgrid.philips.jaxb.beans.Documentinfo;
import org.cvrgrid.philips.jaxb.beans.Interpretations;
import org.cvrgrid.philips.jaxb.beans.Orderinfo;
import org.cvrgrid.philips.jaxb.beans.OtherECGs;
import org.cvrgrid.philips.jaxb.beans.Patient;
import org.cvrgrid.philips.jaxb.beans.Reportinfo;
import org.cvrgrid.philips.jaxb.beans.Restingecgdata;
import org.cvrgrid.philips.jaxb.beans.TYPErestingecgstatus;
import org.cvrgrid.philips.jaxb.beans.Userdefines;
import org.cvrgrid.philips.jaxb.beans.Waveforms;

import edu.jhu.cvrg.annotations.utilities.interfaces.RestingECGData;


public class Philips104RestingECGData implements RestingECGData{

	private Restingecgdata restingECGData;
	
	public Philips104RestingECGData(Restingecgdata restingECGData){
		this.restingECGData = restingECGData;
	}

	@Override
	public Object getDocumentinfo() {

		return restingECGData.getDocumentinfo();
	}

	@Override
	public void setDocumentinfo(Object value) {

		this.restingECGData.setDocumentinfo((Documentinfo)value);
	}

	@Override
	public Object getUserdefines() {

		return restingECGData.getUserdefines();
	}

	@Override
	public void setUserdefines(Object value) {
		this.restingECGData.setUserdefines((Userdefines)value);	
	}

	@Override
	public Object getOrderinfo() {
		return restingECGData.getOrderinfo();
	}

	@Override
	public void setOrderinfo(Object value) {
		this.restingECGData.setOrderinfo((Orderinfo)value);
		
	}

	@Override
	public Object getOtherECGs() {
		return restingECGData.getOtherECGs();
	}

	@Override
	public void setOtherECGs(Object value) {

		this.restingECGData.setOtherECGs((OtherECGs)value);
	}

	@Override
	public Object getReportinfo() {

		return restingECGData.getReportinfo();
	}

	@Override
	public void setReportinfo(Object value) {

		this.restingECGData.setReportinfo((Reportinfo)value);
	}

	@Override
	public Object getDataacquisition() {

		return restingECGData.getDataacquisition();
	}

	@Override
	public void setDataacquisition(Object value) {
		this.restingECGData.setDataacquisition((Dataacquisition)value);
		
	}

	@Override
	public Object getPatient() {

		return restingECGData.getPatient();
	}

	@Override
	public void setPatient(Object value) {
		this.restingECGData.setPatient((Patient)value);
		
	}

	@Override
	public Object getInternalmeasurements() {
		return restingECGData.getInternalmeasurements();
	}

	@Override
	public Object getInterpretations() {

		return restingECGData.getInterpretations();
	}

	@Override
	public void setInterpretations(Object value) {

		this.restingECGData.setInterpretations((Interpretations)value);
	}

	@Override
	public Object getWaveforms() {

		return restingECGData.getWaveforms();
	}

	@Override
	public void setWaveforms(Object value) {
		this.restingECGData.setWaveforms((Waveforms)value);
		
	}

	@Override
	public String getCrc() {

		return restingECGData.getCrc();
	}

	@Override
	public void setCrc(String value) {

		this.restingECGData.setCrc(value);
	}

	@Override
	public Object getStatus() {

		return restingECGData.getStatus();
	}

	@Override
	public void setStatus(Object value) {
		this.restingECGData.setStatus((TYPErestingecgstatus)value);
		
	}

	@Override
	public String getLang() {

		return restingECGData.getLang();
	}

	@Override
	public void setLang(String value) {
		this.restingECGData.setLang(value);
		
	}

	@Override
	public String getLocale() {

		return restingECGData.getLocale();
	}

	@Override
	public void setLocale(String value) {

		this.restingECGData.setLocale(value);
	}

}
