package spring.project.todo.ToDoItem;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import spring.project.todo.toDoList.ToDoList;

@Entity
@Table(name = "to_do_items")
public class ToDoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Temporal(TemporalType.DATE)
    private Date dueDate;

    @ManyToOne
    @JoinColumn(name = "to_do_list_id")
    private ToDoList toDoList;

    public ToDoItem() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setToDoList(ToDoList toDoList) {
        this.toDoList = toDoList;
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

    public ToDoList getToDoList() {
        return toDoList;
    }

    @Override
    public String toString() {
        return "ToDoItem [id=" + id + ", name=" + name + ", description=" + description + ", dueDate=" + dueDate + "]";
    }
}