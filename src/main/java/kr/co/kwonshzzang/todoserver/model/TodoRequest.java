package kr.co.kwonshzzang.todoserver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoRequest {
    private String title;
    private Long order;
    private boolean completed;
}
