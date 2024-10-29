package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.entities.Hospede;
import com.example.repository.HospedeRepository;

import jakarta.transaction.Transactional;

@SpringBootTest // Carrega o contexto completo do Spring para testes
@Transactional // Garante que as operações no banho de dados serão revertidos após cada teste
class HospedeServiceTest {

	@Autowired
	private HospedeService hospedeService;

	@Autowired
	private HospedeRepository hospedeRepository;

	@BeforeEach
	void setUp() {
		hospedeRepository.deleteAll(); // Limpa o banco de dados antes de cada teste
	}

	@DisplayName("Testando salvar Hóspede")
	@Test
	void testSalvarHospede() {
		Hospede hospede = new Hospede(null, "Julia Maria", "julia@gmail.com", "(00)00000-0000");

		Hospede resultado = hospedeService.salvaHospede(hospede);

		assertNotNull(resultado);
		assertEquals("Julia Maria", resultado.getNome());
		assertTrue(resultado.getId() > 0);
	}

	@DisplayName("Testando listar todos os Hóspedes")
	@Test
	void testListarTodos() {
		Hospede hospede1 = new Hospede(null, "Julia Maria", "julia@gmail.com", "(00)00000-0000");
		Hospede hospede2 = new Hospede(null, "Julia Maria", "julia@gmail.com", "(00)00000-0000");

		hospedeService.salvaHospede(hospede1);
		hospedeService.salvaHospede(hospede2);

		List<Hospede> resultado = hospedeService.buscaTodosHospedes();

		assertNotNull(resultado);
		assertEquals(2, resultado.size());

	}

	@DisplayName("Testando buscar Hóspede por ID")
	@Test
	void testBuscarPorId() {
		Hospede hospede = new Hospede(null, "Julia Maria", "julia@gmail.com", "(00)00000-0000");

		Hospede salvo = hospedeService.salvaHospede(hospede);
		Optional<Hospede> resultado = hospedeService.buscaHospedeId(salvo.getId());

		assertTrue(resultado.isPresent());
		assertEquals("Julia Maria", resultado.get().getNome());
	}
	
	@DisplayName("Testando atualizar Hóspedes")
	@Test
	void testAtualizarHospede() {
		Hospede hospede = new Hospede (null, "Julia", "julia@gmail.com", "(11)11111-1111");
		Hospede salvo = hospedeService.salvaHospede(hospede);
		
		salvo.setNome("Mel");
		salvo.setEmail("Mel@gmail.com");
		
		Hospede atualizado = hospedeService.alterarHospede(salvo);
		
		assertNotNull(atualizado);
		assertEquals("Mel", atualizado.getNome());
		assertEquals("Mel@gmail.com" , atualizado.getEmail());
	}
	
	@DisplayName ("Testado deletar Hóspede")
	@Test
	void testDeletarHospede() {
		Hospede hospede = new Hospede(null, "Julia Maria", "julia@gmail.com")
	}
}
