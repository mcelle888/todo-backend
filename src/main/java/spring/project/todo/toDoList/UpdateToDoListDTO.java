package spring.project.todo.toDoList;

import jakarta.validation.constraints.Pattern;

public class UpdateToDoListDTO {
    
    private String title;

    @Pattern(regexp = ".*\\S.*", message= "title cannot be empty")
    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "UpdateToDoListDTO [title=" + title + "]";
    }
}
