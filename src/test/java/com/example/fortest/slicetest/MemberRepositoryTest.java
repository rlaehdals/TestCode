package com.example.fortest.slicetest;

import com.example.fortest.domain.member.domain.Member;
import com.example.fortest.domain.member.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("멤버 만들기")
    void createMember(){
        /*
        given
         */
        Member member1 = Member.builder().name("hi1").age(10).build();
        Member member2 = Member.builder().name("hi2").age(20).build();

        /*
        when
         */
        Member result1 = memberRepository.save(member1);
        Member result2 = memberRepository.save(member2);

        /*
        then
         */
        assertThat(result1.getAge()).isEqualTo(member1.getAge());
        assertThat(result2.getAge()).isEqualTo(member2.getAge());

    }

    @Test
    @DisplayName("멤버의 리스트를 반환 하는지 확인")
    void MemberList(){
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
        List<Member> result = memberRepository.findAll();

        /*
        then
         */
        assertThat(result.size()).isEqualTo(2);
    }

}
