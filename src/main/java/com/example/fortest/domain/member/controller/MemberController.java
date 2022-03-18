package com.example.fortest.domain.member.controller;

import com.example.fortest.domain.member.dto.MemberRequestDto;
import com.example.fortest.domain.member.dto.MemberResponseDto;
import com.example.fortest.domain.member.service.MemberService;
import com.example.fortest.domain.member.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members")
    public List<MemberResponseDto.ListDto> getMemberList(){
        return memberService.findAll();
    }

    @PostMapping("/members")
    public Long createMember(@RequestBody MemberRequestDto.CreateDto createDto){
        return memberService.createMember(createDto.getName(), createDto.getAge());
    }


    @PatchMapping("/members")
    public Long changeAge(@RequestBody MemberRequestDto.ChangeAgeDto changeAgeDto){
        return memberService.changeAge(changeAgeDto.getName(),changeAgeDto.getAge());
    }
}
