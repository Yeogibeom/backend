package com.study.backend.com.mapper;

import com.study.backend.dto.MemberDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.lang.reflect.Member;
import java.util.Optional;

@Mapper
public interface MemberMapper {

    void insertMember(MemberDTO member);


    Optional<Member> findByUsername(String username);
}
