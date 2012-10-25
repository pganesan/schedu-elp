package scheduelp.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import scheduelp.common.ScheduelpException;
import scheduelp.dto.UserSessionTO;
import scheduelp.service.LoginService;

@Controller
@SessionAttributes("userDetail")
public class LoginController extends BaseController {

	@Resource
	private LoginService loginService;

	@RequestMapping(value = "/showlogin", method = RequestMethod.GET)
	public String getLogin(Model model, SessionStatus status) throws ScheduelpException {
		// clear any previous session
		status.setComplete();
		model.addAttribute("userTo", new UserSessionTO());
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String submitLogin(@ModelAttribute("userTo") UserSessionTO credentials, Model model)
			throws ScheduelpException {
		UserSessionTO userDetailTO = loginService.login(credentials);

		model.addAttribute("userDetail", userDetailTO);
		return "logout";
	}

}
