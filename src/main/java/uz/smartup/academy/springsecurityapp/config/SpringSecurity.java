package uz.smartup.academy.springsecurityapp.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

//
@Configuration
public class SpringSecurity {
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager detailsManager = new JdbcUserDetailsManager(dataSource);

        //detailsManager.setUsersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?");
        //detailsManager.setAuthoritiesByUsernameQuery("SELECT username, role FROM role WHERE username = ?");

        detailsManager.setUsersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?");
        detailsManager.setAuthoritiesByUsernameQuery("""
                SELECT
                    u.username,
                            (SELECT r.name FROM roles r WHERE r.id = ur.role_id) AS roles
                    FROM
                    users u,
                    users_roles ur
                    WHERE
                    u.id = ur.user_id
                    AND u.username = ?
                """);


        return detailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authRegistry ->
                        authRegistry
                                .anyRequest()
                                .authenticated())
                .formLogin(form->
                        form
                                .loginPage("/login")
                                .loginProcessingUrl("/authenticate")
                                .permitAll())
                .logout(LogoutConfigurer::permitAll);
        return http.build();
    }



}
