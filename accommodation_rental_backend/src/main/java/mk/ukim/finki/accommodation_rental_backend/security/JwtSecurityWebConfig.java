package mk.ukim.finki.accommodation_rental_backend.security;

import mk.ukim.finki.accommodation_rental_backend.config.CustomUsernamePasswordAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Profile("dev")
@Configuration
@EnableWebSecurity
public class JwtSecurityWebConfig {

    private final CustomUsernamePasswordAuthenticationProvider authenticationProvider;
    private final JwtFilter jwtFilter;

    public JwtSecurityWebConfig(CustomUsernamePasswordAuthenticationProvider authenticationProvider, JwtFilter jwtFilter) {
        this.authenticationProvider = authenticationProvider;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(corsCustomizer ->
                        corsCustomizer.configurationSource(corsConfigurationSource())
                )
                .authorizeHttpRequests(authorizeHttpRequestsCustomizer ->
                        authorizeHttpRequestsCustomizer
                                .requestMatchers(
                                        "/docs",
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**",
                                        "/api/auth/register",
                                        "/api/auth/login",
                                        "/test/test/test/test/test/test/test/test",
                                        "/api/country",
                                        "/api/host",
                                        "/api/accommodation",
                                        "/api/country/**",
                                        "/api/host/**",
                                        "/api/accommodation/**"
                                )
                                .permitAll()
                                .anyRequest().permitAll()
//                                .requestMatchers(
//                                        "/api/country",
//                                        "/api/host",
//                                        "/api/accommodation"
//                                )
//                                .hasAnyRole("USER", "HOST")
//                                .anyRequest()
//                                .hasRole("HOST")
                )
                .sessionManagement(sessionManagementConfigurer ->
                        sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


}
