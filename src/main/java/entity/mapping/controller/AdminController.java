package entity.mapping.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * The AdminController handles authentication and redirection for users in the application.
 */
@RestController
@RequestMapping
public class AdminController {

	/**
	 * Redirects users to the appropriate page after login based on their roles.
	 *
	 * @param authentication the authentication object containing user information.
	 * @return a RedirectView to the user's destination page or an error page.
	 */
	@GetMapping("/login")
	public RedirectView defaultAfterLogin(Authentication authentication) {
		if (authentication != null && authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			return new RedirectView("/admin");
		} else if (authentication != null && authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
			return new RedirectView("/user");
		}
		return new RedirectView("/login?error");
	}

	/**
	 * Displays a welcome message for admin users.
	 *
	 * @param authentication the authentication object containing user information.
	 * @return a welcome message for the admin user.
	 */
	@GetMapping("/admin")
	public String login(Authentication authentication) {
		String userName = authentication.getName();

		return "Spring Security In-memory Authentication Example - Welcome " + userName;
	}

	/**
	 * Displays a welcome message for regular users.
	 *
	 * @param authentication the authentication object containing user information.
	 * @return a welcome message for the regular user.
	 */
	@GetMapping("/user")
	public String user(Authentication authentication) {
		String userName = authentication.getName();

		return "Spring Security In-memory Authentication Example - Welcome " + userName;
	}

}
