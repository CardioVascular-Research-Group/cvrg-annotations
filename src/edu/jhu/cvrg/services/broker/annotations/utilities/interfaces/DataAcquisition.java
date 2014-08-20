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
import javax.xml.datatype.XMLGregorianCalendar;

public interface DataAcquisition {

	   public String getDatabaseid();
	   
	    public void setDatabaseid(String value);
	    
	    public String getModality();
	    
	    public void setModality(String value);
	    
	    public Object getMachine();
	    
	    public void setMachine(Object value);
	    
	    public Object getAcquirer();
	    
	    public void setAcquirer(Object value);
	    
	    public Object getSignalcharacteristics();
	    
	    public void setSignalcharacteristics(Object value);
	    
	    public XMLGregorianCalendar getDate();
	    
	    public void setDate(XMLGregorianCalendar value);
	    
	    public XMLGregorianCalendar getTime();
	    
	    public void setTime(XMLGregorianCalendar value);
	    
	    public Object getStatflag();
	    
	    public void setStatflag(Object value);

	
}
