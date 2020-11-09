package datn.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private ApiAuthenticationFailureHandler failureHandler;

    @Autowired
    private ApiAuthenticationSuccessHandler successHandler;


    @Autowired
    private AccessTokenAuthenticationProvider accessTokenAuthenticationProvider;

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;


    @Value("${application.enable-security}")
    private boolean enableSecurity;

    private Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addExposedHeader("authorization");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (enableSecurity) {
            LOGGER.info("Enable application security.");
            http.exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint);
            http.authorizeRequests()
                    .antMatchers("/api-docs/**").permitAll()
                    .antMatchers("/api-public/**").permitAll()
                    .antMatchers("/api/**").authenticated()
                    .antMatchers("/actuator/**").authenticated();
        } else {
            LOGGER.info("Disable application security.");
            http.authorizeRequests().anyRequest().permitAll();
        }
        http.formLogin()
                .loginProcessingUrl(SecurityUrls.LOGIN_PROCESS_URL)
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(successHandler)
                .failureHandler(failureHandler);

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable();
        http.addFilterAfter(accessTokenAuthenticationProvider, SecurityContextPersistenceFilter.class);
    }
}
