package scheduelp.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import scheduelp.common.ScheduelpException;
import scheduelp.dto.CourseDetailTO;
import scheduelp.dto.UserSessionTO;
import scheduelp.model.Review;
import scheduelp.service.ReviewService;

@Controller
@SessionAttributes("userDetail")
public class ReviewController extends BaseController {

	@Resource
	private ReviewService reviewService;

	@RequestMapping(value = "/review/list", method = RequestMethod.GET)
	public String getReviews(@RequestParam("cid") String courseCode, Model model)
			throws ScheduelpException {
		CourseDetailTO dto = reviewService.getReviews(courseCode);

		model.addAttribute("reviewTo", dto);
		return "viewreview";
	}
	
	@RequestMapping(value = "/review/new", method = RequestMethod.GET)
	public String getNewReview(@ModelAttribute("userDetail") UserSessionTO userDetail,
			@RequestParam("cid") String courseCode, Model model) throws ScheduelpException {
		Review dto = new Review();
		dto.setCourse(courseCode);

		model.addAttribute("reviewDetailTo", dto);
		return "newreview";
	}

	@RequestMapping(value = "/review/new/submit", method = RequestMethod.POST)
	public String submitNewReview(@ModelAttribute("userDetail") UserSessionTO userDetail,
			@ModelAttribute("reviewDetailTo") Review review, Model model)
			throws ScheduelpException {
		review.setStudent(userDetail.getUserID());
		reviewService.postReview(review);
		String courseCode = review.getCourse();

		return "redirect:/view/review/list?cid=".concat(courseCode);
	}

}
