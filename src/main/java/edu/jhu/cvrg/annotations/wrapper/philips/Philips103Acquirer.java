package edu.jhu.cvrg.annotations.wrapper.philips;

import edu.jhu.cvrg.annotations.utilities.interfaces.Acquirer;

public class Philips103Acquirer implements Acquirer {

	private org.sierraecg.schema.Acquirer acquirer;
	
	public Philips103Acquirer(org.sierraecg.schema.Acquirer acquirer) {
		this.acquirer = acquirer; 
	}
	
	@Override
	public String getEncounterId() {
		return this.acquirer.getEncounterid();
	}

	@Override
	public String getOperatorId() {
		return this.acquirer.getOperatorid();
	}

	@Override
	public String getRoom() {
		return this.acquirer.getRoom();
	}

	@Override
	public String getDepartmentid() {
		return this.acquirer.getDepartmentid();
	}

	@Override
	public String getDepartmentname() {
		return this.acquirer.getDepartmentname();
	}

	@Override
	public String getInstitutionid() {
		return this.acquirer.getInstitutionid();
	}

	@Override
	public String getInstitutionname() {
		return this.acquirer.getInstitutionname();
	}

	@Override
	public String getConsultingclinician() {
		return this.acquirer.getReviewingclinician();
	}

}
