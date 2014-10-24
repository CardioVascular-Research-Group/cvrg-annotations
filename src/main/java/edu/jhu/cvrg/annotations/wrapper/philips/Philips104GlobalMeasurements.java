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
import org.cvrgrid.philips.jaxb.beans.Crossleadmeasurements;

import edu.jhu.cvrg.annotations.utilities.interfaces.GlobalMeasurements;

public class Philips104GlobalMeasurements implements GlobalMeasurements {

	private Crossleadmeasurements globalMeasurements;
	
	public Philips104GlobalMeasurements(Crossleadmeasurements crossleadmeasurements){
		this.globalMeasurements = crossleadmeasurements;
	}

	@Override
	public String getPacemalf() {
		return this.globalMeasurements.getPacemalf();
	}

	@Override
	public String getPacemisc() {
		return this.globalMeasurements.getPacemisc();
	}

	@Override
	public String getEctopicrhythm() {
		return this.globalMeasurements.getEctopicrhythm();
	}

	@Override
	public String getQtintdispersion() {
		return this.globalMeasurements.getQtintdispersion();
	}

	@Override
	public String getNumberofcomplexes() {
		return this.globalMeasurements.getNumberofcomplexes();
	}

	@Override
	public String getNumberofgroups() {
		return this.globalMeasurements.getNumberofgroups();
	}
	

	@Override
	public String getQaactioncode() {
		return this.globalMeasurements.getQaactioncode().value();
	}

	@Override
	public String getPfrontaxis() {
		return this.globalMeasurements.getPfrontaxis();
	}

	@Override
	public String getPhorizaxis() {
		return this.globalMeasurements.getPhorizaxis();
	}

	@Override
	public String getI40Frontaxis() {
		return this.globalMeasurements.getI40Frontaxis();
	}

	@Override
	public String getI40Horizaxis() {
		return this.globalMeasurements.getI40Horizaxis();
	}

	@Override
	public String getQrsfrontaxis() {
		return this.globalMeasurements.getQrsfrontaxis();
	}

	@Override
	public String getQrshorizaxis() {
		return this.globalMeasurements.getQrshorizaxis();
	}

	@Override
	public String getT40Frontaxis() {
		return this.globalMeasurements.getT40Frontaxis();
	}

	@Override
	public String getT40Horizaxis() {
		return this.globalMeasurements.getT40Horizaxis();
	}

	@Override
	public String getStfrontaxis() {
		return this.globalMeasurements.getStfrontaxis();
	}

	@Override
	public String getSthorizaxis() {
		return this.globalMeasurements.getSthorizaxis();
	}

	@Override
	public String getTfrontaxis() {
		return this.globalMeasurements.getTfrontaxis();
	}

	@Override
	public String getThorizaxis() {
		return this.globalMeasurements.getThorizaxis();
	}

	@Override
	public String getAtrialrate() {
		return this.globalMeasurements.getAtrialrate();
	}

	@Override
	public String getLowventrate() {
		return this.globalMeasurements.getLowventrate();
	}

	@Override
	public String getMeanventrate() {
		return this.globalMeasurements.getMeanventrate();
	}

	@Override
	public String getHighventrate() {
		return this.globalMeasurements.getHighventrate();
	}

	@Override
	public String getMeanprint() {
		return this.globalMeasurements.getMeanprint();
	}

	@Override
	public String getMeanprseg() {
		return this.globalMeasurements.getMeanprseg();
	}

	@Override
	public String getMeanqrsdur() {
		return this.globalMeasurements.getMeanqrsdur();
	}

	@Override
	public String getMeanqtint() {
		return this.globalMeasurements.getMeanqtint();
	}

	@Override
	public String getMeanqtc() {
		return this.globalMeasurements.getMeanqtc();
	}

	@Override
	public String getDeltawavecount() {
		return this.globalMeasurements.getDeltawavecount();
	}

	@Override
	public String getDeltawavepercent() {
		return this.globalMeasurements.getDeltawavepercent();
	}

	@Override
	public String getBigeminycount() {
		return this.globalMeasurements.getBigeminycount();
	}

	@Override
	public String getBigeminystring() {
		return this.globalMeasurements.getBigeminystring();
	}

	@Override
	public String getTrigeminycount() {
		return this.globalMeasurements.getTrigeminycount();
	}

	@Override
	public String getTrigeminystring() {
		return this.globalMeasurements.getTrigeminystring();
	}

	@Override
	public String getWenckcount() {
		return this.globalMeasurements.getWenckcount();
	}

	@Override
	public String getWenckstring() {
		return this.globalMeasurements.getWenckstring();
	}

	@Override
	public String getFlutterfibcount() {
		return this.globalMeasurements.getFlutterfibcount();
	}

	@Override
	public String getBeatclassification() {
		String beatClassificationValues = "";
		// concatenate all the beat classifications, as that is how they appear in the XML
		for (Integer beatValue : globalMeasurements.getBeatclassification()) {
			beatClassificationValues = beatClassificationValues + " " + beatValue;
		}
		return beatClassificationValues;
	}
	
}
