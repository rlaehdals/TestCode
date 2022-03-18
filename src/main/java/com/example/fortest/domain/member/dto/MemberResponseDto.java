package com.example.fortest.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class MemberResponseDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ListDto {
        String name;
        int age;
    }
}
