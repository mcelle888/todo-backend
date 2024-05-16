package spring.project.todo.toDoList;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoListRepo extends JpaRepository<ToDoList, Long> {

}
