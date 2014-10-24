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

import org.cvrgrid.philips.jaxb.beans.Drgcategories;
import org.cvrgrid.philips.jaxb.beans.Orderinfo;

import edu.jhu.cvrg.annotations.utilities.exceptions.IncorrectPhilipsVersionException;
import edu.jhu.cvrg.annotations.utilities.interfaces.OrderInfo;

public class Philips104OrderInfo implements OrderInfo{

	private Orderinfo orderInfo;
	
	public Philips104OrderInfo(Orderinfo orderInfo){
		this.orderInfo = orderInfo;
	}

	@Override
	public String getOrdernumber() {
		
		return orderInfo.getOrdernumber();
	}

	@Override
	public void setOrdernumber(String value) {
		this.orderInfo.setOrdernumber(value);
		
	}

	@Override
	public String getUniqueorderid() {
		
		return orderInfo.getUniqueorderid();
	}

	@Override
	public void setUniqueorderid(String value) {
		this.orderInfo.setUniqueorderid(value);
		
	}

	@Override
	public String getOrderbillingcode() {
		
		return orderInfo.getOrderbillingcode();
	}

	@Override
	public void setOrderbillingcode(String value) {
		
		this.orderInfo.setOrderbillingcode(value);
	}

	@Override
	public String getOrderremarks() {
		
		return orderInfo.getOrderremarks();
	}

	@Override
	public void setOrderremarks(String value) {
		this.orderInfo.setOrderremarks(value);
		
	}

	@Override
	public String getReasonfororder() {
		
		return orderInfo.getReasonfororder();
	}

	@Override
	public void setReasonfororder(String value) {
		this.orderInfo.setReasonfororder(value);
		
	}

	@Override
	public Object getDrgcategories() {
		
		return orderInfo.getDrgcategories();
	}

	@Override
	public void setDrgcategories(Object value) {
		
		this.orderInfo.setDrgcategories((Drgcategories)value);
	}

	@Override
	public String getOrderstatus() {
		
		return orderInfo.getOrderstatus();
	}

	@Override
	public void setOrderstatus(String value) {
		
		this.orderInfo.setOrderstatus(value);
	}

	@Override
	public String getInbox() {
		
		return orderInfo.getInbox();
	}

	@Override
	public void setInbox(String value) {
		this.orderInfo.setInbox(value);
		
	}

	@Override
	public Object getOther() {
		
		return orderInfo.getOther();
	}

	@Override
	public String getPriority() {
		
		return orderInfo.getPriority();
	}

	@Override
	public void setPriority(String value) {
		
		this.orderInfo.setPriority(value);
	}

	@Override
	public XMLGregorianCalendar getOrderrequestdate() {
		
		return orderInfo.getOrderrequestdate();
	}

	@Override
	public void setOrderrequestdate(XMLGregorianCalendar value) {
		
		this.orderInfo.setOrderrequestdate(value);
	}

	@Override
	public XMLGregorianCalendar getOrderrequesttime() {
		
		return orderInfo.getOrderrequesttime();
	}

	@Override
	public void setOrderrequesttime(XMLGregorianCalendar value) {
		
		this.orderInfo.setOrderrequesttime(value);
	}

	@Override
	public XMLGregorianCalendar getDateprocessed() {
		
		return orderInfo.getDateprocessed();
	}

	@Override
	public void setDateprocessed(XMLGregorianCalendar value) {
		
		this.orderInfo.setDateprocessed(value);
	}

	@Override
	public XMLGregorianCalendar getTimeprocessed() {
		
		return orderInfo.getTimeprocessed();
	}

	@Override
	public void setTimeprocessed(XMLGregorianCalendar value) {
		
		this.orderInfo.setTimeprocessed(value);
	}

	@Override
	public String getEncounterId() {
		try {
			throw new IncorrectPhilipsVersionException();
		} catch (IncorrectPhilipsVersionException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getOperatorid() {
		try {
			throw new IncorrectPhilipsVersionException();
		} catch (IncorrectPhilipsVersionException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getViperuniqueorderid() {
		try {
			throw new IncorrectPhilipsVersionException();
		} catch (IncorrectPhilipsVersionException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getOrderingclinicianname() {
		try {
			throw new IncorrectPhilipsVersionException();
		} catch (IncorrectPhilipsVersionException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getOrderingclinicianUPIN() {
		try {
			throw new IncorrectPhilipsVersionException();
		} catch (IncorrectPhilipsVersionException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
