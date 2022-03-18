package com.example.fortest.domain.member.service;

import com.example.fortest.domain.member.domain.Member;
import com.example.fortest.domain.member.dto.MemberResponseDto;
import com.example.fortest.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    @Override
    public List<MemberResponseDto.ListDto> findAll(){
        return memberRepository.findAll().stream().map(a -> new MemberResponseDto.ListDto(a.getName(),a.getAge()))
                .collect(Collectors.toList());
    }

    @Override
    public Long createMember(String name, int age){
        memberRepository.findByName(name).ifPresent(a -> {
            throw new IllegalStateException("이미 있는 아이디");
        });

        Member member = Member.builder()
                .age(age)
                .name(name).build();

        return memberRepository.save(member).getId();
    }

    @Override
    public Long changeAge(String name, int age) {
        Member member = memberRepository.findByName(name).orElseThrow(() -> {
            throw new IllegalStateException("없는 멤버 입니다.");
        });
        member.changeAge(age);
        return member.getId();
    }
}
