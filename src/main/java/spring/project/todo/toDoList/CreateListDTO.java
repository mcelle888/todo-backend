package spring.project.todo.toDoList;

import jakarta.validation.constraints.NotBlank;

public class CreateListDTO {
    @NotBlank
    private String title;

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "CreateListDTO [title=" + title + "]";
    }
}
