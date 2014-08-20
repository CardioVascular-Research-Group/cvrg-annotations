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
import org.cvrgrid.philips.jaxb.beans.Beats;
import org.cvrgrid.philips.jaxb.beans.Leadmeasurement;
import org.cvrgrid.philips.jaxb.beans.Leadqualitystates;
import org.cvrgrid.philips.jaxb.beans.Pacepulses;
import org.cvrgrid.philips.jaxb.beans.TYPEflag;
import org.cvrgrid.philips.jaxb.beans.TYPEflagUnk;
import org.cvrgrid.philips.jaxb.beans.TYPEnotch;
import org.cvrgrid.philips.jaxb.beans.TYPEstshape;

import edu.jhu.cvrg.services.broker.annotations.utilities.interfaces.LeadMeasurement;

public class Philips104LeadMeasurement implements LeadMeasurement{
	
	private Leadmeasurement leadMeasurement;

	public Philips104LeadMeasurement(Leadmeasurement leadMeasurement){
		this.leadMeasurement = leadMeasurement;
	}
	
	@Override
	public Object getPacepulses() {
		return leadMeasurement.getPacepulses();
	}

	@Override
	public void setPacepulses(Object value) {
		this.leadMeasurement.setPacepulses((Pacepulses)value);
		
	}

	@Override
	public Object getLeadqualitystates() {
		
		return leadMeasurement.getLeadqualitystates();
	}

	@Override
	public void setLeadqualitystates(Object value) {
		
		this.leadMeasurement.setLeadqualitystates((Leadqualitystates)value);
	}

	@Override
	public String getPamp() {
		
		return leadMeasurement.getPamp();
	}

	@Override
	public void setPamp(String value) {
		this.leadMeasurement.setPamp(value);
		
	}

	@Override
	public String getPdur() {
		
		return leadMeasurement.getPdur();
	}

	@Override
	public void setPdur(String value) {
		this.leadMeasurement.setPdur(value);
		
	}

	@Override
	public String getParea() {
		
		return leadMeasurement.getParea();
	}

	@Override
	public void setParea(String value) {
		
		this.leadMeasurement.setParea(value);
	}

	@Override
	public String getPpamp() {
		
		return leadMeasurement.getPpamp();
	}

	@Override
	public void setPpamp(String value) {
		
		this.leadMeasurement.setPpamp(value);
	}

	@Override
	public String getPpdur() {
		
		return leadMeasurement.getPpdur();
	}

	@Override
	public void setPpdur(String value) {
		this.leadMeasurement.setPpdur(value);
		
	}

	@Override
	public String getPpppdur() {
		
		return leadMeasurement.getPpppdur();
	}

	@Override
	public void setPpppdur(String value) {
		
		this.leadMeasurement.setPpppdur(value);
	}

	@Override
	public String getPparea() {
		
		return leadMeasurement.getPparea();
	}

	@Override
	public void setPparea(String value) {
		
		this.leadMeasurement.setPparea(value);
	}

	@Override
	public String getPppparea() {
		
		return leadMeasurement.getPppparea();
	}

	@Override
	public void setPppparea(String value) {
		
		this.leadMeasurement.setPppparea(value);
	}

	@Override
	public String getQamp() {
		
		return leadMeasurement.getQamp();
	}

	@Override
	public void setQamp(String value) {
		this.leadMeasurement.setQamp(value);
		
	}

	@Override
	public String getQdur() {
		
		return leadMeasurement.getQdur();
	}

	@Override
	public void setQdur(String value) {
		
		this.leadMeasurement.setQdur(value);
	}

	@Override
	public String getRamp() {
		
		return leadMeasurement.getRamp();
	}

	@Override
	public void setRamp(String value) {
		this.leadMeasurement.setRamp(value);
		
	}

	@Override
	public String getRdur() {
		
		return leadMeasurement.getRdur();
	}

	@Override
	public void setRdur(String value) {
		
		this.leadMeasurement.setRdur(value);
	}

	@Override
	public String getSamp() {
		
		return leadMeasurement.getSamp();
	}

	@Override
	public void setSamp(String value) {
		this.leadMeasurement.setSamp(value);
		
	}

	@Override
	public String getSdur() {
		
		return leadMeasurement.getSdur();
	}

	@Override
	public void setSdur(String value) {
		this.leadMeasurement.setSdur(value);
		
	}

	@Override
	public String getRpamp() {
		
		return leadMeasurement.getRpamp();
	}

	@Override
	public void setRpamp(String value) {
		this.leadMeasurement.setRpamp(value);
		
	}

	@Override
	public String getRpdur() {
		
		return leadMeasurement.getRpdur();
	}

	@Override
	public void setRpdur(String value) {
		
		this.leadMeasurement.setRpdur(value);
	}

	@Override
	public String getSpamp() {
		
		return leadMeasurement.getSpamp();
	}

	@Override
	public void setSpamp(String value) {
		
		this.leadMeasurement.setSpamp(value);
	}

	@Override
	public String getSpdur() {
		
		return leadMeasurement.getSpdur();
	}

	@Override
	public void setSpdur(String value) {
		
		this.leadMeasurement.setSpdur(value);
	}

	@Override
	public String getVat() {
		
		return leadMeasurement.getVat();
	}

	@Override
	public void setVat(String value) {
		
		this.leadMeasurement.setVat(value);
	}

	@Override
	public String getQrsppk() {
		
		return leadMeasurement.getQrsppk();
	}

	@Override
	public void setQrsppk(String value) {
		
		this.leadMeasurement.setQrsppk(value);
	}

	@Override
	public String getQrsdur() {
		
		return leadMeasurement.getQrsdur();
	}

	@Override
	public void setQrsdur(String value) {
		this.leadMeasurement.setQrsdur(value);
		
	}

	@Override
	public String getQrsarea() {
		
		return leadMeasurement.getQrsarea();
	}

	@Override
	public void setQrsarea(String value) {
		
		this.leadMeasurement.setQrsarea(value);
	}

	@Override
	public String getSton() {
		
		return leadMeasurement.getSton();
	}

	@Override
	public void setSton(String value) {
		this.leadMeasurement.setSton(value);
		
	}

	@Override
	public String getStmid() {
		
		return leadMeasurement.getStmid();
	}

	@Override
	public void setStmid(String value) {
		
		this.leadMeasurement.setStmid(value);
	}

	@Override
	public String getSt80() {
		
		return leadMeasurement.getSt80();
	}

	@Override
	public void setSt80(String value) {
		this.leadMeasurement.setSt80(value);
		
	}

	@Override
	public String getStend() {
		
		return leadMeasurement.getStend();
	}

	@Override
	public void setStend(String value) {
		this.leadMeasurement.setStend(value);
		
	}

	@Override
	public String getStdur() {
		
		return leadMeasurement.getStdur();
	}

	@Override
	public void setStdur(String value) {
		this.leadMeasurement.setStdur(value);
		
	}

	@Override
	public String getStslope() {
		
		return leadMeasurement.getStslope();
	}

	@Override
	public void setStslope(String value) {
		this.leadMeasurement.setStslope(value);
		
	}

	@Override
	public Object getStshape() {
		
		return leadMeasurement.getStshape();
	}

	@Override
	public void setStshape(Object value) {
		this.leadMeasurement.setStshape((TYPEstshape)value);
		
	}

	@Override
	public String getTamp() {
		
		return leadMeasurement.getTamp();
	}

	@Override
	public void setTamp(String value) {
		
		this.leadMeasurement.setTamp(value);
	}

	@Override
	public String getTdur() {
		
		return leadMeasurement.getTdur();
	}

	@Override
	public void setTdur(String value) {
		this.leadMeasurement.setTdur(value);
		
	}

	@Override
	public String getTarea() {
		
		return leadMeasurement.getTarea();
	}

	@Override
	public void setTarea(String value) {
		this.leadMeasurement.setTarea(value);
		
	}

	@Override
	public String getTpamp() {
		
		return leadMeasurement.getTpamp();
	}

	@Override
	public void setTpamp(String value) {
		
		this.leadMeasurement.setTpamp(value);
	}

	@Override
	public String getTptpdur() {
		
		return leadMeasurement.getTptpdur();
	}

	@Override
	public void setTptpdur(String value) {
		this.leadMeasurement.setTptpdur(value);
		
	}

	@Override
	public String getTpdur() {
		
		return leadMeasurement.getTpdur();
	}

	@Override
	public void setTpdur(String value) {
		
		this.leadMeasurement.setTpdur(value);
	}

	@Override
	public String getTparea() {
		
		return leadMeasurement.getTparea();
	}

	@Override
	public void setTparea(String value) {
		this.leadMeasurement.setTparea(value);
		
	}

	@Override
	public String getTptparea() {
		
		return leadMeasurement.getTparea();
	}

	@Override
	public void setTptparea(String value) {
		
		this.leadMeasurement.setTptparea(value);
	}

	@Override
	public String getPrint() {
		
		return leadMeasurement.getPrint();
	}

	@Override
	public void setPrint(String value) {
		
		this.leadMeasurement.setPrint(value);
	}

	@Override
	public String getPrseg() {
		
		return leadMeasurement.getPrseg();
	}

	@Override
	public void setPrseg(String value) {
		
		this.leadMeasurement.setPrseg(value);
	}

	@Override
	public String getQtint() {
		
		return leadMeasurement.getQtint();
	}

	@Override
	public void setQtint(String value) {
		
		this.leadMeasurement.setQtint(value);
	}

	@Override
	public Object getBeats() {
		
		return leadMeasurement.getBeats();
	}

	@Override
	public void setBeats(Object value) {
		
		this.leadMeasurement.setBeats((Beats)value);
	}

	@Override
	public Object getNamedmeasurement() {
		
		return leadMeasurement.getNamedmeasurement();
	}

	@Override
	public String getLeadname() {
		
		return leadMeasurement.getLeadname();
	}

	@Override
	public void setLeadname(String value) {
		
		this.leadMeasurement.setLeadname(value);
	}

	@Override
	public Object getPexistflag() {
		
		return leadMeasurement.getPexistflag();
	}

	@Override
	public void setPexistflag(Object value) {
		this.leadMeasurement.setPexistflag((TYPEflag)value);
		
	}

	@Override
	public Object getPmeasflag() {
		
		return leadMeasurement.getPmeasflag();
	}

	@Override
	public void setPmeasflag(Object value) {
		
		this.leadMeasurement.setPmeasflag((TYPEflag)value);
	}

	@Override
	public Object getPnotchflag() {
		
		return leadMeasurement.getPnotchflag();
	}

	@Override
	public void setPnotchflag(Object value) {
		
		this.leadMeasurement.setPnotchflag((TYPEflag)value);
	}

	@Override
	public Object getQrsexistflag() {
		
		return leadMeasurement.getQrsexistflag();
	}

	@Override
	public void setQrsexistflag(Object value) {
		
		this.leadMeasurement.setQrsexistflag((TYPEflag)value);
	}

	@Override
	public Object getQrsspikeflag() {
		
		return leadMeasurement.getQrsspikeflag();
	}

	@Override
	public void setQrsspikeflag(Object value) {
		
		this.leadMeasurement.setQrsspikeflag((TYPEflag)value);
	}

	@Override
	public Object getQrsmeasflag() {
		
		return leadMeasurement.getQrsmeasflag();
	}

	@Override
	public void setQrsmeasflag(Object value) {
		
		this.leadMeasurement.setQrsmeasflag((TYPEflag)value);
	}

	@Override
	public Object getQrsnotchflag() {
		
		return leadMeasurement.getQrsnotchflag();
	}

	@Override
	public void setQrsnotchflag(Object value) {
		this.leadMeasurement.setQrsnotchflag((TYPEnotch)value);
		
	}

	@Override
	public Object getQrsdeltaflag() {
		
		return leadMeasurement.getQrsdeltaflag();
	}

	@Override
	public void setQrsdeltaflag(Object value) {
		
		this.leadMeasurement.setQrsdeltaflag((TYPEflag)value);
	}

	@Override
	public Object getStexistflag() {
		
		return leadMeasurement.getStexistflag();
	}

	@Override
	public void setStexistflag(Object value) {
		
		this.leadMeasurement.setStexistflag((TYPEflag)value);
	}

	@Override
	public Object getStmeasflag() {
		
		return leadMeasurement.getStmeasflag();
	}

	@Override
	public void setStmeasflag(Object value) {
		this.leadMeasurement.setStmeasflag((TYPEflag)value);
		
	}

	@Override
	public Object getTexistflag() {
		
		return leadMeasurement.getTexistflag();
	}

	@Override
	public void setTexistflag(Object value) {
		this.leadMeasurement.setTexistflag((TYPEflag)value);
		
	}

	@Override
	public Object getTmeasflag() {
		
		return leadMeasurement.getTmeasflag();
	}

	@Override
	public void setTmeasflag(Object value) {
		
		this.leadMeasurement.setTmeasflag((TYPEflag)value);
	}

	@Override
	public Object getTnotchflag() {
		
		return leadMeasurement.getTnotchflag();
	}

	@Override
	public void setTnotchflag(Object value) {
		
		this.leadMeasurement.setTnotchflag((TYPEflag)value);
	}

	@Override
	public Object getAtrialpaceflag() {
		
		return leadMeasurement.getAtrialpaceflag();
	}

	@Override
	public void setAtrialpaceflag(Object value) {
		
		this.leadMeasurement.setAtrialpaceflag((TYPEflagUnk)value);
	}

	@Override
	public Object getVentpaceflag() {
		
		return leadMeasurement.getVentpaceflag();
	}

	@Override
	public void setVentpaceflag(Object value) {
		this.leadMeasurement.setVentpaceflag((TYPEflag)value);
		
	}

	
}
