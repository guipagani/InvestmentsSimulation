package com.InvestmentSimulation.StudyProject.services;

import com.InvestmentSimulation.StudyProject.model.User;
import com.InvestmentSimulation.StudyProject.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(User user) {
        user.setSenha(encoder.encode(user.getSenha()));
        return userRepository.save(user);
    }

    public boolean login(String email, String senha) {
        return userRepository.findByEmail(email)
                .filter(u -> encoder.matches(senha, u.getSenha()))
                .isPresent();
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}