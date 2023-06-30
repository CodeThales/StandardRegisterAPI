package com.thalescoding.registerAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EntityScan(basePackages = {"com.thalescoding.registerAPI.model"})
@ComponentScan(basePackages = {"com.*"})
@EnableJpaRepositories(basePackages = {"com.thalescoding.registerAPI.repository"})
@EnableTransactionManagement
@EnableWebMvc
@RestController
@EnableAutoConfiguration
public class RegisterApiApplication extends SpringBootServletInitializer implements WebMvcConfigurer  {

	public static void main(String[] args) {
		SpringApplication.run(RegisterApiApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(RegisterApiApplication.class);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	//Configuração centralizada de CORS - MAPEAMENTO GLOBAL
	@Override
	public void addCorsMappings(CorsRegistry registry) {

		//Librando acesso a todos os controllers e endpoints de qualquer origem
		//registry.addMapping("/**").allowedMethods("*").allowedOrigins("*");

		//Liberando acesso a todos os endpoints e ações (métodos) de /user de qualquer origem
		registry.addMapping("/user/**")
				.allowedMethods("*")
				.allowedOrigins("*");

		//Liberando acesso a todos os endpoints com ação GET e POST de /user das origens localhost:8080 e localhost:4200
		registry.addMapping("/customer/**")
				.allowedMethods("GET", "POST")
				.allowedOrigins("localhost:8080", "localhost:4200");

	}

	/*Esse trecho funciona como um seed. Toda vez que aplicação é levantada ele é executado.
		É uma solução que ajuda muito na depuração durante o desenvolvimento ;)
	 */
//	@Bean
//	CommandLineRunner run(UserServiceInterface userService){
//		return args -> {
//
//			//Criando as roles
///*			userService.saveRole(new Role(null, "ROLE_USER"));
////			userService.saveRole(new Role(null, "ROLE_MANAGER"));
////			userService.saveRole(new Role(null, "ROLE_ADMIN"));
////			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN")); */
//
//			//Criando users
///*			userService.saveUser(new User(null, "john@gmail.com",
////					"fasKJ098OIU&¨%jgf",
////					"John Travolta" ));
////			userService.saveUser(new User(null, "will@gmail.com",
////					"kj&(56gGr5si",
////					"Will Smith" ));
////			userService.saveUser(new User(null, "jim@gmail.com",
////					"jkÇJ86(*&ÿ%$gvhfgdsogB",
////					"Jim Carry" ));
////			userService.saveUser(new User(null, "arnold@gmail.com",
////					"askdjfÇOIH8976)%$¨&(GKLGflyg",
////					"Arnold Schwarzenegger" )); */
//
//			//Adicionando role aos users
///*			userService.addRoleToUser("john@gmail.com", "ROLE_USER");
////			userService.addRoleToUser("john@gmail.com", "ROLE_MANAGER");
////			userService.addRoleToUser("will@gmail.com", "ROLE_MANAGER");
////			userService.addRoleToUser("jim@gmail.com", "ROLE_ADMIN");
//			userService.addRoleToUser("arnold@gmail.com", "ROLE_SUPER_ADMIN"); */
//		};
//	}


}
