package com.xitaymin.todolist.suites;

import com.xitaymin.todolist.entity.Task;
import com.xitaymin.todolist.web.dto.ResponseError;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import static com.xitaymin.todolist.service.TaskServiceImpl.TASK_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TaskTestSuite extends BaseTestSuite {
    @Test
    public void isTaskListEmptyBeforeCreationTest() throws Exception {
        mockMvc.perform(get("/todo").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void notFoundTest() throws Exception {
        isTaskListEmptyBeforeCreationTest();
        String absentId = "1";
        MvcResult mvcResult =
                mockMvc.perform(delete("/todo").param("id", absentId)).andExpect(status().isNotFound()).andReturn();

        ResponseError responseError = fromResponse(mvcResult, ResponseError.class);
        assertThat(responseError.getMessage()).isEqualTo(String.format(TASK_NOT_FOUND, absentId));

    }

    @Test
    public void createTaskTest() throws Exception {
        isTaskListEmptyBeforeCreationTest();

        Task request = new Task();
        request.setId(null);
        request.setText("First task");

        MvcResult result = mockMvc.perform(post("/todo").content(asJson(request))
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        Task response = fromResponse(result, Task.class);

        assertNotNull(response.getId());
        assertEquals(response, request);

        MvcResult resultAfterSave =
                mockMvc.perform(get("/todo").accept(APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
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

        MvcResult result = mockMvc.perform(post("/todo").content(asJson(request))
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        Task response = fromResponse(result, Task.class);

        assertEquals(request.getId(), response.getId());
        assertEquals(response, request);

        MvcResult resultAfterSave =
                mockMvc.perform(get("/todo").accept(APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        Task[] allTasksAfterSave = fromResponse(resultAfterSave, Task[].class);

        assertEquals(allTasksAfterSave.length, 1);

        Task taskAfterSave = allTasksAfterSave[0];

        assertEquals(taskAfterSave, response);
        assertEquals(taskAfterSave.getId(), response.getId());
    }

    @Test
    public void deleteTaskById() throws Exception {
        isTaskListEmptyBeforeCreationTest();

        Task firstTask = taskDao.upsert(new Task(null, "First task"));
        Task secondTask = taskDao.upsert(new Task(null, "Second task"));

        mockMvc.perform(delete("/todo").param("id", String.valueOf(firstTask.getId()))).andExpect(status().isOk());

        MvcResult resultAfterSave =
                mockMvc.perform(get("/todo").accept(APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        Task[] allTasksAfterSave = fromResponse(resultAfterSave, Task[].class);

        assertEquals(allTasksAfterSave.length, 1);

        Task taskAfterSave = allTasksAfterSave[0];
        assertEquals(taskAfterSave, secondTask);
        assertEquals(taskAfterSave.getId(), secondTask.getId());
    }

    @Test
    public void deleteAllTasks() throws Exception {
        isTaskListEmptyBeforeCreationTest();

        taskDao.upsert(new Task(null, "First task"));
        taskDao.upsert(new Task(null, "Second task"));

        mockMvc.perform(delete("/todo")).andExpect(status().isOk());

        mockMvc.perform(get("/todo").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

}
