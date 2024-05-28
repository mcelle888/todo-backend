package spring.project.todo.ToDoItem;

 

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@JsonIgnoreProperties({ "toDoList" })
public class ToDoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Temporal(TemporalType.DATE)
    private Date dueDate;

    @Column(nullable = false)
    private boolean done = false;  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_do_list_id", nullable = false)
    private ToDoList toDoList;

    public ToDoItem() {
    }

    public ToDoItem(String name, String description, Date dueDate, boolean done, ToDoList toDoList) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.done = done;
        this.toDoList = toDoList;
    }

    public Long getId() {
        return id;
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

    public ToDoList getToDoList() {
        return toDoList;
    }

    public void setToDoList(ToDoList toDoList) {
        this.toDoList = toDoList;
    }

    @Override
    public String toString() {
        return "ToDoItem [id=" + id + ", name=" + name + ", description=" + description + ", dueDate=" + dueDate
                + ", done=" + done + "]";
    }
}