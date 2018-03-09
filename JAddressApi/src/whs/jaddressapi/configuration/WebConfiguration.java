package whs.jaddressapi.configuration;

import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvcSecurity
/**
 *Configura o acesso da aplicação, os tipos de métodos e os tipos de acesso
 */
public class WebConfiguration extends WebMvcConfigurerAdapter {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowCredentials(false)
		.allowedOrigins("*")
		.allowedHeaders("Content-Type, Access-Control-Allow-Origin, Access-Control-Allow-Headers, Authorization, X-Requested-With, requestId, Correlation-Id")
		.allowedMethods("GET, PUT, POST, DELETE");
	}
	
//	@Bean
//	public FilterRegistrationBean corsFilter() {
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		CorsConfiguration config = new CorsConfiguration();
//		config.setAllowCredentials(true);
//		config.addAllowedOrigin("http://domain1.com");
//		config.addAllowedHeader("*");
//		config.addAllowedMethod("*");
//		source.registerCorsConfiguration("/**", config);
//		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
//		bean.setOrder(0);
//		return bean;
//	}
	
}
