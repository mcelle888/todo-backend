package spring.project.todo.ToDoItem;

import java.util.Date;
 

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
 

public class CreateItemDTO {
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @FutureOrPresent(message = "Due date must be in the present or future")
    private Date dueDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "CreateItemDTO [name=" + name + ", description=" + description + ", dueDate=" + dueDate + "]";
    }
}
