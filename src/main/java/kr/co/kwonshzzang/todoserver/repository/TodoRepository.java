package kr.co.kwonshzzang.todoserver.repository;

import kr.co.kwonshzzang.todoserver.model.TodoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<TodoModel, Long> {
}
