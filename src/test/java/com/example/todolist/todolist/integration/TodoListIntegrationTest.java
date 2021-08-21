package com.example.todolist.todolist.integration;

import com.example.todolist.entity.Todo;
import com.example.todolist.repository.TodoListRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoListIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TodoListRepository todoListRepository;

    private List<Todo> todoItemsInfo;

    @AfterEach
    void tearDown() {
        todoListRepository.deleteAll();
    }
    @BeforeEach
    public void todoItemsInformation() {
        todoItemsInfo = Arrays.asList(
                (new Todo("My first todo item", false)),
                (new Todo("My second todo item", false)),
                (new Todo("My third todo item", false)),
                (new Todo("My fourth todo item", false))
        );
    }

    @Test
    void should_return_all_todo_items_when_getAllTodoItems_API() throws Exception {
        //given
        todoListRepository.save(todoItemsInfo.get(0));
        todoListRepository.save(todoItemsInfo.get(1));
        todoListRepository.save(todoItemsInfo.get(2));
        todoListRepository.save(todoItemsInfo.get(3));
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].id").isNumber())
                .andExpect(jsonPath("$[1].text").value("My second todo item"))
                .andExpect(jsonPath("$.*", Matchers.hasSize(4)));
    }
    @Test
    void should_create_a_new_todo_item_when_addTodoItem_API() throws Exception {
        //given
        String newTodoItem = "{\n" +
                "  \"text\": \"My fifth todo item\",\n" +
                "  \"done\": false\n" +
                "}";
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newTodoItem))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.text").value("My fifth todo item"))
                .andExpect(jsonPath("$.done").value(false));
    }

    @Test
    void should_toggle_the_status_of_the_todo_item_when_updateTodoItem_API() throws Exception {
        //given
        Todo todoItem = todoListRepository.save(todoItemsInfo.get(3));
        Integer itemId = todoItem.getId();
        String updatedTodoItem = "{\n" +
                "    \"text\": \"My updated fourth todo item\",\n" +
                "    \"done\": \"true\"\n" +
                "}";
        //then
        mockMvc.perform(MockMvcRequestBuilders.put("/todos/{itemId}", String.valueOf(itemId))
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedTodoItem))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.text").value("My updated fourth todo item"))
                .andExpect(jsonPath("$.done").value(true));
    }

    @Test
    void should_delete_selected_todo_item_when_deleteTodoItem_API() throws Exception {
        //given
        Todo firstTodoItem = todoListRepository.save(todoItemsInfo.get(0));
        Integer itemId = firstTodoItem.getId();

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/todos/{itemId}", String.valueOf(itemId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").doesNotExist());
    }
}
