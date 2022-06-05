package com.example.fortest.restdocs;

import com.example.fortest.domain.member.controller.MemberController;
import com.example.fortest.domain.member.dto.MemberRequestDto;
import com.example.fortest.domain.member.dto.MemberResponseDto;
import com.example.fortest.domain.member.service.MemberServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.headers.HeaderDescriptor;
import org.springframework.restdocs.headers.HeaderDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.restdocs.snippet.Attributes;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.management.OperationsException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

@AutoConfigureRestDocs
@WebMvcTest(MemberController.class)
@Import(RestDocsConfiguration.class)
public class RestDocs {

    @Autowired
    MockMvc mvc;

    @MockBean
    MemberServiceImpl memberService;

    @Test
    void test() throws Exception {

        FieldDescriptor[] reviews = getReviewFieldDescriptors();

        List<MemberResponseDto.ListDto> list = new ArrayList<>();
        list.add(new MemberResponseDto.ListDto("fsd",10));
        list.add(new MemberResponseDto.ListDto("fsddf",10));
        Mockito.when(memberService.findAll()).thenReturn(list);
        /*ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/members")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("fsd"))
                .andDo(MockMvcRestDocumentation.document("/members",
                        responseFields(PayloadDocumentation.fieldWithPath("[]name").description("이름"),
                        PayloadDocumentation.fieldWithPath("[]age").description("나이"))));
*/
//        ResultActions resultActions2 = mvc.perform(MockMvcRequestBuilders.get("/members")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("fsd"))
//                .andDo(MockMvcRestDocumentation.document("/members",
//                        responseFields(reviews)));



        ResultActions resultActions1 = mvc.perform(MockMvcRequestBuilders.get("/members").header(HttpHeaders.AUTHORIZATION,"hihi")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("fsd"))
                .andDo(MockMvcRestDocumentation.document("/members",
                        PayloadDocumentation.responseFields(reviews),
                        HeaderDocumentation.requestHeaders(HeaderDocumentation.headerWithName(HttpHeaders.AUTHORIZATION).description("hasdf")),
                        HeaderDocumentation.responseHeaders(HeaderDocumentation.headerWithName("hihi").description("헤더"))));

    }

    @Test
    void request_field() throws Exception {
        Mockito.when(memberService.createMember("hi",10)).thenReturn(1l);

        ObjectMapper objectMapper = new ObjectMapper();

        MemberRequestDto.CreateDto member = new MemberRequestDto.CreateDto("hi",10);

        String string = objectMapper.writeValueAsString(member);

        mvc.perform(MockMvcRequestBuilders.post("/members").contentType(MediaType.APPLICATION_JSON).content(string))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcRestDocumentation.document("/createMember",
                        PayloadDocumentation.requestFields(PayloadDocumentation.fieldWithPath("name").type(JsonFieldType.STRING).description("이름").attributes(getAttribute()),
                                PayloadDocumentation.fieldWithPath("age").type(JsonFieldType.NUMBER).description("나이").optional())
                        ));
    }

    private FieldDescriptor[] getReviewFieldDescriptors() {
        return new FieldDescriptor[]{
                fieldWithPath("[].name").description("이름"),
                fieldWithPath("[].age").description("나이")
        };
    }

    private Attributes.Attribute getAttribute(){
        return Attributes.key("constraints").value("10자이하");
    }

}
