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
public interface GroupMeasurement {

	    public abstract String getMembercount();
	    
	    public abstract void setMembercount(String value);
	    
	    public abstract String getMemberpercent();
	    
	    public abstract void setMemberpercent(String value);
	    
	    public abstract String getLongestrun();
	    
	    public abstract void setLongestrun(String value);
	    
	    public abstract String getMeanqrsdur();
	    
	    public abstract void setMeanqrsdur(String value);
	    
	    public abstract String getLowventrate();
	    
	    public abstract void setLowventrate(String value);
	    
	    public abstract String getMeanventrate();
	    
	    public abstract void setMeanventrate(String value);
	    
	    public abstract String getHighventrate();
	    
	    public abstract void setHighventrate(String value);
	    
	    public abstract String getVentratestddev();
	    
	    public abstract void setVentratestddev(String value);
	    
	    public abstract String getMeanrrint();
	    
	    public abstract void setMeanrrint(String value);
	    
	    public abstract String getAtrialrate();
	    
	    public abstract void setAtrialrate(String value);
	    
	    public abstract String getAtrialratestddev();
	    
	    public abstract void setAtrialratestddev(String value);
	    
	    public abstract String getAvgpcount();
	    
	    public abstract void setAvgpcount(String value);
	    
	    public abstract String getNotavgpbeats();
	    
	    public abstract void setNotavgpbeats(String value);
	    
	    public abstract String getLowprint();
	    
	    public abstract void setLowprint(String value);
	    
	    public abstract String getMeanprint();
	    
	    public abstract void setMeanprint(String value);
	    
	    public abstract String getHighprint();
	    
	    public abstract void setHighprint(String value);
	    
	    public abstract String getPrintstddev();
	    
	    public abstract void setPrintstddev(String value);
	    
	    public abstract String getMeanprseg();
	    
	    public abstract void setMeanprseg(String value);
	    
	    public abstract String getMeanqtint();
	    
	    public abstract void setMeanqtint(String value);
	    
	    public abstract String getMeanqtseg();
	    
	    public abstract void setMeanqtseg(String value);
	    
	    public abstract String getComppausecount();
	    
	    public abstract void setComppausecount(String value);
	    
	    public abstract Object getNamedmeasurement();
	    
	    public abstract int getGroupnumber();
	    
	    public abstract void setGroupnumber(int value);
	    
	    public abstract Object getInterpflag();
	    
	    public abstract void setInterpflag(Object value);
	    
	    public abstract Object getSinusflag();
	    
	    public abstract void setSinusflag(Object value);
	    
	    public abstract Object getPrprogflag();
	    
	    public abstract void setPrprogflag(Object value);
	    
	    public abstract Object getWenckflag();
	    
	    public abstract void setWenckflag(Object value);
	    
	    public abstract Object getBigflag();
	    
	    public abstract void setBigflag(Object value);
	    
	    public abstract Object getTrigflag();
	    
	    public abstract void setTrigflag(Object value);
	    
	    public abstract Object getAberrantflag();
	    
	    public abstract void setAberrantflag(Object value);
	    
	    public abstract Object getMultptestflag();
	    
	    public abstract void setMultptestflag(Object value);
	    
	    public abstract Object getQrsmeasflag();
	    
	    public abstract void setQrsmeasflag(Object value);
	    
	    public abstract Object getAtrialpaceflag();
	    
	    public abstract void setAtrialpaceflag(Object value);
	    
	    public abstract Object getVentdualpaceflag();
	    
	    public abstract void setVentdualpaceflag(Object value);

		public abstract String getGroupreserved();

	}
