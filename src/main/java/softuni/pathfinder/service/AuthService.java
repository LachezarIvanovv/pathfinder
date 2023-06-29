package softuni.pathfinder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import softuni.pathfinder.model.User;
import softuni.pathfinder.model.dto.userRegistrationDTO;
import softuni.pathfinder.repository.UserRepository;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(userRegistrationDTO registrationDTO){
        if(!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())){
            throw new RuntimeException("passwords.match");
        }

        Optional<User> byEmail = userRepository.findByEmail(registrationDTO.getEmail());

        if(byEmail.isPresent()){
            throw new RuntimeException("email.used");
        }

        User user = new User(
                registrationDTO.getUsername(),
                passwordEncoder.encode(registrationDTO.getPassword()),
                registrationDTO.getEmail(),
                registrationDTO.getFullName(),
                registrationDTO.getAge()
        );

        this.userRepository.save(user);
    }

    public User getUser(String username){
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " was not found"));
    }
}
