package spring.project.todo.ToDoItem;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ToDoItemRepo extends JpaRepository<ToDoItem, Long> {
}
