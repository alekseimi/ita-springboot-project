package ita.springboot.application.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import ita.springboot.application.model.User;
import ita.springboot.application.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}
