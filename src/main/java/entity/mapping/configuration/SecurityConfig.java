package entity.mapping.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import entity.mapping.authenticateprovider.CustomAuthenticationProvider;
import entity.mapping.service.MyUserDetails;
import entity.mapping.service.UserService;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Security configuration class for setting up security features in the application.
 * This includes user authentication, authorization, and password encoding.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig  {

	private final UserService userService;

	/**
	 * Custom authentication provider for handling authentication.
	 */
	@Autowired
	private CustomAuthenticationProvider authenticationProvider;

	/**
	 * Constructs a SecurityConfig with the specified UserService.
	 * 
	 * @param userService the service used for user-related operations.
	 */
	@Autowired
	public SecurityConfig(UserService userService) {
		super();
		this.userService = userService;
	}

	//	@Bean
	//   pubic SecurityFilterChain securityFilterChainDefault(HttpSecurity http) throws Exception {
	//
	//        http.csrf().disable()
	//                .authorizeHttpRequests((authorize) -> {
	//                    authorize.anyRequest().authenticated();
	//                }).httpBasic(Customizer.withDefaults());
	//        return http.build();
	//    }

	/**
	 * Creates an in-memory user details service with predefined users.
	 * 
	 * @return a UserDetailsService containing user details for authentication.
	 */
	@Bean
	public UserDetailsService userDetailsService(){

		UserDetails ramesh = User.builder()
				.username("sowmya")
				.password(passwordEncoder().encode("password"))
				.roles("USER")
				.build();

		UserDetails admin = User.builder()
				.username("admin")
				.password(passwordEncoder().encode("admin"))
				.roles("ADMIN")
				.build();

		return new InMemoryUserDetailsManager(ramesh, admin);
	}

	/**
	 * Configures the security filter chain for HTTP requests.
	 * 
	 * @param http the HttpSecurity object used to configure security settings.
	 * @return a SecurityFilterChain with configured security settings.
	 * @throws Exception if an error occurs during configuration.
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.requestMatchers("/register").permitAll() // Allow everyone to register
		.requestMatchers("/login").permitAll()    // Allow everyone to access login
		.requestMatchers("/user/**").authenticated()
		.and()
		.authorizeRequests()
		.requestMatchers("/user/**")
		.authenticated().and()
		.httpBasic()
		.and()
		.authenticationProvider(authenticationProvider);


		return http.build();
	}

	/**
	 * Provides a password encoder for encoding passwords.
	 * 
	 * @return a PasswordEncoder for encoding passwords using BCrypt.
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Creates an authentication manager for handling authentication requests.
	 * 
	 * @param authenticationConfiguration the configuration used to create the authentication manager.
	 * @return an AuthenticationManager for authenticating users.
	 * @throws Exception if an error occurs while creating the authentication manager.
	 */
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

}