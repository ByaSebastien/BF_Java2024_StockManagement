package be.bstorm.bf_java2024_stockmanagement.il.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(r ->
//                        r.requestMatchers("/article/**").authenticated()
//                                .requestMatchers("/admin/**").hasAuthority("ADMIN")
//                                .requestMatchers("/user/**").hasAuthority("USER")
                        r.requestMatchers("/styles/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login").permitAll()
                        .defaultSuccessUrl("/article")
                )
                .logout(logout -> logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/article")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"))
                .exceptionHandling(e ->
                        e.accessDeniedPage("/login?error=true")
                )
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
