package spring.project.todo.toDoList;

import java.util.Date;
import java.util.List;
import spring.project.todo.ToDoItem.ToDoItemDTO;

public class ToDoListDTO {
    private Long id;
    private String title;
    private Date dateCreated;
    private List<ToDoItemDTO> items;

    public ToDoListDTO() {
    }

    public ToDoListDTO(Long id, String title, Date dateCreated, List<ToDoItemDTO> items) {
        this.id = id;
        this.title = title;
        this.dateCreated = dateCreated;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public List<ToDoItemDTO> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "ToDoListDTO [id=" + id + ", title=" + title + ", dateCreated=" + dateCreated + ", items=" + items + "]";
    }
}