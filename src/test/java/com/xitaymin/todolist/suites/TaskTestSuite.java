package com.xitaymin.todolist.suites;

import org.junit.jupiter.api.Test;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TaskTestSuite extends BaseTestSuite
{
    @Test
    void isTaskListEmptyBeforeCreationTest() throws Exception
    {
        mockMvc.perform(get("/todo").accept(APPLICATION_JSON)).andExpect(status().isOk())
            .andExpect(content().json("[]"));
    }
}
