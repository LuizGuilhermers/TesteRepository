package com.example.entities;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HospedeTest {

	private Hospede hospede;

	@BeforeEach
	void setUp() {
		hospede = new Hospede(1L, "Joao Silva", "joao.silva@example.com", "123456789");
	}

	@Test
	@DisplayName("Testando o getter e setter do campo ID")
	void testGetSetId() {
		hospede.setId(2L);
		assertEquals(2L, hospede.getId());
	}

	@Test
	@DisplayName("Testando o getter e setter do campo Nome")
	void testGetSetName() {
		hospede.setNome("Maria Oliveira");
		assertEquals("Maria Oliveira", hospede.getNome());
	}

	@Test
	@DisplayName("Testando o getter e setter do campo Email")
	void testGetSetEmail() {
		hospede.setEmail("maria.oliveira@example.com");
		assertEquals("maria.oliveira@example.com", hospede.getEmail());

	}

	@Test
	@DisplayName("Testando o getter e setter do campo Telefone")
	void testGetSetTelefone() {
		hospede.setTelefone("987654321");
		assertEquals("987654321", hospede.getTelefone());

	}

	@Test
	@DisplayName("Testando o método construtor da classe Hospede")
	void testHospedeConstructor() {
		Hospede novoHospede = new Hospede(3L, "Carlos Mendes", "carlos.mendes@example.com", "555555555");
		assertAll("novoHospede", () -> assertEquals(3L, novoHospede.getId()),
				() -> assertEquals("Carlos Mendes", novoHospede.getNome()),
				() -> assertEquals("carlos.mendes@example.com", novoHospede.getEmail()),
				() -> assertEquals("555555555", novoHospede.getTelefone()));

	}

}
