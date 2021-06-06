package org.flopez.escalab.books.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ConfiguracionRecursos extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		// Se pueden agregar mas metodos HTTP para que puedan acceder 
		// sin el token de autorizacion.
		// Para todos los demas si se necesita el token
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/categorias").permitAll()
		.anyRequest().authenticated();
	}
}
