package edu.jhu.cvrg.annotations.utilities.interfaces;
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

public interface OrderInfo {

	  public String getOrdernumber();
	  
	    public void setOrdernumber(String value);
	    
	    public String getUniqueorderid();
	    
	    public void setUniqueorderid(String value);
	    
	    public String getOrderbillingcode();
	    
	    public void setOrderbillingcode(String value);
	    
	    public String getOrderremarks();
	    
	    public void setOrderremarks(String value);
	    
	    public String getReasonfororder();
	    
	    public void setReasonfororder(String value);
	    
	    public Object getDrgcategories();
	    
	    public void setDrgcategories(Object value);
	    
	    public String getOrderstatus();
	    
	    public void setOrderstatus(String value);
	    
	    public String getInbox();
	    
	    public void setInbox(String value);
	    
	    public Object getOther();
	    
	    public String getPriority();
	    
	    public void setPriority(String value);
	    
	    public XMLGregorianCalendar getOrderrequestdate();
	    
	    public void setOrderrequestdate(XMLGregorianCalendar value);
	    
	    public XMLGregorianCalendar getOrderrequesttime();
	    
	    public void setOrderrequesttime(XMLGregorianCalendar value);
	    
	    public XMLGregorianCalendar getDateprocessed();
	    
	    public void setDateprocessed(XMLGregorianCalendar value);
	    
	    public XMLGregorianCalendar getTimeprocessed();
	    
	    public void setTimeprocessed(XMLGregorianCalendar value);
	    
	    public String getEncounterId();
	    
	    public String getOperatorid();
	    
	    public String getViperuniqueorderid();
	    
	    public String getOrderingclinicianname();
	    
	    public String getOrderingclinicianUPIN();
	    
}
