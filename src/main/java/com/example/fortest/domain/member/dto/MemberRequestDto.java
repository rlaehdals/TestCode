package com.example.fortest.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class MemberRequestDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateDto {
        String name;
        int age;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChangeAgeDto{
        String name;
        int age;
    }
}
