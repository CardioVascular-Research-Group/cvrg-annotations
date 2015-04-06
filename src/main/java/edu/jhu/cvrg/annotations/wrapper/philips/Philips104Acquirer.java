package edu.jhu.cvrg.annotations.wrapper.philips;

import edu.jhu.cvrg.annotations.utilities.interfaces.Acquirer;

public class Philips104Acquirer implements Acquirer {

	private org.cvrgrid.philips.jaxb.beans.Acquirer acquirer;
	
	public Philips104Acquirer(org.cvrgrid.philips.jaxb.beans.Acquirer acquirer) {
		this.acquirer = acquirer; 
	}
	
	@Override
	public String getEncounterId() {
		return this.acquirer.getEncounterid();
	}

	@Override
	public String getOperatorId() {
		if(this.acquirer.getOperator() != null){
			return this.acquirer.getOperator().getId();
		}else{
			return null;
		}
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
		if(this.acquirer.getConsultingclinician() != null){
			return this.acquirer.getConsultingclinician().getValue() + " (" + this.acquirer.getConsultingclinician().getId() + ')';	
		}else{
			return null;
		}
	}

}
