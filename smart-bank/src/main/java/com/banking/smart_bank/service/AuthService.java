package com.banking.smart_bank.service;

import com.banking.smart_bank.dto.LoginResponse;
import com.banking.smart_bank.entity.User;
import com.banking.smart_bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Service
public class AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private UserRepository userRepository;

    public LoginResponse authenticate(String username, String password) {
        logger.debug("In AuthService #21- Attempting authentication for username: {}", username);
        logger.debug("In AuthService #22- Attempting authentication for password: {}", password);

        // Use orElseThrow to handle missing user directly
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            if (optionalUser.get().getPassword().equals(password)){
                return new LoginResponse(optionalUser.get().getId(), optionalUser.get().getUsername(), optionalUser.get().getRole());
            }
        }

        logger.debug("Password mismatch for user: {}", username);
        return null;  // Return null if authentication fails
    }
}
