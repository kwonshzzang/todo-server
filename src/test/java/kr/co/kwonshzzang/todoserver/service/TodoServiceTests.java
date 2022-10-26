package kr.co.kwonshzzang.todoserver.service;

import kr.co.kwonshzzang.todoserver.model.TodoModel;
import kr.co.kwonshzzang.todoserver.model.TodoRequest;
import kr.co.kwonshzzang.todoserver.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TodoServiceTests {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    @Test
    void add() {
        when(todoRepository.save(any(TodoModel.class)))
                .then(AdditionalAnswers.returnsFirstArg());

        TodoRequest expected = TodoRequest.builder()
                .title("Test")
                .build();

        TodoModel actual = todoService.add(expected);

        assertEquals(expected.getTitle(), actual.getTitle());
    }

    @Test
    void searchById() {
        Optional<TodoModel> optional = Optional.of(TodoModel.builder()
                .id(123L)
                .title("TITLE")
                .order(0L)
                .complete(false)
                .build());

        given(todoRepository.findById(anyLong()))
                .willReturn(optional);

        TodoModel actual = todoService.searchById(123L);
        TodoModel expected = optional.get();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getOrder(), actual.getOrder());
        assertEquals(expected.isComplete(), actual.isComplete());
    }

    @Test
    void searchByIdFailed() {
        given(todoRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> todoService.searchById(123L));
    }
}