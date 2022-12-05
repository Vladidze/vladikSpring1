package com.example.vladik.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vladik.models.Person;

public interface VladikRepository extends JpaRepository<Person, Long>{
	
	Person findByLogin (String login);

}
