package spring.project.todo.toDoList;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import spring.project.todo.ToDoItem.ToDoItems.ToDoItem;

@Entity
@Table(name = "to_do_list")
public class ToDoList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @OneToMany(mappedBy = "toDoList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ToDoItem> items;

    ToDoList() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setItems(List<ToDoItem> items) {
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

    public List<ToDoItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "ToDoList [id=" + id + ", title=" + title + ", dateCreated=" + dateCreated + ", items=" + items + "]";
    }
}
