package com.InvestmentSimulation.StudyProject.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "investments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String acao;
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;
}