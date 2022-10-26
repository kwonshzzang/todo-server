package kr.co.kwonshzzang.todoserver.service;

import kr.co.kwonshzzang.todoserver.model.TodoModel;
import kr.co.kwonshzzang.todoserver.model.TodoRequest;
import kr.co.kwonshzzang.todoserver.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public TodoModel add(TodoRequest request) {
        TodoModel todoEntity = TodoModel.builder()
                .title(request.getTitle())
                .order(request.getOrder())
                .complete(request.isCompleted())
                .build();
        return todoRepository.save(todoEntity);
    }

    public TodoModel searchById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<TodoModel> serchAll() {
        return todoRepository.findAll();
    }

    public TodoModel updateById(Long id, TodoRequest request) {
        TodoModel todoEntity = searchById(id);
        if(request.getTitle() != null) {
            todoEntity.setTitle(request.getTitle());
        }

        if(request.getOrder() != null) {
            todoEntity.setOrder(request.getOrder());
        }

        todoEntity.setComplete(request.isCompleted());

        return todoRepository.save(todoEntity);
    }

    public void deleteById(Long id) {
        todoRepository.deleteById(id);
    }

    public void deleteAll() {
        todoRepository.deleteAll();
    }
}
