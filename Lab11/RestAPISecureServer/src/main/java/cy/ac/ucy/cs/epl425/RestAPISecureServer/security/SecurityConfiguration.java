package cy.ac.ucy.cs.epl425.RestAPISecureServer.security;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    AuthenticationManager authenticationManager;

    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsManager());
        authenticationManager = authenticationManagerBuilder.build();

        http
            .csrf(csrf->csrf.disable())
            .httpBasic(Customizer.withDefaults())
			.authorizeHttpRequests(authorize -> authorize
				.requestMatchers(HttpMethod.POST, "/api/books/**").hasAnyRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/books/**").hasAnyRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE, "/api/books/**").hasAnyRole("MANAGER")
                .requestMatchers(HttpMethod.GET, "/api/books/**").permitAll()
				.anyRequest().authenticated()
			)
            .authenticationManager(authenticationManager);

		return http.build();
	}

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        List<UserDetails> userDetailsList = new ArrayList<>();
        userDetailsList.add(User
            .withUsername("employee")
            .password(passwordEncoder().encode("epl425$"))
            .roles("EMPLOYEE")
            .build());
        
        userDetailsList.add(User
            .withUsername("manager")
            .password(passwordEncoder().encode("password"))
            .roles("EMPLOYEE", "MANAGER")
            .build());

        return new InMemoryUserDetailsManager(userDetailsList);
    }

    @Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}