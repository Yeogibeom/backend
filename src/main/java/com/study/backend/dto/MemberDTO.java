package com.study.backend.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString

public class MemberDTO {
    private Long id;
    private String username;
    private String password;
    private String displayName;



}
