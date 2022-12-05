package com.example.vladik.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.vladik.models.Person;
import com.example.vladik.repositories.VladikRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
public class VladikService implements UserDetailsService{

	@Autowired 
    VladikRepository vladikRepository;
	
	
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(8);

	public Person findPersonById(Long id) {
		Optional<Person> pers = vladikRepository.findById(id);
		return pers.orElse(new Person());
	}
	
	public List<Person> people() {
		return vladikRepository.findAll();
	}
//	
//	public boolean check (String login, String password) {
//		if(vladikRepository.findByLogin("root") && vladikRepository.findByPassword("toor")) {
//			System.out.println("YEAHHH");
//		}
//		return (vladikRepository.findByLogin(login) && vladikRepository.findByPassword(password));
//			
//	}
	
	public void removePerson(String login) {
		Person p = vladikRepository.findByLogin(login);
		vladikRepository.deleteById(p.getId());
	}
	
	public boolean check(Person person) {
		Person p = vladikRepository.findByLogin(person.getLogin());
	if(p == null) {
		System.out.println("USER NOT FOUND");
		return false;}
	System.out.println("Pass from DB:" + p.getPassword());
	System.out.println("Your pass:" + bCryptPasswordEncoder.encode(person.getPassword()));
	
//	System.out.println("Your pass:" + person.getPassword());
//	if(p.getPassword().equals(person.getPassword())) {return true;}
	return bCryptPasswordEncoder.matches(person.getPassword(), p.getPassword());
	}

	public boolean addPerson(Person person) {
		System.out.println("adding");
		if(vladikRepository.findByLogin(person.getLogin()) != null) {return false;}
		person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
		System.err.println("Encripted pass: " + person.getPassword());
	    vladikRepository.save(person);
	    return true;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Person person = vladikRepository.findByLogin(username);
		if(person == null) {
			throw new UsernameNotFoundException("User no found...");
		}
		return person;
	}

}
