package spring.project.todo.toDoList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/todo")
public class ToDoController {
    @PostMapping()
    public String postMethodName() {
         return "This end point creates a todo item";
    }
    

}
