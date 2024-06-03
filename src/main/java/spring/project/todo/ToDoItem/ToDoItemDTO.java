package spring.project.todo.ToDoItem;

import java.util.Date;

public class ToDoItemDTO {
    private Long id;
    private String name;
    private String description;
    private Date dueDate;
    private boolean done;
    private Long toDoListId;

    public ToDoItemDTO() {
    }

    public ToDoItemDTO(Long id, String name, String description, Date dueDate, boolean done, Long toDoListId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.done = done;
        this.toDoListId = toDoListId;
    }

    public ToDoItemDTO(ToDoItem item) {
        this.id = item.getId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.dueDate = item.getDueDate();
        this.done = item.isDone();
        this.toDoListId = item.getToDoList().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getToDoListId() {
        return toDoListId;
    }

    public void setToDoListId(Long toDoListId) {
        this.toDoListId = toDoListId;
    }

    @Override
    public String toString() {
        return "ToDoItemDTO [id=" + id + ", name=" + name + ", description=" + description + ", dueDate=" + dueDate
                + ", done=" + done + ", toDoListId=" + toDoListId + "]";
    }
}
