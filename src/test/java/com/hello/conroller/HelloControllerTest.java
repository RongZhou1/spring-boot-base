package com.hello.conroller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@WebMvcTest(value = HelloController.class)
public class HelloControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldGetHello() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/hello");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertThat(result.getResponse().getContentAsString(), is("hello"));
    }
}