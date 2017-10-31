package io.bear;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	@Autowired
	PersonRepository personRepository;
	
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
      ModelAndView mav = new ModelAndView("login");
      mav.addObject("person", new Person());
      return mav;
    }      


	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public ModelAndView registerProcess(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("person") Person person) {
		
			Person p = personRepository.findByName(person.getName());
			if (p.getPassword().equals(person.getPassword())) {
				return new ModelAndView("welcome", "name", person.getName());
			}

		return new ModelAndView("error","errorInfo","Password not right");
	}

}
