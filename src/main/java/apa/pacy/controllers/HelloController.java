package apa.pacy.controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import apa.pacy.models.ReactivePersonRepository;
import apa.pacy.models.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 
 * @author Gaurav Rai Mazra
 * <a href="http://www.gauravbytes.com">Catch me</a>
 */
@RestController
public class HelloController {
	
	 private ReactivePersonRepository people;

	  public HelloController(ReactivePersonRepository people) {
	    this.people = people;
	  }
	
	@GetMapping("/test")
	public String helloGb(Map<String, Object> model) {
		model.put("title", new String("Title"));
		model.put("message", new String("OK"));
		return "welcome";
	}
	
	 @GetMapping("/people")
	  Mono<Person> namesByLastname(@RequestParam String lastname) {
		Person user = new Person();
		user.setFirstname("Joe");
		user.setLastname("Hiber");
		user.setAge(27);
		Mono<Person> saved = this.people.save(user);
	    // Flux<Person> result = this.people.findByLastname(lastname);
	    return saved;
	  }
	
}