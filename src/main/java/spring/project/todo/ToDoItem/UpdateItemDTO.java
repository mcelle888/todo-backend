package spring.project.todo.ToDoItem;

import java.util.Date;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;

public class UpdateItemDTO {
    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    @FutureOrPresent(message = "Due date must be a future date")
    private Date dueDate;

    private boolean done; 

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

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "UpdateItemDTO [name=" + name + ", description=" + description + ", dueDate=" + dueDate + ", done="
                + done + "]";
    }
}