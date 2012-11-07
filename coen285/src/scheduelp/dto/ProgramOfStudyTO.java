package scheduelp.dto;

import java.util.HashMap;

import scheduelp.model.PlannedCourse;
import scheduelp.model.SpecialRequirement;

public class ProgramOfStudyTO {

	private Integer plannedUnits;

	private Integer requiredUnits;

	private HashMap<SpecialRequirement, PlannedCourse> programMap;

	public Integer getPlannedUnits() {
		return plannedUnits;
	}

	public void setPlannedUnits(Integer plannedUnits) {
		this.plannedUnits = plannedUnits;
	}

	public Integer getRequiredUnits() {
		return requiredUnits;
	}

	public void setRequiredUnits(Integer requiredUnits) {
		this.requiredUnits = requiredUnits;
	}

	public HashMap<SpecialRequirement, PlannedCourse> getProgramMap() {
		return programMap;
	}

	public void setProgramMap(HashMap<SpecialRequirement, PlannedCourse> programMap) {
		this.programMap = programMap;
	}

}
