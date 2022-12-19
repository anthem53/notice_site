package com.test.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void return_hello() throws Exception {
        String hello = "hello";
        mvc.perform(get("/hello")).andExpect(status().isOk()).andExpect(content().string(hello));
    }

    @Test
    public void return_kill_you() throws Exception{
        String kill = "kill you";
        mvc.perform(get("/kill")).andExpect(status().isOk()).andExpect(content().string(kill));
    }

    @Test
    public void helloDto_return() throws Exception{
        String name = "Hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto").param("name",name).param("amount",String.valueOf(amount))
        ).andExpect(status().isOk());

    }


}
