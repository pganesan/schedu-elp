package scheduelp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import scheduelp.common.ScheduelpException;

@Controller
public class HomeController extends BaseController {

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String getHome(Model model) throws ScheduelpException {
		return "home";
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getIndex(Model model) throws ScheduelpException {
		return "index";
	}
}
