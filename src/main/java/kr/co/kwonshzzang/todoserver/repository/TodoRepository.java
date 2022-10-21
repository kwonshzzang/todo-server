package kr.co.kwonshzzang.todoserver.repository;

import kr.co.kwonshzzang.todoserver.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
}
