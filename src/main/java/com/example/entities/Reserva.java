package com.example.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reserva")
public class Reserva {
 
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
 
    private Long id;
	
	private LocalDate CheckInData;
	
	private LocalDate CheckOutData;
	
	
	@ManyToOne
    private Hospede hospede;
	
	@ManyToOne
    private Quarto quarto;
}
