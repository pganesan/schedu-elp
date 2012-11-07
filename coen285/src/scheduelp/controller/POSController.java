package scheduelp.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import scheduelp.common.ScheduelpException;
import scheduelp.dto.ProgramOfStudyTO;
import scheduelp.dto.UserSessionTO;
import scheduelp.service.POSService;

@Controller
@SessionAttributes("userDetail")
public class POSController extends BaseController {

	@Resource
	private POSService posService;

	@RequestMapping(value = "/pos/add", method = RequestMethod.POST)
	public @ResponseBody
	String addCourse(@ModelAttribute("userDetail") UserSessionTO userDetail,
			@RequestParam("cid") String courseCode, Model model) throws ScheduelpException {
		posService.addCourse(userDetail.getUserID(), userDetail.getDegree(), courseCode);

		return courseCode.concat(" has been added to your Program of Study");
	}

	@RequestMapping(value = "/pos/remove", method = RequestMethod.POST)
	public @ResponseBody
	String removeCourse(@ModelAttribute("userDetail") UserSessionTO userDetail,
			@RequestParam("cid") String courseCode, Model model) throws ScheduelpException {
		throw new ScheduelpException(
				"Sorry! This feature is not supported with this release of Schedu-elp");
	}

	@RequestMapping(value = "/pos", method = RequestMethod.GET)
	public String getProgramOfStudy(@ModelAttribute("userDetail") UserSessionTO userDetail,
			Model model) throws ScheduelpException {
		ProgramOfStudyTO dto = posService.getProgramOfStudy(userDetail.getUserID(), userDetail.getDegree());
		model.addAttribute("posTo", dto);
		
		return "viewpos";
	}
}
