package spring.project.todo.toDoList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import spring.project.todo.ToDoItem.CreateItemDTO;
import spring.project.todo.ToDoItem.ToDoItem;
import spring.project.todo.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/todo")
public class ToDoController {
    @Autowired
    private ToDoListService toDoListService;

    // Post a new list
    @PostMapping()
    public ResponseEntity<ToDoList> createPost(@Valid @RequestBody CreateListDTO data) {
        ToDoList createdList = this.toDoListService.createPost(data);
        return new ResponseEntity<>(createdList, HttpStatus.CREATED);
    }

    // Get all lists
    @GetMapping()
    public ResponseEntity<List<ToDoList>> getAllLists() {
        List<ToDoList> allLists = this.toDoListService.getAllLists();
        return new ResponseEntity<>(allLists, HttpStatus.OK);
    }

    // Get list by id
    @GetMapping("/{id}")
    public ResponseEntity<ToDoList> getListById(@PathVariable Long id) throws NotFoundException {
        Optional<ToDoList> maybeList = this.toDoListService.getById(id);
        ToDoList foundList = maybeList.orElseThrow(() -> new NotFoundException(ToDoList.class, id));
        return new ResponseEntity<>(foundList, HttpStatus.NOT_FOUND);
    }

    // Delete a list
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostById(@PathVariable Long id) throws NotFoundException {
        boolean isDeleted = this.toDoListService.deleteById(id);
        if (!isDeleted) {
            throw new NotFoundException(ToDoList.class, id);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Update a list

    @PatchMapping("/{id}")
    public ResponseEntity<ToDoList> updatePostById(@PathVariable Long id, @Valid @RequestBody UpdateToDoListDTO data)
            throws NotFoundException {
        Optional<ToDoList> maybeList = this.toDoListService.updateById(id, data);
        ToDoList updatedList = maybeList.orElseThrow(() -> new NotFoundException(ToDoList.class, id));
        return new ResponseEntity<>(updatedList, HttpStatus.OK);
    }


    // Add item to a list
    // @PostMapping("/{id}/add")
    // public ResponseEntity<ToDoItem> addItemToList(@PathVariable Long id, @Valid @RequestBody CreateItemDTO data)
    //         throws NotFoundException {
    //     Optional<ToDoList> maybeList = this.toDoListService.getById(id);
    //     ToDoList list = maybeList.orElseThrow(() -> new NotFoundException(ToDoList.class, id));

    //     ToDoItem createdItem = this.toDoListService.addItemToList(list, data);
    //     return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    // }
}
