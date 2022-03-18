package com.example.fortest.slicetest;

import com.example.fortest.domain.member.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;

import static org.assertj.core.api.Assertions.*;

public class MemberDomainTest {

    @Test
    @DisplayName("멤버가 생성되는지 확인하는 테스트")
    void createMember(){
        /*
        given
         */
        Member member = Member.builder().age(10).name("hi").build();

        /*
        when, then
         */
        assertThat(member.getAge()).isEqualTo(10);
        assertThat(member.getName()).isEqualTo("hi");
    }

    @Test
    @DisplayName("멤버의 나이 바뀌는지 확인하는 테스트")
    void changeAgeTest(){
        /*
        given
         */
        Member member = Member.builder().age(10).name("hi").build();

        /*
        when
         */
        member.changeAge(13);
        /*
        then
         */
        assertThat(member.getAge()).isEqualTo(13);
    }
}
