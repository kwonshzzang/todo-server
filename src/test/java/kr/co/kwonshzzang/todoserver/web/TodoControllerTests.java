package kr.co.kwonshzzang.todoserver.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.kwonshzzang.todoserver.model.TodoModel;
import kr.co.kwonshzzang.todoserver.model.TodoRequest;
import kr.co.kwonshzzang.todoserver.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TodoController.class)
class TodoControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TodoService todoService;

    private TodoModel expected;

    @BeforeEach
    void setup() {
        expected = TodoModel.builder()
                .id(123L)
                .title("TEST TITLE")
                .order(0L)
                .complete(false)
                .build();
    }


    @Test
    void create() throws Exception {
        when(todoService.add(any(TodoRequest.class)))
                .then((i) -> {
                    TodoRequest request = i.getArgument(0, TodoRequest.class);
                    return new TodoModel(expected.getId(),
                            request.getTitle(),
                            expected.getOrder(),
                            expected.isComplete());
                });

        TodoRequest request = TodoRequest.builder().title("ANY TITLE").build();

        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(request);

        mvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.title").value("ANY TITLE"));
    }

    @Test
    void readOne() {
    }
}