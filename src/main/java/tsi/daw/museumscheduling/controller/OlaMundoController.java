package tsi.daw.museumscheduling.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OlaMundoController {
	
	@RequestMapping("olaSpring")
	public String ola() {
		return "ola";
	}
}
