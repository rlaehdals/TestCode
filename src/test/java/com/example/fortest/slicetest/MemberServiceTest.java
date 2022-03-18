package com.example.fortest.slicetest;

import com.example.fortest.domain.member.domain.Member;
import com.example.fortest.domain.member.dto.MemberResponseDto;
import com.example.fortest.domain.member.repository.MemberRepository;
import com.example.fortest.domain.member.service.MemberService;
import com.example.fortest.domain.member.service.MemberServiceImpl;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class MemberServiceTest {

    MemberService memberService;

    @MockBean
    MemberRepository memberRepository;

    @BeforeEach
    void setUp(){
        memberService = new MemberServiceImpl(memberRepository);
    }

    @Test
    @DisplayName("리스트를 반환 하는가?")
    void getList(){
        /*
        given
         */
        Member member1 = Member.builder().name("hi1").age(10).build();
        Member member2 = Member.builder().name("hi2").age(20).build();
        Member member3 = Member.builder().name("hi3").age(10).build();
        Mockito.when(memberRepository.save(member1)).thenReturn(member1);
        Mockito.when(memberRepository.save(member2)).thenReturn(Mockito.any(Member.class));
        Mockito.when(memberRepository.findByName("hi1")).thenReturn(Optional.of(member1));
        List<Member> list = List.of(member1,member2);
        Mockito.when(memberRepository.findAll()).thenReturn(list);
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
        /*
        given
         */
        Member member3 = Member.builder().name("hi3").age(10).build();
        ReflectionTestUtils.setField(member3,"id",3l);
        Mockito.when(memberRepository.save(member3)).thenReturn(member3);
        /*
        when
         */
        Long hi3 = memberService.createMember("hi3", 10);
        /*
        then
         */
        assertThat(hi3).isEqualTo(3L);
    }

    @Test
    @DisplayName("멤버 생성시 member1 과 이름이 같아서 예외 발생")
    void createMemberFail(){
        /*
        given
         */
        Member member1 = Member.builder().name("hi1").age(10).build();
        Mockito.when(memberRepository.findByName("hi1")).thenReturn(Optional.of(member1));

        /*
        when then
         */
        assertThatThrownBy(() -> memberService.createMember("hi1",10)).isInstanceOf(IllegalStateException.class);
    }
}
