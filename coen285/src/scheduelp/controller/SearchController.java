package scheduelp.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import scheduelp.common.ScheduelpException;
import scheduelp.dto.CourseDetailTO;
import scheduelp.dto.CourseSearchTO;
import scheduelp.service.SearchService;

@Controller
@SessionAttributes("userDetail")
public class SearchController extends BaseController {

	@Resource
	private SearchService searchService;

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String getSearch(Model model) throws ScheduelpException {
		model.addAttribute("courseSearchTO", new CourseSearchTO());
		model.addAttribute("daysOfWeek", populateDays());
		return "searchcourse";
	}

	@RequestMapping(value = "/search/result", method = RequestMethod.POST)
	public String getSearchResult(@ModelAttribute("courseSearchTO") CourseSearchTO searchTO,
			Model model) throws ScheduelpException {
		List<CourseDetailTO> courseList = searchService.findCourses(searchTO);
		
		model.addAttribute("courseList", courseList);
		model.addAttribute("courseSearchTO", searchTO);
		model.addAttribute("daysOfWeek", populateDays());
		return "searchcourse";
	}

	protected Map<String, String> populateDays() {
		Map<String, String> daysOfWeek = new LinkedHashMap<String, String>();
		daysOfWeek.put("M", "M");
		daysOfWeek.put("T", "T");
		daysOfWeek.put("W", "W");
		daysOfWeek.put("R", "Th");
		daysOfWeek.put("F", "F");
		daysOfWeek.put("A", "Sa");
		daysOfWeek.put("S", "Su");

		return daysOfWeek;
	}
}