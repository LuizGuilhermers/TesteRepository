package com.example.repository;


import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.entities.Hospede;



@DataJpaTest
class HospedeReposiotryTest {

	@Autowired
	private HospedeRepository hospedeRepository;

	@DisplayName("Testando o save")
	@Test
	void testSalvarRespository() {
		// Given / Arrange
		Hospede hospede1 = new Hospede(null, "Julia Maria", "julia@gmail.com", "(00)0000-0000");
		// When / Act
		Hospede saveHospede = hospedeRepository.save(hospede1);
		// Then / Assert
		Assertions.assertNotNull(saveHospede);
		Assertions.assertTrue(saveHospede.getId() > 0);
	}

	@DisplayName("Testando Get para todos Hospedes")
	@Test
	void testGetAllRespository() {
		// Given / Arrange
		Hospede hospede1 = new Hospede(null, "Julia Maria", "julia@gmail.com", "(00)0000-0000");
		Hospede hospede2 = new Hospede(null, "Julio Fernando", "julio@gmail.com", "(00)0000-0000");
		hospedeRepository.save(hospede1);
		hospedeRepository.save(hospede2);

		// When / Act
		List<Hospede> hospedeList = hospedeRepository.findAll();
		// Then / Assert
		Assertions.assertNotNull(hospedeList);
		Assertions.assertEquals(2, hospedeList.size());

	}

	@DisplayName("Testando GET By ID")
	@Test
	void testGetById() {

		Hospede hospede1 = new Hospede(null, "Julio Fernando", "julio@gmail.com", "(00)0000-0000");

		hospedeRepository.save(hospede1);

		Hospede saveHospede = hospedeRepository.findById(hospede1.getId()).get();

		Assertions.assertNotNull(saveHospede);
		Assertions.assertEquals(hospede1.getId(), saveHospede.getId());
	}

	@DisplayName("Testando o update")
	@Test
	void testUpdateHospede() {
		// Given / Arrange
		Hospede hospede1 = new Hospede(null, "Julia Maria", "julia@gmail.com", "(00)0000-0000");
		hospedeRepository.save(hospede1);
		// When / Act
		Hospede saveHospede = hospedeRepository.findById(hospede1.getId()).get();
		hospede1.setNome("Leonardo");
		hospede1.setEmail("leonardo@gmail.com");
		Hospede updateHospede = hospedeRepository.save(saveHospede);
		// Then / Assert
		Assertions.assertNotNull(updateHospede);
		Assertions.assertEquals("Leonardo", updateHospede.getNome());
		Assertions.assertEquals("leonardo@gmail.com", updateHospede.getEmail());

	}
	
	@DisplayName("testando o Delete")
	@Test
	void testDeleteHospede () {
		
		Hospede hospede1 =new Hospede(null, "Julio Fernando", "julio@gmail.com", "(00) 0000-0000");
		
		hospedeRepository.save(hospede1);
		
		hospedeRepository.deleteById(hospede1.getId());
		
		Optional<Hospede> hospedeOptional = hospedeRepository.findById(hospede1.getId());
		
		
		Assertions.assertTrue(hospedeOptional.isEmpty());
		
	}

}