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
public interface LeadMeasurement {

	   	public abstract Object getPacepulses();

	    public abstract void setPacepulses(Object value);
	    
	    public abstract Object getLeadqualitystates();
	    
	    public abstract void setLeadqualitystates(Object value);
	    
	    public abstract String getPamp();
	    
	    public abstract void setPamp(String value);
	    
	    public abstract String getPdur();
	    
	    public abstract void setPdur(String value);
	    
	    public abstract String getParea();
	    
	    public abstract void setParea(String value);
	    
	    public abstract String getPpamp();
	    
	    public abstract void setPpamp(String value);
	    
	    public abstract String getPpdur();
	    
	    public abstract void setPpdur(String value);
	    
	    public abstract String getPpppdur();
	    
	    public abstract void setPpppdur(String value);
	    
	    public abstract String getPparea();
	    
	    public abstract void setPparea(String value);
	    
	    public abstract String getPppparea();
	    
	    public abstract void setPppparea(String value);
	    
	    public abstract String getQamp();
	    
	    public abstract void setQamp(String value);
	    
	    public abstract String getQdur();
	    
	    public abstract void setQdur(String value);
	    
	    public abstract String getRamp();
	    
	    public abstract void setRamp(String value);
	    
	    public abstract String getRdur();
	    
	    public abstract void setRdur(String value);
	    
	    public abstract String getSamp();
	    
	    public abstract void setSamp(String value);
	    
	    public abstract String getSdur();
	    
	    public abstract void setSdur(String value);
	    
	    public abstract String getRpamp();
	    
	    public abstract void setRpamp(String value);
	    
	    public abstract String getRpdur();
	    
	    public abstract void setRpdur(String value);
	    
	    public abstract String getSpamp();
	    
	    public abstract void setSpamp(String value);
	    
	    public abstract String getSpdur();
	    
	    public abstract void setSpdur(String value);
	    
	    public abstract String getVat();
	    
	    public abstract void setVat(String value);
	    
	    public abstract String getQrsppk();
	    
	    public abstract void setQrsppk(String value);
	    
	    public abstract String getQrsdur();
	    
	    public abstract void setQrsdur(String value);
	    
	    public abstract String getQrsarea();
	    
	    public abstract void setQrsarea(String value);
	    
	    public abstract String getSton();
	    
	    public abstract void setSton(String value);
	    
	    public abstract String getStmid();
	    
	    public abstract void setStmid(String value);
	    
	    public abstract String getSt80();
	    
	    public abstract void setSt80(String value);
	    
	    public abstract String getStend();
	    
	    public abstract void setStend(String value);
	    
	    public abstract String getStdur();
	    
	    public abstract void setStdur(String value);
	    
	    public abstract String getStslope();
	    
	    public abstract void setStslope(String value);
	    
	    public abstract Object getStshape();
	    
	    public abstract void setStshape(Object value);
	    
	    public abstract String getTamp();
	    
	    public abstract void setTamp(String value);
	    
	    public abstract String getTdur();
	    
	    public abstract void setTdur(String value);
	    
	    public abstract String getTarea();
	    
	    public abstract void setTarea(String value);
	    
	    public abstract String getTpamp();
	    
	    public abstract void setTpamp(String value);
	    
	    public abstract String getTptpdur();
	    
	    public abstract void setTptpdur(String value);
	    
	    public abstract String getTpdur();
	    
	    public abstract void setTpdur(String value);
	    
	    public abstract String getTparea();
	    
	    public abstract void setTparea(String value);
	    
	    public abstract String getTptparea();
	    
	    public abstract void setTptparea(String value);
	    
	    public abstract String getPrint();
	    
	    public abstract void setPrint(String value);
	    
	    public abstract String getPrseg();
	    
	    public abstract void setPrseg(String value);
	    
	    public abstract String getQtint();
	    
	    public abstract void setQtint(String value);
	    
	    public abstract Object getBeats();
	    
	    public abstract void setBeats(Object value);
	    
	    public abstract Object getNamedmeasurement();
	    
	    public abstract String getLeadname();
	    
	    public abstract void setLeadname(String value);
	    
	    public abstract Object getPexistflag();
	    
	    public abstract void setPexistflag(Object value);
	    
	    public abstract Object getPmeasflag();
	    
	    public abstract void setPmeasflag(Object value);
	    
	    public abstract Object getPnotchflag();
	    
	    public abstract void setPnotchflag(Object value);
	    
	    public abstract Object getQrsexistflag();
	    
	    public abstract void setQrsexistflag(Object value);
	    
	    public abstract Object getQrsspikeflag();
	    
	    public abstract void setQrsspikeflag(Object value);
	    
	    public abstract Object getQrsmeasflag();
	    
	    public abstract void setQrsmeasflag(Object value);
	    
	    public abstract Object getQrsnotchflag();
	    
	    public abstract void setQrsnotchflag(Object value);
	    
	    public abstract Object getQrsdeltaflag();
	    
	    public abstract void setQrsdeltaflag(Object value);
	    
	    public abstract Object getStexistflag();
	    
	    public abstract void setStexistflag(Object value);
	    
	    public abstract Object getStmeasflag();
	    
	    public abstract void setStmeasflag(Object value);
	    
	    public abstract Object getTexistflag();
	    
	    public abstract void setTexistflag(Object value);
	    
	    public abstract Object getTmeasflag();
	    
	    public abstract void setTmeasflag(Object value);
	    
	    public abstract Object getTnotchflag();
	    
	    public abstract void setTnotchflag(Object value);
	    
	    public abstract Object getAtrialpaceflag();
	    
	    public abstract void setAtrialpaceflag(Object value);
	    
	    public abstract Object getVentpaceflag();
	    
	    public abstract void setVentpaceflag(Object value);
}

