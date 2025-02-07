package com.study.backend.config.user;

import com.study.backend.com.mapper.MemberMapper;
import com.study.backend.dto.MemberDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(MemberDTO member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberMapper.insertMember(member);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var result = memberMapper.findByUsername(username);
        if(result.isEmpty()) {
            throw new UsernameNotFoundException("아이디 없음");
        }
        MemberDTO user = (MemberDTO) result.get(); // MemberDTO 객체
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("일반유져"));
        CustomUser customUser = new CustomUser(user.getUsername(), user.getPassword(), authorities); // getUsername() 호출
        customUser.displayName = user.getDisplayName();
        return customUser;
    }
}
