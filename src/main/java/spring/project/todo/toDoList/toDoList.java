package spring.project.todo.toDoList;

import java.util.Date;

import spring.project.todo.ToDoItem.ToDoItem;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@Entity
@Table(name = "to_do_list")
@JsonIgnoreProperties({"items"})
public class ToDoList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dateCreated;

    @OneToMany(mappedBy = "toDoList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ToDoItem> items = new ArrayList<>();

    public ToDoList() {
    }

    public ToDoList(String title, Date dateCreated) {
        this.title = title;
        this.dateCreated = dateCreated;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<ToDoItem> getItems() {
        return items;
    }

    public void setItems(List<ToDoItem> items) {
        this.items = items;
    }

    public void addItem(ToDoItem item) {
        // relationship with items
        item.setToDoList(this); 
        this.items.add(item);
    }

    @Override
    public String toString() {
        return "ToDoList [id=" + id + ", title=" + title + ", dateCreated=" + dateCreated + "]";
    }
}