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

import org.sierraecg.schema.Drgcategories;
import org.sierraecg.schema.Orderinfo;

import edu.jhu.cvrg.annotations.utilities.exceptions.IncorrectPhilipsVersionException;
import edu.jhu.cvrg.annotations.utilities.interfaces.OrderInfo;

public class Philips103OrderInfo implements OrderInfo {

	private Orderinfo orderInfo;
	
	public Philips103OrderInfo(Orderinfo orderInfo){
		this.orderInfo = orderInfo;
	}
	
	@Override
	public String getOrdernumber() {
		
		return orderInfo.getOrdernumber();
	}

	@Override
	public void setOrdernumber(String value) {
		
		orderInfo.setOrdernumber(value);
	}
	
	@Override
	public String getEncounterId(){
		return orderInfo.getEncounterid();
	}

	@Override
	public String getUniqueorderid() {
		
		try {
			throw new IncorrectPhilipsVersionException();
		} catch (IncorrectPhilipsVersionException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void setUniqueorderid(String value) {
		try {
			throw new IncorrectPhilipsVersionException();
		} catch (IncorrectPhilipsVersionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getOrderbillingcode() {
		
		try {
			throw new IncorrectPhilipsVersionException();
		} catch (IncorrectPhilipsVersionException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void setOrderbillingcode(String value) {
		try {
			throw new IncorrectPhilipsVersionException();
		} catch (IncorrectPhilipsVersionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getOrderremarks() {
		
		try {
			throw new IncorrectPhilipsVersionException();
		} catch (IncorrectPhilipsVersionException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void setOrderremarks(String value) {
		try {
			throw new IncorrectPhilipsVersionException();
		} catch (IncorrectPhilipsVersionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getReasonfororder() {
		
		return orderInfo.getReasonfororder();
	}

	@Override
	public void setReasonfororder(String value) {
		
		orderInfo.setReasonfororder(value);
	}

	@Override
	public Drgcategories getDrgcategories() {
		
		return orderInfo.getDrgcategories();
	}

	@Override
	public void setDrgcategories(Object value) {
		
		orderInfo.setDrgcategories((Drgcategories)value);
	}

	@Override
	public String getOrderstatus() {
		
		
		try {
			throw new IncorrectPhilipsVersionException();
		} catch (IncorrectPhilipsVersionException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void setOrderstatus(String value) {
		
		try {
			throw new IncorrectPhilipsVersionException();
		} catch (IncorrectPhilipsVersionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getInbox() {
		
		try {
			throw new IncorrectPhilipsVersionException();
		} catch (IncorrectPhilipsVersionException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void setInbox(String value) {
		
		try {
			throw new IncorrectPhilipsVersionException();
		} catch (IncorrectPhilipsVersionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object getOther() {
		try {
			throw new IncorrectPhilipsVersionException();
		} catch (IncorrectPhilipsVersionException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getPriority() {
		
		return orderInfo.getPriority();
	}

	@Override
	public void setPriority(String value) {
		
		orderInfo.setPriority(value);
	}

	@Override
	public XMLGregorianCalendar getOrderrequestdate() {
		
		try {
			throw new IncorrectPhilipsVersionException();
		} catch (IncorrectPhilipsVersionException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void setOrderrequestdate(XMLGregorianCalendar value) {
		
		try {
			throw new IncorrectPhilipsVersionException();
		} catch (IncorrectPhilipsVersionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public XMLGregorianCalendar getOrderrequesttime() {
		
		try {
			throw new IncorrectPhilipsVersionException();
		} catch (IncorrectPhilipsVersionException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void setOrderrequesttime(XMLGregorianCalendar value) {
		
		try {
			throw new IncorrectPhilipsVersionException();
		} catch (IncorrectPhilipsVersionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public XMLGregorianCalendar getDateprocessed() {
		
		return orderInfo.getDateprocessed();
	}

	@Override
	public void setDateprocessed(XMLGregorianCalendar value) {
		
		orderInfo.setDateprocessed(value);
	}

	@Override
	public XMLGregorianCalendar getTimeprocessed() {
		
		return orderInfo.getTimeprocessed();
	}

	@Override
	public void setTimeprocessed(XMLGregorianCalendar value) {
		
		orderInfo.setTimeprocessed(value);
	}

	@Override
	public String getOperatorid() {
		return orderInfo.getOperatorid();
	}

	@Override
	public String getViperuniqueorderid() {
		return orderInfo.getViperuniqueorderid();
	}

	@Override
	public String getOrderingclinicianname() {
		return orderInfo.getOrderingclinicianname();
	}

	@Override
	public String getOrderingclinicianUPIN() {
		return orderInfo.getOrderingclinicianUPIN();
	}

}
