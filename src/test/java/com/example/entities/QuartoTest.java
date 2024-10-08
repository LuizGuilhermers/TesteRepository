package com.example.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QuartoTest {

	private Quarto quarto;

	@BeforeEach
	void setUp() {
		quarto = new Quarto(1L, "20", "Casal");
	}

	@Test
	@DisplayName("Testando o getter e setter do campo ID")
	void testGetSetId() {
		quarto.setId(2L);
		assertEquals(2L, quarto.getId());
	}

	@Test
	@DisplayName("Testando o getter e setter do campo Tipo")
	void testGetSetTipo() {
		quarto.setTipo("suite");
		assertEquals("suite", quarto.getTipo());

	}

	@Test
	@DisplayName("Testando o getter e setter do campo Num")
	void testGetSetName() {
		quarto.setNum("1");
		assertEquals("1", quarto.getNum());
	}

	@Test
	@DisplayName("Testando o método construtor da classe Quarto")
	void testQuartoConstructor() {
		Quarto novoQuarto = new Quarto(3L, "Suite2", "11");
		assertAll("novoQuarto", () -> assertEquals(3L, novoQuarto.getId(), "Error ID"),
				() -> assertEquals("Suite2", novoQuarto.getTipo(), "Error Num"),
				() -> assertEquals("11", novoQuarto.getNum(), "Erro Tipo"));

	}

}
