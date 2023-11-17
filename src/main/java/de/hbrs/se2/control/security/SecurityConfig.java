package de.hbrs.se2.control.security;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import de.hbrs.se2.util.Encryption;
import de.hbrs.se2.views.routes.login.LoginView;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends VaadinWebSecurity {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
           authorizationManagerRequestMatcherRegistry.requestMatchers(
                   AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/images/*.png")).permitAll();
        });
        super.configure(http);
        setLoginView(http, LoginView.class);
    }

    @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder().username("").password(Encryption.sha256("")).roles("USER").build();
        UserDetails admin = User.builder().username("admin").password(Encryption.sha256("admin123")).roles("USER", "ADMIN").build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
