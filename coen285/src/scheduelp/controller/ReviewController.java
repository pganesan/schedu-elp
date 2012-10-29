package scheduelp.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import scheduelp.common.ScheduelpException;
import scheduelp.dto.CourseDetailTO;
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

	/*
	@RequestMapping(value = "/review/new", method = RequestMethod.POST)
	public String getNewReview(@ModelAttribute("userDetail") UserSessionTO userDetail,
			@RequestParam("cid") String courseCode, Model model) throws ScheduelpException {
		ReviewDetailTO dto = new ReviewDetailTO();
		dto.setRestaurantId(restaurantId);

		model.addAttribute("reviewDetailTo", dto);
		return "newreview";
	}

	@RequestMapping(value = "/review/new/submit", method = RequestMethod.POST)
	public String submitNewReview(@ModelAttribute("userDetail") UserSessionTO userDetail,
			@ModelAttribute("reviewDetailTo") ReviewDetailTO reviewDetailTo, Model model)
			throws ScheduelpException {
		reviewDetailTo.setUserId(userDetail.getUserID());
		reviewService.postReview(reviewDetailTo);
		String restaurantId = Integer.toString(reviewDetailTo.getRestaurantId());

		return "redirect:/list/review/list?cid=".concat(restaurantId);
	}
	*/
}
