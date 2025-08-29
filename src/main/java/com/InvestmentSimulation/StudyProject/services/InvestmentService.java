package com.InvestmentSimulation.StudyProject.services;

import com.InvestmentSimulation.StudyProject.model.Investment;
import com.InvestmentSimulation.StudyProject.repository.InvestmentRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InvestmentService {

    private final InvestmentRepository investmentRepository;

    public InvestmentService(InvestmentRepository investmentRepository) {
        this.investmentRepository = investmentRepository;
    }

    public Investment register(Investment investment){
        return investmentRepository.save(investment);
    }

    public List<Investment> findAll() {
        return investmentRepository.findAll();
    }

    public Investment findById(Long id) {
        return investmentRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        investmentRepository.deleteById(id);
    }

    public Map<String, Object> calcularRetorno(Investment investment) {
        Map<String, Object> resultado = new HashMap<>();

        BigDecimal dividendosPorAcao = new BigDecimal("0.10");
        resultado.put("dividendos", dividendosPorAcao);

        BigDecimal retornoProjetado = investment.getValor().multiply(new BigDecimal("0.08"));
        resultado.put("retornoProjetado", retornoProjetado);

        return resultado;
    }
}