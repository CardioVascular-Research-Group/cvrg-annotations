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
import org.sierraecg.schema.Groupmeasurement;
import org.sierraecg.schema.TYPEflag;
import org.sierraecg.schema.TYPEflag3;

import edu.jhu.cvrg.annotations.utilities.exceptions.IncorrectPhilipsVersionException;
import edu.jhu.cvrg.annotations.utilities.interfaces.GroupMeasurement;

public class Philips103GroupMeasurement implements GroupMeasurement {

	private Groupmeasurement groupmeasurement;
	
	public Philips103GroupMeasurement(Groupmeasurement groupMeasurement){
		this.groupmeasurement = groupMeasurement;
	}

	@Override
	public String getMembercount() {
		return groupmeasurement.getMembercount();
	}

	@Override
	public void setMembercount(String value) {
		groupmeasurement.setMembercount(value);
	}

	@Override
	public String getMemberpercent() {
		return groupmeasurement.getMemberpercent();
	}

	@Override
	public void setMemberpercent(String value) {
		groupmeasurement.setMemberpercent(value);
	}

	@Override
	public String getLongestrun() {
		return groupmeasurement.getLongestrun();
	}

	@Override
	public void setLongestrun(String value) {
		groupmeasurement.setLongestrun(value);
	}

	@Override
	public String getMeanqrsdur() {

		return groupmeasurement.getMeanqrsdur();
	}

	@Override
	public void setMeanqrsdur(String value) {

		groupmeasurement.setMeanqrsdur(value);
	}

	@Override
	public String getLowventrate() {

		return groupmeasurement.getLowventrate();
	}

	@Override
	public void setLowventrate(String value) {

		groupmeasurement.setLowventrate(value);
	}

	@Override
	public String getMeanventrate() {

		return groupmeasurement.getMeanventrate();
	}

	@Override
	public void setMeanventrate(String value) {

		groupmeasurement.setMeanventrate(value);
	}

	@Override
	public String getHighventrate() {

		return groupmeasurement.getHighventrate();
	}

	@Override
	public void setHighventrate(String value) {

		groupmeasurement.setHighventrate(value);
	}

	@Override
	public String getVentratestddev() {

		return groupmeasurement.getVentratestddev();
	}

	@Override
	public void setVentratestddev(String value) {

		groupmeasurement.setVentratestddev(value);
	}

	@Override
	public String getMeanrrint() {

		return groupmeasurement.getMeanrrint();
	}

	@Override
	public void setMeanrrint(String value) {

		groupmeasurement.setMeanrrint(value);
	}

	@Override
	public String getAtrialrate() {

		return groupmeasurement.getAtrialrate();
	}

	@Override
	public void setAtrialrate(String value) {

		groupmeasurement.setAtrialrate(value);
	}

	@Override
	public String getAtrialratestddev() {

		return groupmeasurement.getAtrialratestddev();
	}

	@Override
	public void setAtrialratestddev(String value) {

		groupmeasurement.setAtrialratestddev(value);
	}

	@Override
	public String getAvgpcount() {

		return groupmeasurement.getAvgpcount();
	}

	@Override
	public void setAvgpcount(String value) {

		groupmeasurement.setAvgpcount(value);
	}

	@Override
	public String getNotavgpbeats() {

		return groupmeasurement.getNotavgpbeats();
	}

	@Override
	public void setNotavgpbeats(String value) {

		groupmeasurement.setNotavgpbeats(value);
	}

	@Override
	public String getLowprint() {

		return groupmeasurement.getLowprint();
	}

	@Override
	public void setLowprint(String value) {

		groupmeasurement.setLowprint(value);
	}

	@Override
	public String getMeanprint() {

		return groupmeasurement.getMeanprint();
	}

	@Override
	public void setMeanprint(String value) {

		groupmeasurement.setMeanprint(value);
	}

	@Override
	public String getHighprint() {

		return groupmeasurement.getHighprint();
	}

	@Override
	public void setHighprint(String value) {

		groupmeasurement.setHighprint(value);
	}

	@Override
	public String getPrintstddev() {

		return groupmeasurement.getPrintstddev();
	}

	@Override
	public void setPrintstddev(String value) {

		groupmeasurement.setPrintstddev(value);
	}

	@Override
	public String getMeanprseg() {

		return groupmeasurement.getMeanprseg();
	}

	@Override
	public void setMeanprseg(String value) {

		groupmeasurement.setMeanprseg(value);
	}

	@Override
	public String getMeanqtint() {

		return groupmeasurement.getMeanqtint();
	}

	@Override
	public void setMeanqtint(String value) {

		groupmeasurement.setMeanqtint(value);
	}

	@Override
	public String getMeanqtseg() {

		return groupmeasurement.getMeanqtseg();
	}

	@Override
	public void setMeanqtseg(String value) {

		groupmeasurement.setMeanqtseg(value);
	}

	@Override
	public String getComppausecount() {

		return groupmeasurement.getComppausecount();
	}

	@Override
	public void setComppausecount(String value) {

		groupmeasurement.setComppausecount(value);
	}

	@Override
	public Object getNamedmeasurement() {
		try {
			throw new IncorrectPhilipsVersionException();
		} catch (IncorrectPhilipsVersionException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getGroupnumber() {

		return groupmeasurement.getGroupnumber();
	}

	@Override
	public void setGroupnumber(int value) {

		groupmeasurement.setGroupnumber(value);
	}

	@Override
	public TYPEflag getInterpflag() {

		return groupmeasurement.getInterpflag();
	}

	@Override
	public void setInterpflag(Object value) {
		groupmeasurement.setInterpflag((TYPEflag)value);
		
	}

	@Override
	public TYPEflag getSinusflag() {

		return groupmeasurement.getSinusflag();
	}

	@Override
	public void setSinusflag(Object value) {

		groupmeasurement.setSinusflag((TYPEflag)value);
	}

	@Override
	public TYPEflag getPrprogflag() {

		return groupmeasurement.getPrprogflag();
	}

	@Override
	public void setPrprogflag(Object value) {
		groupmeasurement.setPrprogflag((TYPEflag)value);
		
	}

	@Override
	public TYPEflag getWenckflag() {

		return groupmeasurement.getWenckflag();
	}

	@Override
	public void setWenckflag(Object value) {

		groupmeasurement.setWenckflag((TYPEflag)value);
	}

	@Override
	public TYPEflag getBigflag() {

		return groupmeasurement.getBigflag();
	}

	@Override
	public void setBigflag(Object value) {

		groupmeasurement.setBigflag((TYPEflag)value);
	}

	@Override
	public TYPEflag getTrigflag() {

		return groupmeasurement.getTrigflag();
	}

	@Override
	public void setTrigflag(Object value) {
		groupmeasurement.setTrigflag((TYPEflag)value);
		
	}

	@Override
	public TYPEflag getAberrantflag() {

		return groupmeasurement.getAberrantflag();
	}

	@Override
	public void setAberrantflag(Object value) {

		groupmeasurement.setAberrantflag((TYPEflag)value);
	}

	@Override
	public TYPEflag getMultptestflag() {

		return groupmeasurement.getMultptestflag();
	}

	@Override
	public void setMultptestflag(Object value) {
		groupmeasurement.setMultptestflag((TYPEflag)value);
		
	}

	@Override
	public TYPEflag getQrsmeasflag() {

		return groupmeasurement.getQrsmeasflag();
	}

	@Override
	public void setQrsmeasflag(Object value) {
		groupmeasurement.setQrsmeasflag((TYPEflag)value);
		
	}

	@Override
	public TYPEflag3 getAtrialpaceflag() {

		return groupmeasurement.getAtrialpaceflag();
	}

	@Override
	public void setAtrialpaceflag(Object value) {

		groupmeasurement.setAtrialpaceflag((TYPEflag3)value);
	}

	@Override
	public TYPEflag getVentdualpaceflag() {

		return groupmeasurement.getVentdualpaceflag();
	}

	@Override
	public void setVentdualpaceflag(Object value) {

		groupmeasurement.setVentdualpaceflag((TYPEflag)value);
	}
	
	public String getGroupreserved(){
		return groupmeasurement.getGroupreserved();
	}

}
