package com.InvestmentSimulation.StudyProject.controller;

import com.InvestmentSimulation.StudyProject.model.Investment;
import com.InvestmentSimulation.StudyProject.model.User;
import com.InvestmentSimulation.StudyProject.services.InvestmentService;
import com.InvestmentSimulation.StudyProject.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/investments")
public class InvestmentController {

    private final InvestmentService investmentService;
    private final UserService userService;

    public InvestmentController(InvestmentService investmentService, UserService userService) {
        this.investmentService = investmentService;
        this.userService = userService;
    }

    @PostMapping
    public Investment createInvestment(@RequestBody InvestmentRequest request) {
        User usuario = userService.findById(request.getUsuarioId());
        if (usuario == null) {
            throw new RuntimeException("User not found");
        }
        Investment investment = new Investment();
        investment.setAcao(request.getAcao());
        investment.setValor(request.getValor());
        investment.setUsuario(usuario);
        return investmentService.register(investment);
    }

    @GetMapping
    public List<Investment> getAllInvestments() {
        return investmentService.findAll();
    }

    @GetMapping("/{id}")
    public Investment getInvestmentById(@PathVariable Long id) {
        Investment investment = investmentService.findById(id);
        if (investment == null) {
            throw new RuntimeException("Investment not found");
        }
        return investment;
    }

    @DeleteMapping("/{id}")
    public String deleteInvestment(@PathVariable Long id) {
        Investment investment = investmentService.findById(id);
        if (investment == null) {
            return "Investment not found";
        }
        investmentService.deleteById(id);
        return "Investment deleted";
    }

    @GetMapping("/{id}/retorno")
    public Map<String, Object> getInvestmentReturn(@PathVariable Long id) {
        Investment investment = investmentService.findById(id);
        if (investment == null) {
            throw new RuntimeException("Investment not found");
        }
        return investmentService.calcularRetorno(investment);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InvestmentRequest {
        private String acao;
        private BigDecimal valor;
        private Long usuarioId;
    }
}