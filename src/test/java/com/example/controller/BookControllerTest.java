package com.example.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.HelloWorldApplication;

@ContextConfiguration(classes = HelloWorldApplication.class)
@WebMvcTest(BookController.class) // MVC를 위한 테스트
@AutoConfigureRestDocs // Rest Docs 코드를 추가하기 위한 설정
class BookControllerTest {
	
	@Autowired
	private MockMvc mockMvc; 
	
	@Test
	public void test() throws Exception {
		mockMvc.perform(RestDocumentationRequestBuilders.get("/book/{id}", 1)
			.accept(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(document("book", pathParameters(
					parameterWithName("id").description("book unique id") 
				),
				responseFields(
					fieldWithPath("id").description("book unique id"),
					fieldWithPath("title").description("title"),
					fieldWithPath("author").description("author"),
					fieldWithPath("dev").description("dev")
				)
			))
			.andExpect(jsonPath("$.id", is(notNullValue())))
			.andExpect(jsonPath("$.title", is(notNullValue())))
			.andExpect(jsonPath("$.author", is(notNullValue())))
			.andExpect(jsonPath("$.dev", is(notNullValue())));
	}

}
