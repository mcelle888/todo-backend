package spring.project.todo.ToDoItem;

import java.util.Date;

public class ToDoItemDTO {
    private Long id;
    private String name;
    private String description;
    private Date dueDate;

    public ToDoItemDTO() {
    }

    public ToDoItemDTO(Long id, String name, String description, Date dueDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return "ToDoItemDTO [id=" + id + ", name=" + name + ", description=" + description + ", dueDate=" + dueDate
                + "]";
    }
}