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
import org.cvrgrid.philips.jaxb.beans.Groupmeasurement;
import org.cvrgrid.philips.jaxb.beans.TYPEflag;
import org.cvrgrid.philips.jaxb.beans.TYPEflagUnk;

import edu.jhu.cvrg.annotations.utilities.exceptions.IncorrectPhilipsVersionException;
import edu.jhu.cvrg.annotations.utilities.interfaces.GroupMeasurement;

public class Philips104Groupmeasurement implements GroupMeasurement{

	private Groupmeasurement groupMeasurement;
	
	public Philips104Groupmeasurement(Groupmeasurement groupMeasurement){
		this.groupMeasurement = groupMeasurement;
	}

	@Override
	public String getMembercount() {
		return groupMeasurement.getMembercount();
	}

	@Override
	public void setMembercount(String value) {
		this.groupMeasurement.setMembercount(value);
	}

	@Override
	public String getMemberpercent() {

		return groupMeasurement.getMembercount();
	}

	@Override
	public void setMemberpercent(String value) {

		this.groupMeasurement.setMembercount(value);
	}

	@Override
	public String getLongestrun() {

		return groupMeasurement.getLongestrun();
	}

	@Override
	public void setLongestrun(String value) {

		this.groupMeasurement.setLongestrun(value);
	}

	@Override
	public String getMeanqrsdur() {

		return groupMeasurement.getMeanqrsdur();
	}

	@Override
	public void setMeanqrsdur(String value) {

		this.groupMeasurement.setMeanqrsdur(value);
	}

	@Override
	public String getLowventrate() {

		return groupMeasurement.getLowventrate();
	}

	@Override
	public void setLowventrate(String value) {
		this.groupMeasurement.setLowventrate(value);
		
	}

	@Override
	public String getMeanventrate() {

		return groupMeasurement.getMeanventrate();
	}

	@Override
	public void setMeanventrate(String value) {
		this.groupMeasurement.setMeanventrate(value);
		
	}

	@Override
	public String getHighventrate() {

		return groupMeasurement.getHighventrate();
	}

	@Override
	public void setHighventrate(String value) {
		this.groupMeasurement.setHighventrate(value);
		
	}

	@Override
	public String getVentratestddev() {

		return groupMeasurement.getVentratestddev();
	}

	@Override
	public void setVentratestddev(String value) {

		this.groupMeasurement.setVentratestddev(value);
	}

	@Override
	public String getMeanrrint() {

		return groupMeasurement.getMeanrrint();
	}

	@Override
	public void setMeanrrint(String value) {
		this.groupMeasurement.setMeanrrint(value);
		
	}

	@Override
	public String getAtrialrate() {

		return groupMeasurement.getAtrialrate();
	}

	@Override
	public void setAtrialrate(String value) {
		this.groupMeasurement.setAtrialrate(value);
		
	}

	@Override
	public String getAtrialratestddev() {

		return groupMeasurement.getAtrialratestddev();
	}

	@Override
	public void setAtrialratestddev(String value) {
		this.groupMeasurement.setAtrialratestddev(value);
		
	}

	@Override
	public String getAvgpcount() {

		return groupMeasurement.getAvgpcount();
	}

	@Override
	public void setAvgpcount(String value) {

		this.groupMeasurement.setAvgpcount(value);
	}

	@Override
	public String getNotavgpbeats() {

		return groupMeasurement.getNotavgpbeats();
	}

	@Override
	public void setNotavgpbeats(String value) {

		this.groupMeasurement.setNotavgpbeats(value);
	}

	@Override
	public String getLowprint() {

		return groupMeasurement.getLowprint();
	}

	@Override
	public void setLowprint(String value) {
		this.groupMeasurement.setLowprint(value);
		
	}

	@Override
	public String getMeanprint() {

		return groupMeasurement.getMeanprint();
	}

	@Override
	public void setMeanprint(String value) {
		this.groupMeasurement.setMeanprint(value);
		
	}

	@Override
	public String getHighprint() {

		return groupMeasurement.getHighprint();
	}

	@Override
	public void setHighprint(String value) {
		this.groupMeasurement.setHighprint(value);
		
	}

	@Override
	public String getPrintstddev() {

		return groupMeasurement.getPrintstddev();
	}

	@Override
	public void setPrintstddev(String value) {
		this.groupMeasurement.setPrintstddev(value);
		
	}

	@Override
	public String getMeanprseg() {

		return groupMeasurement.getMeanprseg();
	}

	@Override
	public void setMeanprseg(String value) {
		this.groupMeasurement.setMeanprseg(value);
		
	}

	@Override
	public String getMeanqtint() {

		return groupMeasurement.getMeanqtint();
	}

	@Override
	public void setMeanqtint(String value) {
		this.groupMeasurement.setMeanqtint(value);
		
	}

	@Override
	public String getMeanqtseg() {

		return groupMeasurement.getMeanqtseg();
	}

	@Override
	public void setMeanqtseg(String value) {
		this.groupMeasurement.setMeanqtseg(value);
		
	}

	@Override
	public String getComppausecount() {

		return groupMeasurement.getComppausecount();
	}

	@Override
	public void setComppausecount(String value) {
		this.groupMeasurement.setComppausecount(value);
		
	}

	@Override
	public Object getNamedmeasurement() {

		return groupMeasurement.getNamedmeasurement();
	}

	@Override
	public int getGroupnumber() {

		return groupMeasurement.getGroupnumber();
	}

	@Override
	public void setGroupnumber(int value) {

		this.groupMeasurement.setGroupnumber(value);
	}

	@Override
	public Object getInterpflag() {

		return groupMeasurement.getInterpflag();
	}

	@Override
	public void setInterpflag(Object value) {
		this.groupMeasurement.setInterpflag((TYPEflag)value);
		
	}

	@Override
	public Object getSinusflag() {

		return groupMeasurement.getSinusflag();
	}

	@Override
	public void setSinusflag(Object value) {
		this.groupMeasurement.setSinusflag((TYPEflag)value);
		
	}

	@Override
	public Object getPrprogflag() {

		return groupMeasurement.getPrprogflag();
	}

	@Override
	public void setPrprogflag(Object value) {

		this.groupMeasurement.setPrprogflag((TYPEflag)value);
	}

	@Override
	public Object getWenckflag() {

		return groupMeasurement.getWenckflag();
	}

	@Override
	public void setWenckflag(Object value) {
		this.groupMeasurement.setWenckflag((TYPEflag)value);
		
	}

	@Override
	public Object getBigflag() {

		return groupMeasurement.getBigflag();
	}

	@Override
	public void setBigflag(Object value) {

		this.groupMeasurement.setBigflag((TYPEflag)value);
	}

	@Override
	public Object getTrigflag() {

		return groupMeasurement.getTrigflag();
	}

	@Override
	public void setTrigflag(Object value) {
		this.groupMeasurement.setTrigflag((TYPEflag)value);
		
	}

	@Override
	public Object getAberrantflag() {

		return groupMeasurement.getAberrantflag();
	}

	@Override
	public void setAberrantflag(Object value) {

		this.groupMeasurement.setAberrantflag((TYPEflag)value);
	}

	@Override
	public Object getMultptestflag() {

		return groupMeasurement.getMultptestflag();
	}

	@Override
	public void setMultptestflag(Object value) {
		this.groupMeasurement.setMultptestflag((TYPEflag)value);
		
	}

	@Override
	public Object getQrsmeasflag() {

		return groupMeasurement.getQrsmeasflag();
	}

	@Override
	public void setQrsmeasflag(Object value) {
		this.groupMeasurement.setQrsmeasflag((TYPEflag)value);
		
	}

	@Override
	public Object getAtrialpaceflag() {

		return groupMeasurement.getAtrialpaceflag();
	}

	@Override
	public void setAtrialpaceflag(Object value) {

		this.groupMeasurement.setAtrialpaceflag((TYPEflagUnk)value);
	}

	@Override
	public Object getVentdualpaceflag() {

		return groupMeasurement.getVentdualpaceflag();
	}

	@Override
	public void setVentdualpaceflag(Object value) {

		this.groupMeasurement.setVentdualpaceflag((TYPEflag)value);
	}

	@Override
	public String getGroupreserved() {
		try {
			throw new IncorrectPhilipsVersionException();
		} catch (IncorrectPhilipsVersionException e) {
			e.printStackTrace();
		}
		return null;
	}
}
