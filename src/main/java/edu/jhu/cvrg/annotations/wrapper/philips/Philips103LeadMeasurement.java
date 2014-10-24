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
import org.sierraecg.schema.Leadmeasurement;
import org.sierraecg.schema.Leadqualitystates;
import org.sierraecg.schema.Pacepulses;
import org.sierraecg.schema.TYPEflag;
import org.sierraecg.schema.TYPEflag2;
import org.sierraecg.schema.TYPEflag3;
import org.sierraecg.schema.TYPEstshape;

import edu.jhu.cvrg.annotations.utilities.exceptions.IncorrectPhilipsVersionException;
import edu.jhu.cvrg.annotations.utilities.interfaces.LeadMeasurement;

//Wrapper class to handle the sierraecg version of Leadmeasurement
public class Philips103LeadMeasurement implements LeadMeasurement {
	
	private Leadmeasurement leadMeasurement;
	
	public Philips103LeadMeasurement(Leadmeasurement leadMeasurement){
		this.leadMeasurement = leadMeasurement;
	}

	@Override
	public Pacepulses getPacepulses() {
		return leadMeasurement.getPacepulses();
	}

	@Override
	public void setPacepulses(Object value) {
		leadMeasurement.setPacepulses((Pacepulses)value);
	}

	@Override
	public Leadqualitystates getLeadqualitystates() {
		return leadMeasurement.getLeadqualitystates();
	}

	@Override
	public void setLeadqualitystates(Object value) {
		leadMeasurement.setLeadqualitystates((Leadqualitystates) value);
	}

	@Override
	public String getPamp() {
		return leadMeasurement.getPamp();
	}

	@Override
	public void setPamp(String value) {
		leadMeasurement.setPamp(value);
	}

	@Override
	public String getPdur() {
		return leadMeasurement.getPdur();
	}

	@Override
	public void setPdur(String value) {
		leadMeasurement.setPdur(value);
	}

	@Override
	public String getParea() {
		return leadMeasurement.getParea();
	}

	@Override
	public void setParea(String value) {
		leadMeasurement.setParea(value);
	}

	@Override
	public String getPpamp() {
		return leadMeasurement.getPpamp();
	}

	@Override
	public void setPpamp(String value) {
		leadMeasurement.setPpamp(value);
	}

	@Override
	public String getPpdur() {
		return leadMeasurement.getPpdur();
	}

	@Override
	public void setPpdur(String value) {
		leadMeasurement.getPpdur();
	}

	@Override
	public String getPpppdur() {
		return leadMeasurement.getPpppdur();
	}

	@Override
	public void setPpppdur(String value) {
		leadMeasurement.setPpppdur(value);
	}

	@Override
	public String getPparea() {
		return leadMeasurement.getPparea();
	}

	@Override
	public void setPparea(String value) {
		leadMeasurement.setPparea(value);
	}

	@Override
	public String getPppparea() {
		return leadMeasurement.getPppparea();
	}

	@Override
	public void setPppparea(String value) {
		leadMeasurement.setPppparea(value);
	}

	@Override
	public String getQamp() {
		return leadMeasurement.getQamp();
	}

	@Override
	public void setQamp(String value) {
		leadMeasurement.setQamp(value);
	}

	@Override
	public String getQdur() {
		return leadMeasurement.getQdur();
	}

	@Override
	public void setQdur(String value) {
		leadMeasurement.setQdur(value);
	}

	@Override
	public String getRamp() {
		return leadMeasurement.getRamp();
	}

	@Override
	public void setRamp(String value) {
		leadMeasurement.setRamp(value);
	}

	@Override
	public String getRdur() {
		return leadMeasurement.getRdur();
	}

	@Override
	public void setRdur(String value) {
		leadMeasurement.setRdur(value);
	}

	@Override
	public String getSamp() {
		return leadMeasurement.getSamp();
	}

	@Override
	public void setSamp(String value) {
		leadMeasurement.setSamp(value);
	}

	@Override
	public String getSdur() {
		return leadMeasurement.getSdur();
	}

	@Override
	public void setSdur(String value) {
		leadMeasurement.setSdur(value);
	}

	@Override
	public String getRpamp() {
		return leadMeasurement.getRpamp();
	}

	@Override
	public void setRpamp(String value) {
		leadMeasurement.setRpamp(value);
	}

	@Override
	public String getRpdur() {
		return leadMeasurement.getRpdur();
	}

	@Override
	public void setRpdur(String value) {
		leadMeasurement.setRpdur(value);
	}

	@Override
	public String getSpamp() {
		return leadMeasurement.getSpamp();
	}

	@Override
	public void setSpamp(String value) {
		
		leadMeasurement.setSpamp(value);
	}

	@Override
	public String getSpdur() {
		
		return leadMeasurement.getSpdur();
	}

	@Override
	public void setSpdur(String value) {
		
		leadMeasurement.setSpdur(value);
	}

	@Override
	public String getVat() {
		
		return leadMeasurement.getVat();
	}

	@Override
	public void setVat(String value) {
		
		leadMeasurement.setVat(value);
	}

	@Override
	public String getQrsppk() {
		
		return leadMeasurement.getQrsppk();
	}

	@Override
	public void setQrsppk(String value) {
		
		leadMeasurement.setQrsppk(value);
	}

	@Override
	public String getQrsdur() {
		
		return leadMeasurement.getQrsdur();
	}

	@Override
	public void setQrsdur(String value) {
		
		leadMeasurement.setQrsdur(value);
	}

	@Override
	public String getQrsarea() {
		
		return leadMeasurement.getQrsarea();
	}

	@Override
	public void setQrsarea(String value) {
		
		leadMeasurement.setQrsarea(value);
	}

	@Override
	public String getSton() {
		
		return leadMeasurement.getSton();
	}

	@Override
	public void setSton(String value) {
		
		leadMeasurement.setSton(value);
	}

	@Override
	public String getStmid() {
		
		return leadMeasurement.getStmid();
	}

	@Override
	public void setStmid(String value) {
		
		leadMeasurement.setStmid(value);
	}

	@Override
	public String getSt80() {
		
		return leadMeasurement.getSt80();
	}

	@Override
	public void setSt80(String value) {
		
		leadMeasurement.setSt80(value);
	}

	@Override
	public String getStend() {
		
		return leadMeasurement.getStend();
	}

	@Override
	public void setStend(String value) {
		
		leadMeasurement.setStend(value);
	}

	@Override
	public String getStdur() {
		
		return leadMeasurement.getStdur();
	}

	@Override
	public void setStdur(String value) {
		
		leadMeasurement.setStdur(value);
	}

	@Override
	public String getStslope() {
		
		return leadMeasurement.getStslope();
	}

	@Override
	public void setStslope(String value) {
		
		leadMeasurement.setStslope(value);
	}

	@Override
	public TYPEstshape getStshape() {
		
		return leadMeasurement.getStshape();
	}

	@Override
	public void setStshape(Object value) {
		
		leadMeasurement.setStshape((TYPEstshape)value);
	}

	@Override
	public String getTamp() {
		
		return leadMeasurement.getTamp();
	}

	@Override
	public void setTamp(String value) {
		
		leadMeasurement.setTamp(value);
	}

	@Override
	public String getTdur() {
		
		return leadMeasurement.getTdur();
	}

	@Override
	public void setTdur(String value) {
		
		leadMeasurement.setTdur(value);
	}

	@Override
	public String getTarea() {
		
		return leadMeasurement.getTarea();
	}

	@Override
	public void setTarea(String value) {
		
		leadMeasurement.setTarea(value);
	}

	@Override
	public String getTpamp() {
		
		return leadMeasurement.getTpamp();
	}

	@Override
	public void setTpamp(String value) {
		
		leadMeasurement.setTpamp(value);
	}

	@Override
	public String getTptpdur() {
		
		return leadMeasurement.getTptpdur();
	}

	@Override
	public void setTptpdur(String value) {
		
		leadMeasurement.setTptpdur(value);
	}

	@Override
	public String getTpdur() {
		
		return leadMeasurement.getTpdur();
	}

	@Override
	public void setTpdur(String value) {
		
		leadMeasurement.setTpdur(value);
	}

	@Override
	public String getTparea() {
		
		return leadMeasurement.getTparea();
	}

	@Override
	public void setTparea(String value) {
		
		leadMeasurement.setTparea(value);
	}

	@Override
	public String getTptparea() {
		
		return leadMeasurement.getTptparea();
	}

	@Override
	public void setTptparea(String value) {
		
		leadMeasurement.setTptparea(value);
	}

	@Override
	public String getPrint() {
		
		return leadMeasurement.getPrint();
	}

	@Override
	public void setPrint(String value) {
		
		leadMeasurement.setPrint(value);
	}

	@Override
	public String getPrseg() {
		
		return leadMeasurement.getPrseg();
	}

	@Override
	public void setPrseg(String value) {
		
		leadMeasurement.setPrseg(value);
	}

	@Override
	public String getQtint() {
		
		return leadMeasurement.getQtint();
	}

	@Override
	public void setQtint(String value) {
		
		leadMeasurement.setQtint(value);
	}

	@Override
	public Object getBeats() {
		try {
			throw new IncorrectPhilipsVersionException();
		} catch (IncorrectPhilipsVersionException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void setBeats(Object value){
		try {
			throw new IncorrectPhilipsVersionException();
		} catch (IncorrectPhilipsVersionException e) {
			e.printStackTrace();
		}
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
	public String getLeadname() {
		
		return leadMeasurement.getLeadname();
	}

	@Override
	public void setLeadname(String value) {
		
		leadMeasurement.setLeadname(value);
	}

	@Override
	public TYPEflag getPexistflag() {
		
		return leadMeasurement.getPexistflag();
	}

	@Override
	public void setPexistflag(Object value) {
		
		leadMeasurement.setPexistflag((TYPEflag)value);
	}

	@Override
	public TYPEflag getPmeasflag() {
		
		return leadMeasurement.getPmeasflag();
	}

	@Override
	public void setPmeasflag(Object value) {
		
		leadMeasurement.setPmeasflag((TYPEflag)value);
	}

	@Override
	public TYPEflag getPnotchflag() {
		
		return leadMeasurement.getPnotchflag();
	}

	@Override
	public void setPnotchflag(Object value) {
		
		leadMeasurement.setPnotchflag((TYPEflag)value);
	}

	@Override
	public TYPEflag getQrsexistflag() {
		
		return leadMeasurement.getQrsexistflag();
	}

	@Override
	public void setQrsexistflag(Object value) {
		
		leadMeasurement.setQrsexistflag((TYPEflag)value);
	}

	@Override
	public TYPEflag getQrsspikeflag() {
		
		return leadMeasurement.getQrsspikeflag();
	}

	@Override
	public void setQrsspikeflag(Object value) {
		
		leadMeasurement.setQrsspikeflag((TYPEflag)value);
	}

	@Override
	public TYPEflag getQrsmeasflag() {
		
		return leadMeasurement.getQrsmeasflag();
	}

	@Override
	public void setQrsmeasflag(Object value) {
		
		leadMeasurement.setQrsmeasflag((TYPEflag)value);
	}

	@Override
	public TYPEflag2 getQrsnotchflag() {
		
		return leadMeasurement.getQrsnotchflag();
	}

	@Override
	public void setQrsnotchflag(Object value) {
		leadMeasurement.setQrsnotchflag((TYPEflag2)value);
	}

	@Override
	public TYPEflag getQrsdeltaflag() {
		
		return leadMeasurement.getQrsdeltaflag();
	}

	@Override
	public void setQrsdeltaflag(Object value) {
		
		leadMeasurement.setQrsdeltaflag((TYPEflag)value);
	}

	@Override
	public TYPEflag getStexistflag() {
		
		return leadMeasurement.getStexistflag();
	}

	@Override
	public void setStexistflag(Object value) {
		
		leadMeasurement.setStexistflag((TYPEflag)value);
	}

	@Override
	public TYPEflag getStmeasflag() {
		
		return leadMeasurement.getStmeasflag();
	}

	@Override
	public void setStmeasflag(Object value) {
		
		leadMeasurement.setStmeasflag((TYPEflag)value);
	}

	@Override
	public TYPEflag getTexistflag() {
		
		return leadMeasurement.getTexistflag();
	}

	@Override
	public void setTexistflag(Object value) {
		
		leadMeasurement.setTexistflag((TYPEflag)value);
	}

	@Override
	public TYPEflag getTmeasflag() {
		
		return leadMeasurement.getTmeasflag();
	}

	@Override
	public void setTmeasflag(Object value) {
		
		leadMeasurement.setTmeasflag((TYPEflag)value);
	}

	@Override
	public TYPEflag getTnotchflag() {
		
		return leadMeasurement.getTnotchflag();
	}

	@Override
	public void setTnotchflag(Object value) {
		
		leadMeasurement.setTnotchflag((TYPEflag)value);
	}

	@Override
	public TYPEflag3 getAtrialpaceflag() {
		
		return leadMeasurement.getAtrialpaceflag();
	}

	@Override
	public void setAtrialpaceflag(Object value) {
		try {
			throw new IncorrectPhilipsVersionException();
		} catch (IncorrectPhilipsVersionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public TYPEflag getVentpaceflag() {
		
		return leadMeasurement.getVentpaceflag();
	}

	@Override
	public void setVentpaceflag(Object value) {
		
		leadMeasurement.setVentpaceflag((TYPEflag)value);
	}

}
