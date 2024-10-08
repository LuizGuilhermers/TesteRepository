package com.example.repository;


import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.entities.Quarto;

@DataJpaTest
class QuartoRepositoryTest {

	@Autowired
	private QuartoRepository quartoRepository;

	@DisplayName("Testando o save")
	@Test
	void testSalvarRespository() {
		// Given / Arrange
		Quarto quarto1 = new Quarto(null, "3242", "suite");
		// When / Act
		Quarto saveQuarto = quartoRepository.save(quarto1);
		// Then / Assert
		Assertions.assertNotNull(saveQuarto);
		Assertions.assertTrue(saveQuarto.getId() > 0);
	}

	@DisplayName("Testando Get para todos Quartos")
	@Test
	void testGetAllRespository() {
		// Given / Arrange
		Quarto quarto1 = new Quarto(null, "3242", "suite");
		Quarto quarto2 = new Quarto(null, "3575", "premium");
		quartoRepository.save(quarto1);
		quartoRepository.save(quarto2);

		// When / Act
		List<Quarto> quartoList = quartoRepository.findAll();
		// Then / Assert
		Assertions.assertNotNull(quartoList);
		Assertions.assertEquals(2, quartoList.size());

	}

	@DisplayName("Testando GET By ID")
	@Test
	void testGetById() {

		Quarto quarto1 = new Quarto(null, "3575", "premium");

		quartoRepository.save(quarto1);

		Quarto saveQuarto = quartoRepository.findById(quarto1.getId()).get();

		Assertions.assertNotNull(saveQuarto);
		Assertions.assertEquals(quarto1.getId(), saveQuarto.getId());
	}

	@DisplayName("Testando o update")
	@Test
	void testUpdateHospede() {
		// Given / Arrange
		Quarto quarto1 = new Quarto (null, "3242", "suite");
		quartoRepository.save(quarto1);
		// When / Act
		Quarto saveQuarto = quartoRepository.findById(quarto1.getId()).get();
		quarto1.setNum("7754");
		quarto1.setTipo("plus");
		Quarto updateQuarto = quartoRepository.save(saveQuarto);
		// Then / Assert
		Assertions.assertNotNull(updateQuarto);
		Assertions.assertEquals("7754", updateQuarto.getNum());
		Assertions.assertEquals("plus", updateQuarto.getTipo());

	}
	
	@DisplayName("testando o Delete")
	@Test
	void testDeleteQuarto () {
		
		Quarto quarto1 =new Quarto(null, "3575", "premium");
		
		quartoRepository.save(quarto1);
		
		quartoRepository.deleteById(quarto1.getId());
		
		Optional<Quarto> quartoOptional = quartoRepository.findById(quarto1.getId());
		
		
		Assertions.assertTrue(quartoOptional.isEmpty());
		
	}

}