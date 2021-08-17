package hk.com.nexify.rest.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RestController
@RequestMapping("/book/")
public class BookController {

	@RequestMapping(value = "/index")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView("index");
		return modelAndView;
	}
	
	@RequestMapping(value = "/add")
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView("add");
		return modelAndView;
	}
	
	@RequestMapping(value = "/detail/{id}")
	public ModelAndView detail() {
		ModelAndView modelAndView = new ModelAndView("detail");
		return modelAndView;
	}

}