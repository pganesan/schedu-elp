package scheduelp.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import scheduelp.common.ScheduelpException;

@Controller
public class BaseController {
	
	@ExceptionHandler(ScheduelpException.class)
	public @ResponseBody
	String handleException(ScheduelpException le, HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		return le.getMessage();
	}

	@ExceptionHandler(HttpSessionRequiredException.class)
	public @ResponseBody
	String handleException(HttpSessionRequiredException le, HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		return "Please log in to plan your program of study.";
	}
}
