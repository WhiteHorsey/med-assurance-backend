package ma.medass.config;

import ma.medass.security.*;
import ma.medass.security.jwt.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.web.filter.CorsFilter;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;
import tech.jhipster.config.JHipsterProperties;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Import(SecurityProblemSupport.class)
public class SecurityConfiguration {

    private final JHipsterProperties jHipsterProperties;

    private final TokenProvider tokenProvider;

    private final CorsFilter corsFilter;
    private final SecurityProblemSupport problemSupport;

    public SecurityConfiguration(
        TokenProvider tokenProvider,
        CorsFilter corsFilter,
        JHipsterProperties jHipsterProperties,
        SecurityProblemSupport problemSupport
    ) {
        this.tokenProvider = tokenProvider;
        this.corsFilter = corsFilter;
        this.problemSupport = problemSupport;
        this.jHipsterProperties = jHipsterProperties;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .csrf()
            .disable()
            .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling()
                .authenticationEntryPoint(problemSupport)
                .accessDeniedHandler(problemSupport)
        .and()
            .headers()
            .contentSecurityPolicy(jHipsterProperties.getSecurity().getContentSecurityPolicy())
        .and()
            .referrerPolicy(ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN)
        .and()
            .permissionsPolicy().policy("camera=(), fullscreen=(self), geolocation=(), gyroscope=(), magnetometer=(), microphone=(), midi=(), payment=(), sync-xhr=()")
        .and()
            .frameOptions()
            .sameOrigin()
        .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .antMatchers("/api/authenticate").permitAll()
            .antMatchers("/api/register").permitAll()
            .antMatchers("/api/activate").permitAll()
            .antMatchers("/api/account/reset-password/init").permitAll()
            .antMatchers("/api/account/reset-password/finish").permitAll()
            .antMatchers("/api/admin/**").hasAuthority(AuthoritiesConstants.ADMIN)
            .antMatchers("/api/users/**").authenticated()
            .antMatchers("/api/prv/**").authenticated()
            .antMatchers(HttpMethod.POST, "/api/pbl/demande").permitAll()
            .antMatchers(HttpMethod.POST, "/api/pbl/demande/suivi").permitAll()
            .antMatchers(HttpMethod.GET,  "/api/pbl/demandeur/*").permitAll()
            .antMatchers(HttpMethod.POST, "/api/pbl/media").permitAll()
            .antMatchers(HttpMethod.GET,  "/api/pbl/media/ownerType/*/ownerId/*").permitAll()
            .antMatchers(HttpMethod.POST, "/api/pbl/etatDemandeHist").permitAll()
            .antMatchers(HttpMethod.GET,  "/api/pbl/etatDemandeHist/last/*").permitAll()
            .antMatchers(HttpMethod.GET,  "/api/pbl/civilite/**").permitAll()
            .antMatchers(HttpMethod.GET,  "/api/pbl/ville/**").permitAll()
            .antMatchers(HttpMethod.GET,  "/api/pbl/branche/**").permitAll()
            .antMatchers(HttpMethod.GET,  "/api/pbl/typeDemandeur/**").permitAll()
            .antMatchers(HttpMethod.GET,  "/api/pbl/natureLitige/**").permitAll()
            .antMatchers(HttpMethod.GET,  "/api/pbl/natureSinistre/**").permitAll()
            .antMatchers(HttpMethod.GET,  "/api/pbl/objetLitige/**").permitAll()
            .antMatchers(HttpMethod.GET,  "/api/pbl/compagnie/**").permitAll()
            .antMatchers(HttpMethod.GET,  "/api/pbl/actualite/**").permitAll()
            .antMatchers(HttpMethod.GET,  "/api/pbl/etatDemande/**").permitAll()
            .antMatchers(HttpMethod.GET,  "/api/pbl/resultatDemande/**").permitAll()
            .antMatchers("/api/pbl/**").authenticated()
        .and()
            .httpBasic()
        .and()
            .apply(securityConfigurerAdapter());
        return http.build();
        // @formatter:on
    }

    private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(tokenProvider);
    }
}
