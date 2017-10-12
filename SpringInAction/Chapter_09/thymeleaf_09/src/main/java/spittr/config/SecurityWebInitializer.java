package spittr.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebInitializer
		extends AbstractSecurityWebApplicationInitializer {
//	public SecurityWebInitializer() {
		// fixed java.lang.IllegalStateException: No WebApplicationContext
		// found: no ContextLoaderListener registered?
//		super(SecurityConfig.class);
//	}
}
