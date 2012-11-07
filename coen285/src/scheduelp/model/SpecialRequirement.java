package scheduelp.model;

import java.io.Serializable;

public class SpecialRequirement implements Serializable {

	private static final long serialVersionUID = -3560055694103400354L;

	private Integer requirementID;

	private String requirementDesc;

	private Integer reqtUnits;

	public Integer getRequirementID() {
		return requirementID;
	}

	public void setRequirementID(Integer requirementID) {
		this.requirementID = requirementID;
	}

	public String getRequirementDesc() {
		return requirementDesc;
	}

	public void setRequirementDesc(String requirementDesc) {
		this.requirementDesc = requirementDesc;
	}

	public Integer getReqtUnits() {
		return reqtUnits;
	}

	public void setReqtUnits(Integer reqtUnits) {
		this.reqtUnits = reqtUnits;
	}

	@Override
	public int hashCode() {
		return requirementID.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		boolean eq = false;

		if (obj instanceof SpecialRequirement) {
			if (requirementID == ((SpecialRequirement) obj).getRequirementID()) {
				eq = true;
			}
		}
		return eq;
	}

}
