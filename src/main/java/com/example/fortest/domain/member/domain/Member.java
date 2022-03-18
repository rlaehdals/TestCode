package com.example.fortest.domain.member.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;



    @Builder
    public Member(String name, int age){
        this.name=name;
        this.age=age;
    }

    @Override
    public boolean equals(Object obj) {
        Member me = (Member) obj;
        return this.name.equals(me.name) && this.age==me.age;
    }

    public void changeAge(int age){
        this.age=age;
    }
}
