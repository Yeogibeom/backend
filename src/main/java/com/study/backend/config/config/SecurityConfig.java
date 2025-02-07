//package com.study.backend.config.config;
//
//import java.util.List;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import lombok.RequiredArgsConstructor;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//
//	@Bean
//	protected SecurityFilterChain  securityFilterChain( HttpSecurity http ) throws Exception	{
//		 http
//		 .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//         .csrf(csrf -> csrf.disable()) // CSRF 보호 비활성화 (REST API에서는 일반적으로 필요 없음)
//         .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 사용 안 함
//         .authorizeHttpRequests(auth -> auth
//             .requestMatchers("/auth/**", "/public/**").permitAll() // 인증 없이 접근 가능
//             .requestMatchers("/api/**").authenticated() // `/api/**` 경로는 인증 필요
//         )
//         .httpBasic(Customizer.withDefaults()); // HTTP Basic 인증 (JWT 적용 시 제거)
//
//     return http.build();
//	}
//
//	//webMvcConfig 보다 Spring Security 설정이 우선시 됨
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//		CorsConfiguration config = new CorsConfiguration();
//	    config.setAllowedOrigins(List.of("*"));
//	    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
//	    config.setAllowedHeaders(List.of("*"));
//	    config.setAllowCredentials(false);
//	//test
//	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	    source.registerCorsConfiguration("/**", config);
//	    return source;
//
//    }
//
//
//}

package com.study.backend.config.config;


import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.study.backend.config.filter.JwtFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {


	//	private final CustomUserDetailsService customUserDetailsService;
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors(cors -> cors.configurationSource(corsConfigurationSource()))
				.csrf(csrf -> csrf.disable()) // CSRF 비활성화
				.authorizeHttpRequests(auth ->
						auth.requestMatchers("/**").permitAll()  // 모든 요청 허용 (필요에 따라 조정)
				)

				.addFilterBefore(new JwtFilter(), ExceptionTranslationFilter.class);  // JWT 필터 추가
		http.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/").invalidateHttpSession(true).clearAuthentication(true).logoutSuccessHandler(logoutSuccessHandler()));
		return http.build();

	}


	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(List.of("http://localhost:8081")); // Vue 개발 서버 주소
		corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
		corsConfig.setAllowedHeaders(List.of("*"));
		corsConfig.setAllowCredentials(true);  // 쿠키 포함 가능
		corsConfig.setExposedHeaders(List.of("Authorization", "Content-Type", "Set-Cookie")); // 쿠키 읽기 허용

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);

		return source;
	}


	@Bean
	public LogoutSuccessHandler logoutSuccessHandler() {
		return (request, response, authentication) -> {
			// 세션 무효화
			request.getSession().invalidate();

			// 인증 정보 초기화
			SecurityContextHolder.clearContext();

			// 쿠키 삭제
			response.addHeader("Set-Cookie", "jwt=; Max-Age=0; path=/; HttpOnly; SameSite=Strict");

			response.setStatus(200);
			response.setContentType("application/json");
			response.getWriter().write("{\"message\":\"로그아웃 성공\"}");
			System.out.println("로그아웃 성공");
		};
	}
}
