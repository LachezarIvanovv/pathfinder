package softuni.pathfinder.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import softuni.pathfinder.repository.UserRepository;
import softuni.pathfinder.service.PathfinderUserDetailsService;

@Configuration
@EnableWebSecurity
public class PathfinderSecurityConfiguration {

    private final UserRepository userRepository;

    public PathfinderSecurityConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.
                authorizeHttpRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/", "/login", "/register").permitAll()
                .requestMatchers("/profile").authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureForwardUrl("/login?error=true")
                .and()
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/")
                .and()
                .csrf().disable();

        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService(){
        return new PathfinderUserDetailsService(userRepository);
    }
}
