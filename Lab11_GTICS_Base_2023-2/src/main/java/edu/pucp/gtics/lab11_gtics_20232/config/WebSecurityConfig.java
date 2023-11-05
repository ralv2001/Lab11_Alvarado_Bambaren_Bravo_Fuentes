package edu.pucp.gtics.lab11_gtics_20232.config;

/*
@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.csrf().disable();
        http.httpBasic();

        http.authorizeHttpRequests()
                .requestMatchers("/generos").authenticated()
                .anyRequest().permitAll();

        return http.build();
    }

}

 */