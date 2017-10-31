package io.bear;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Controller
public class WebController extends WebMvcConfigurerAdapter {

	@Autowired
	PersonRepository personRepository;

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/results").setViewName("results");
	}

	@GetMapping("/")
	public String showRootPage(Person person) {
		return "home";
	}

	@GetMapping("/home")
	public String showHomePage(Person person) {
		return "home";
	}

	// @PostMapping("/")
	// public String checkPersonInfo(@Valid Person person, BindingResult
	// bindingResult) {
	//
	// if (bindingResult.hasErrors()) {
	// return "form";
	// }
	//
	// return "redirect:/results";
	// }

}
