package scheduelp.dto;

import java.util.LinkedHashMap;
import java.util.List;

import scheduelp.model.PlannedCourse;
import scheduelp.model.SpecialRequirement;

public class ProgramOfStudyTO {

	private Integer plannedUnits;

	private Integer percentComplete;

	private LinkedHashMap<SpecialRequirement, List<PlannedCourse>> programMap;
	
	public Integer getPlannedUnits() {
		return plannedUnits;
	}

	public void setPlannedUnits(Integer plannedUnits) {
		this.plannedUnits = plannedUnits;
	}

	public Integer getPercentComplete() {
		return percentComplete;
	}

	public void setPercentComplete(Integer percentComplete) {
		this.percentComplete = percentComplete;
	}

	public LinkedHashMap<SpecialRequirement, List<PlannedCourse>> getProgramMap() {
		return programMap;
	}

	public void setProgramMap(LinkedHashMap<SpecialRequirement, List<PlannedCourse>> programMap) {
		this.programMap = programMap;
	}

}
