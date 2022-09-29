package com.vincent.password_manager.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.vincent.password_manager.security.handler.AccessDeniedHandlerImpl;
import com.vincent.password_manager.security.handler.AuthenticationEntryPointImpl;
import com.vincent.password_manager.security.handler.AuthenticationFailureHandlerImpl;
import com.vincent.password_manager.security.handler.AuthenticationSuccessHandlerImpl;
import com.vincent.password_manager.security.handler.LogoutSuccessHandlerImpl;
import com.vincent.password_manager.utils.ConstantType;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
	private AuthenticationEntryPointImpl authenticationEntryPointImpl;
	@Autowired
	private AccessDeniedHandlerImpl accessDeniedHandlerImpl;
	@Autowired
	private AuthenticationSuccessHandlerImpl authenticationSuccessHandlerImpl;
	@Autowired
	private AuthenticationFailureHandlerImpl authenticationFailureHandlerImpl;
	@Autowired
	private LogoutSuccessHandlerImpl logoutSuccessHandlerImpl;

    @Override
    protected void configure(HttpSecurity http) throws Exception 
    {
        http.csrf().disable();

        http.cors();
        
        http.authorizeRequests((requests) -> requests
                                            .anyRequest()
                                            .permitAll());

        http.exceptionHandling()
			.accessDeniedHandler(accessDeniedHandlerImpl)
			.authenticationEntryPoint(authenticationEntryPointImpl); //this handler will make default login page disable
		
        http.formLogin()
            .usernameParameter("username")
            .passwordParameter("password")
            .successHandler(authenticationSuccessHandlerImpl)
            .failureHandler(authenticationFailureHandlerImpl);

        http.logout()
            .permitAll()
            .logoutUrl("/logout")
            .logoutSuccessHandler(logoutSuccessHandlerImpl);

		http.httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new Sha256PasswordEncoder();
    }

    @Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception 
    {
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(new Sha256PasswordEncoder());
	}

    @Bean
    CorsConfigurationSource corsConfigurationSource() 
    {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(ConstantType.ACCESS_CONTROL_ALLOW_ORIGIN));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Origin", "X-Requested-With", "Content-Type", "Accept"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
