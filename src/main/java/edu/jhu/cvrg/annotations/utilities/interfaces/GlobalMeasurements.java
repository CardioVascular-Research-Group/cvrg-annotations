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
* @author Andre Vilardo
* 
*/
public interface GlobalMeasurements {
	
	public String getPacemalf();
	public String getPacemisc();
	public String getEctopicrhythm();
	public String getQtintdispersion();
	public String getNumberofcomplexes();
	public String getNumberofgroups();
	public String getQaactioncode();
	public String getPfrontaxis();
	public String getPhorizaxis();
	public String getI40Frontaxis();
	public String getI40Horizaxis();
	public String getQrsfrontaxis();
	public String getQrshorizaxis();
	public String getT40Frontaxis();
	public String getT40Horizaxis();
	public String getStfrontaxis();
	public String getSthorizaxis();
	public String getTfrontaxis();
	public String getThorizaxis();
	public String getAtrialrate();
	public String getLowventrate();
	public String getMeanventrate();
	public String getHighventrate();
	public String getMeanprint();
	public String getMeanprseg();
	public String getMeanqrsdur();
	public String getMeanqtint();
	public String getMeanqtc();
	public String getDeltawavecount();
	public String getDeltawavepercent();
	public String getBigeminycount();
	public String getBigeminystring();
	public String getTrigeminycount();
	public String getTrigeminystring();
	public String getWenckcount();
	public String getWenckstring();
	public String getFlutterfibcount();
	public String getBeatclassification();
}
