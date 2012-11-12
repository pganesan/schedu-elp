package scheduelp.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scheduelp.dao.ScheduelpDAO;
import scheduelp.dto.ProgramOfStudiesTO;
import scheduelp.model.PlannedCourse;
import scheduelp.model.SpecialRequirement;

@Service
public class POSService {

	@Resource
	private ScheduelpDAO scheduelpDAO;

	@Transactional
	public ProgramOfStudiesTO getProgramOfStudies(String userID, String degree) {
		List<PlannedCourse> courses = scheduelpDAO.getPlannedCourses(userID, degree);

		// group courses by special requirement
		LinkedHashMap<SpecialRequirement, List<PlannedCourse>> programMap = new LinkedHashMap<SpecialRequirement, List<PlannedCourse>>();
		List<PlannedCourse> courseGroup = null;
		Integer totalUnits = 0;

		for (PlannedCourse course : courses) {
			totalUnits += course.getUnits();
			SpecialRequirement specReqt = course.getSpecialRequirement();
			courseGroup = programMap.get(specReqt);
			if (courseGroup == null) {
				courseGroup = new ArrayList<PlannedCourse>();
				programMap.put(specReqt, courseGroup);
			}
			courseGroup.add(course);
		}
		ProgramOfStudiesTO posTo = new ProgramOfStudiesTO();
		posTo.setProgramMap(programMap);
		posTo.setPlannedUnits(totalUnits);
		posTo.setPercentComplete(Math.round((totalUnits / 45.0f) * 100));
		
		return posTo;
	}

}