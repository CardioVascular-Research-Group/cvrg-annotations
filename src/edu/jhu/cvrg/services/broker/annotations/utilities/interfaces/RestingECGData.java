package edu.jhu.cvrg.services.broker.annotations.utilities.interfaces;
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
public interface RestingECGData {

	  	public Object getDocumentinfo();
	  
	    public void setDocumentinfo(Object value);
	    
	    public Object getUserdefines();
	    
	    public void setUserdefines(Object value);
	    
	    public Object getOrderinfo();
	    
	    public void setOrderinfo(Object value);
	    
	    public Object getOtherECGs();
	    
	    public void setOtherECGs(Object value);
	    
	    public Object getReportinfo();
	    
	    public void setReportinfo(Object value);
	    
	    public Object getDataacquisition();
	    
	    public void setDataacquisition(Object value);
	    
	    public Object getPatient();
	    
	    public void setPatient(Object value);
	    
	    public Object getInternalmeasurements();
	    
	    public void setInternalmeasurements(Object value);
	    
	    public Object getInterpretations();
	    
	    public void setInterpretations(Object value);
	    
	    public Object getWaveforms();
	    
	    public void setWaveforms(Object value);
	    
	    public String getCrc();
	    
	    public void setCrc(String value);
	    
	    public Object getStatus();
	    
	    public void setStatus(Object value);
	    
	    public String getLang();
	    
	    public void setLang(String value);
	    
	    public String getLocale();
	    
	    public void setLocale(String value);
}
