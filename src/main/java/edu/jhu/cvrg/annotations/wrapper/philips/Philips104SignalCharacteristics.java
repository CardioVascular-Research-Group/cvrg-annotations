package edu.jhu.cvrg.annotations.wrapper.philips;

import org.cvrgrid.philips.jaxb.beans.Signalcharacteristics;

import edu.jhu.cvrg.annotations.utilities.interfaces.SignalCharacteristics;

public class Philips104SignalCharacteristics implements SignalCharacteristics {

	private Signalcharacteristics signalCharacteristics;
	
	public Philips104SignalCharacteristics(Signalcharacteristics signalCharacteristics) {
		this.signalCharacteristics = signalCharacteristics;
	}
	
	@Override
	public String getSignalresolution() {
		return String.valueOf(this.signalCharacteristics.getResolution());
	}

	@Override
	public String getAcsetting() {
		return this.signalCharacteristics.getAcsetting();
	}

	@Override
	public String getBitspersample() {
		return String.valueOf(this.signalCharacteristics.getBitspersample());
	}

	@Override
	public String getSignaloffset() {
		if(this.signalCharacteristics.getSignaloffset() != null){
			return this.signalCharacteristics.getSignaloffset().toString();	
		}else{
			return null;
		}
	}

	@Override
	public String getAcquisitiontype() {
		return this.signalCharacteristics.getAcquisitiontype();
	}

	@Override
	public String getSignalsigned() {
		if(this.signalCharacteristics.getSignalsigned() != null){
			return this.signalCharacteristics.getSignalsigned().toString();	
		}else{
			return null;
		}
	}

}
