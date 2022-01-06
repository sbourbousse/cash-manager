package epitech.backend.web.compte.config;

import com.google.common.collect.ImmutableList;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletResponse;
import java.util.Timer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter implements InitializingBean, DisposableBean {

    private Timer backgroundTaskTimer;
    private MultiThreadedHttpConnectionManager multiThreadedHttpConnectionManager;

    public void init() {
        this.backgroundTaskTimer = new Timer(true);
        this.multiThreadedHttpConnectionManager = new MultiThreadedHttpConnectionManager();
    }

    public void shutdown() {
        this.backgroundTaskTimer.purge();
        this.backgroundTaskTimer.cancel();
        this.multiThreadedHttpConnectionManager.shutdown();
    }

    /**
     * Defines the web based security configuration.
     *
     * @param   http It allows configuring web based security for specific http requests.
     * @throws  Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //désactive le Cross-Site Request Forgery CSRF attacks qui nous empêche de fonctionner en API
                //avec csrf enabled on doit ajouter au formulaire de connexion un token unique,
                //généré à l'affichage du formulaire -> impossible en fonctionnement actuel.
                .cors().and()
                .csrf().disable()
                .exceptionHandling();
        http
                .authorizeRequests()
                .antMatchers("/utilisateur/verifyCaptcha").permitAll()
                .antMatchers("/compte/**").anonymous();
        http
                .httpBasic()
                .and()
                .headers()
                .httpStrictTransportSecurity()
                .maxAgeInSeconds(31536000)
                .includeSubDomains(true)
                .and()
                .frameOptions()
                .sameOrigin()
                .and()
                .logout()
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.addHeader("Content-Type", "application/json");
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().flush();
                });
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(ImmutableList.of("*"));
        configuration.setAllowedMethods(ImmutableList.of("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        // setAllowCredentials(true) is important, otherwise:
        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
        configuration.setAllowCredentials(true);
        // setAllowedHeaders is important! Without it, OPTIONS preflight request
        // will fail with 403 Invalid CORS request
        configuration.setAllowedHeaders(ImmutableList.of("Access-Control-Allow-Headers","Authorization", "Cache-Control", "Content-Type", "Access-Control-Allow-Origin", "X-Frame-Options"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    @Override
    public void destroy() throws Exception {
        shutdown();
    }

}
