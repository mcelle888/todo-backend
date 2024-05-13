package spring.project.todo.toDoList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import spring.project.todo.ToDoItems.ToDoItems.ToDoItem;

@Entity
@Table(name="to_do_list")
public class toDoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @OneToMany(mappedBy = "toDoList", cascade = CascadeType.ALL, orphanRemoval = true )
    private List<ToDoItem> items;
}
