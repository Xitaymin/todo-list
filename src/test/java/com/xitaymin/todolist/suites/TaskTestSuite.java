package com.xitaymin.todolist.suites;

import com.xitaymin.todolist.entity.Task;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TaskTestSuite extends BaseTestSuite {
    @Test
    public void isTaskListEmptyBeforeCreationTest() throws Exception {
        mockMvc.perform(get("/todo").accept(APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void createTaskTest() throws Exception {
        isTaskListEmptyBeforeCreationTest();

        Task request = new Task();
        request.setId(null);
        request.setText("First task");

        MvcResult result = mockMvc
                .perform(post("/todo").content(asJson(request)).contentType(APPLICATION_JSON).accept(APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        Task response = fromResponse(result, Task.class);

        assertNotNull(response.getId());
        assertEquals(response, request);

        MvcResult resultAfterSave = mockMvc.perform(get("/todo").accept(APPLICATION_JSON)).andExpect(status().isOk())
                .andReturn();
        Task[] allTasksAfterSave = fromResponse(resultAfterSave, Task[].class);
        Task taskAfterSave = allTasksAfterSave[0];

        assertEquals(allTasksAfterSave.length, 1);
        assertEquals(taskAfterSave, response);
        assertEquals(taskAfterSave.getId(), response.getId());
    }

    @Test
    public void updateTaskTest() throws Exception {
        isTaskListEmptyBeforeCreationTest();

        Task savedTask = taskDao.upsert(new Task(null, "First task"));
        Task request = new Task(savedTask.getId(), "First task updated");

        MvcResult result = mockMvc
                .perform(post("/todo").content(asJson(request)).contentType(APPLICATION_JSON).accept(APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        Task response = fromResponse(result, Task.class);

        assertEquals(request.getId(), response.getId());
        assertEquals(response, request);

        MvcResult resultAfterSave = mockMvc.perform(get("/todo").accept(APPLICATION_JSON)).andExpect(status().isOk())
                .andReturn();
        Task[] allTasksAfterSave = fromResponse(resultAfterSave, Task[].class);

        assertEquals(allTasksAfterSave.length, 1);

        Task taskAfterSave = allTasksAfterSave[0];

        assertEquals(taskAfterSave, response);
        assertEquals(taskAfterSave.getId(), response.getId());
    }

//    @Test
//    public void isTaskCreatedOrUpdatedIfExists() throws Exception
//    {
//        isTaskListEmptyBeforeCreationTest();
//
//        Task createRequest = new Task();
//        createRequest.setId(null);
//        createRequest.setText("First task");
//
//        Task createdTask = upsertTask(createRequest);
//
//        Task updateRequest = new Task();
//        updateRequest.setId(createdTask.getId());
//        updateRequest.setText("First task updated");
//
//        upsertTask(createdTask);
//    }

//    @Test
//    public void deleteTaskById(){
//
//    }

//    private Task upsertTask(Task request) throws Exception
//    {
//        MvcResult result = mockMvc.perform(post("/todo").content(asJson(request))
//            .contentType(APPLICATION_JSON).accept(APPLICATION_JSON))
//            .andExpect(status().isOk())
//            .andReturn();
//
//        Task response = fromResponse(result, Task.class);
//
//        assertNotNull(response.getId());
//        assertEquals(response, request);
//
//        MvcResult resultAfterSave =
//            mockMvc.perform(get("/todo").accept(APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
//        Task[] allTasksAfterSave = fromResponse(resultAfterSave,Task[].class);
//        Task taskAfterSave = allTasksAfterSave[0];
//
//        assertEquals(allTasksAfterSave.length, 1);
//        assertEquals(taskAfterSave,response);
//        assertEquals(taskAfterSave.getId(), response.getId());
//
//        return taskAfterSave;
//    }


}
