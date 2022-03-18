package com.example.fortest.integrationtest;

import com.example.fortest.domain.member.domain.Member;
import com.example.fortest.domain.member.dto.MemberResponseDto;
import com.example.fortest.domain.member.repository.MemberRepository;
import com.example.fortest.domain.member.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("리스트를 반환 하는가?")
    void getList(){
        /*
        given
         */
        Member member1 = Member.builder().name("hi1").age(10).build();
        Member member2 = Member.builder().name("hi2").age(20).build();
        memberRepository.save(member1);
        memberRepository.save(member2);
        /*
        when
         */
        List<MemberResponseDto.ListDto> all = memberService.findAll();
        /*
        then
         */
        assertThat(all.size()).isEqualTo(2);
        assertThat(all.get(0).getName()).isEqualTo("hi1");
    }

    @Test
    @DisplayName("멤버 생성 성공")
    void createMemberSuccess(){
        Long memberId = memberService.createMember("hi1", 10);
        assertThat(memberId).isEqualTo(1l);
    }

    @Test
    @DisplayName("멤버 생성시 member1 과 이름이 같아서 예외 발생")
    void createMemberFail(){
        Long memberId = memberService.createMember("hi1", 10);
        assertThatThrownBy(() -> memberService.createMember("hi1",12)).isInstanceOf(IllegalStateException.class);
    }
}
