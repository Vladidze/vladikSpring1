package com.example.vladik;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.vladik.models.Person;
import com.example.vladik.services.VladikService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class VladikController {
	
	@Autowired
	private final VladikService serv;
	
	@GetMapping("/")
	public String maimPage() {
		return "Page";
	}
	
	@PostMapping("/check")
	public String register(Person person) {
		System.out.println("SSSSSSSSSSSSSSSSSSSSSSSSSSSSS1");
		System.out.println(person.getLogin());
		System.out.println(person.getId());
		System.out.println(person.getPassword());
		if(serv.check(person)) {
			return "TrueReg";
		}
		System.out.println("FAILED");
		return "FallReg";
		
		
//		if(serv.check(person.getLogin(), person.getPassword())) {
//			return "TrueReg";
//		}
           //		return "FallReg";
	}
	
	@PostMapping("/registration")
	public String registrNew(Person person) {
		System.out.println(person.getLogin());
		System.out.println(person.getPassword());
		System.out.println(person.getId());
		serv.addPerson(person);
		return "redirect:/";
	}
	
	@PostMapping("/showTable")
	public String showTable(Model model) {
		model.addAttribute("usersList", serv.people());
		return "tableUsers";
		}
	
	@GetMapping("/deleteUser") 
	public String deleteUser(String login) {
		serv.removePerson(login);
		return "redirect:/";
	}
}
