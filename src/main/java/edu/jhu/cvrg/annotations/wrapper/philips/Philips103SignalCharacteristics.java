package edu.jhu.cvrg.annotations.wrapper.philips;

import org.sierraecg.schema.Signalcharacteristics;

import edu.jhu.cvrg.annotations.utilities.interfaces.SignalCharacteristics;

public class Philips103SignalCharacteristics implements SignalCharacteristics {

	private Signalcharacteristics signalCharacteristics;
	
	public Philips103SignalCharacteristics(Signalcharacteristics signalCharacteristics) {
		this.signalCharacteristics = signalCharacteristics;
	}
	
	@Override
	public String getSignalresolution() {
		return this.signalCharacteristics.getSignalresolution();
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
		return this.signalCharacteristics.getSignaloffset();
	}

	@Override
	public String getAcquisitiontype() {
		if(this.signalCharacteristics.getAcquisitiontype() != null){
			return this.signalCharacteristics.getAcquisitiontype().toString();	
		}else{
			return null;
		}
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
