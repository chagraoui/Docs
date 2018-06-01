package org.tux.config;


import java.util.Arrays;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.tux.config.security.CORSFilter;
import org.tux.config.security.CsrfTokenResponseCookieBindingFilter;
import org.tux.config.security.RESTAuthenticationEntryPoint;
import org.tux.config.security.RESTAuthenticationSuccessHandler;
import org.tux.config.security.RESTLogoutSuccessHandler;



@Configuration
@EnableWebSecurity
@ComponentScan("org.tux.config.security")
@PropertySource("file://${catalina.base}/conf/documentation/ws.properties")
public class SecuirtyConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	Environment env;

	@Resource
	private AuthenticationEntryPoint authenticationEntryPoint;
	@Resource
	private AuthenticationFailureHandler authenticationFailureHandler;
	@Resource
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	@Resource
	private CORSFilter corsFilter;
	@Resource
	private LogoutSuccessHandler logoutSuccessHandler;

	@Resource(name = "securityUserDetailsService")
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		String baseWsURL="/rest*/**";
		http.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/*/**").permitAll()
				.antMatchers("/login", "/rest/open/**", "/sdoc.jsp**", "/css/**", "/images/**", "/lib/**",
						"/swagger-ui.js", "/api-docs/**")
				.permitAll().antMatchers("/logout", "/rest/**").authenticated();

		// Handlers and entry points
		http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
		http.formLogin().successHandler(authenticationSuccessHandler);
		http.formLogin().failureHandler(authenticationFailureHandler);

		// Logout
		http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);

		// CORS
		http.addFilterBefore(corsFilter, ChannelProcessingFilter.class);

		// CSRF
		http.csrf().requireCsrfProtectionMatcher(new AndRequestMatcher(
				// Apply CSRF protection to all paths that do NOT match the ones
				// below

				// We disable CSRF at login/logout, but only for OPTIONS methods
				new NegatedRequestMatcher(new AntPathRequestMatcher("/login*/**", HttpMethod.OPTIONS.toString())),
				new NegatedRequestMatcher(new AntPathRequestMatcher("/logout*/**", HttpMethod.OPTIONS.toString())),

				// We disable CSRF for swagger
				new NegatedRequestMatcher(new AntPathRequestMatcher("/sdoc.jsp**", HttpMethod.GET.toString())),
				new NegatedRequestMatcher(new AntPathRequestMatcher("/css/**", HttpMethod.GET.toString())),
				new NegatedRequestMatcher(new AntPathRequestMatcher("/images/**", HttpMethod.GET.toString())),
				new NegatedRequestMatcher(new AntPathRequestMatcher("/lib/**", HttpMethod.GET.toString())),
				new NegatedRequestMatcher(new AntPathRequestMatcher("/swagger-ui.js", HttpMethod.GET.toString())),
				new NegatedRequestMatcher(new AntPathRequestMatcher("/api-docs/**", HttpMethod.GET.toString())),

				new NegatedRequestMatcher(new AntPathRequestMatcher(baseWsURL, HttpMethod.GET.toString())),
				new NegatedRequestMatcher(new AntPathRequestMatcher(baseWsURL, HttpMethod.HEAD.toString())),
				new NegatedRequestMatcher(new AntPathRequestMatcher(baseWsURL, HttpMethod.OPTIONS.toString())),
				new NegatedRequestMatcher(new AntPathRequestMatcher(baseWsURL, HttpMethod.TRACE.toString())),
				new NegatedRequestMatcher(new AntPathRequestMatcher("/rest/open*/**"))));
		http.addFilterAfter(new CsrfTokenResponseCookieBindingFilter(), CsrfFilter.class); // CSRF
																							// tokens
																							// handling

	}

	/* security */

	@Bean
	public RESTAuthenticationEntryPoint authenticationEntryPoint() {
		return new RESTAuthenticationEntryPoint();
	}

	@Bean
	public SimpleUrlAuthenticationFailureHandler authenticationFailureHandler() {
		return new SimpleUrlAuthenticationFailureHandler();
	}

	@Bean
	public RESTAuthenticationSuccessHandler authenticationSuccessHandler() {
		return new RESTAuthenticationSuccessHandler();
	}

	@Bean
	public CORSFilter corsFilter(Environment env) {
		return new CORSFilter(Arrays.asList(env.getProperty("allowedOrigins").split("\\s*,\\s*")));
	}

	@Bean
	public LogoutSuccessHandler logoutSuccessHandler() {
		return new RESTLogoutSuccessHandler();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}

