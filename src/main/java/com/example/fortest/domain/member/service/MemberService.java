package com.example.fortest.domain.member.service;

import com.example.fortest.domain.member.dto.MemberResponseDto;

import java.util.List;

public interface MemberService {

    List<MemberResponseDto.ListDto> findAll();
    Long createMember(String name, int age);
    Long changeAge(String name, int age);

}
