package com.study.backend.com.controller;



import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.Cookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.study.backend.config.filter.JwtUtil;
import com.study.backend.config.user.CustomUserDetailsService;
import com.study.backend.dto.MemberDTO;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final CustomUserDetailsService userService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody MemberDTO member) {
        userService.registerUser(member);
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<String> loginJWT(@RequestBody Map<String, String> data, HttpServletResponse response) throws IOException {
        var authToken = new UsernamePasswordAuthenticationToken(data.get("username"), data.get("password"));
        var auth = authenticationManagerBuilder.getObject().authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);

        var jwt = JwtUtil.createToken(SecurityContextHolder.getContext().getAuthentication());
        var cookie= new Cookie("jwt",jwt);
        cookie.setHttpOnly(false);
        cookie.setMaxAge(100);
        cookie.setPath("/");
        response.addCookie(cookie);

        System.out.println(jwt);

        // ✅ JWT와 username을 응답 바디에도 포함시킴


        return ResponseEntity.status(HttpStatus.OK).body("로그인되었습니다.");
    }

}