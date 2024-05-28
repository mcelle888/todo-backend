package spring.project.todo.toDoList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
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

    // create
    @PostMapping()
    public ResponseEntity<ToDoListDTO> createPost(@Valid @RequestBody CreateListDTO data) {
        ToDoListDTO createdList = this.toDoListService.createPost(data);
        return new ResponseEntity<>(createdList, HttpStatus.CREATED);
    }

    // get
    @GetMapping()
    public ResponseEntity<List<ToDoListDTO>> getAllLists() {
        List<ToDoListDTO> allLists = this.toDoListService.getAllLists();
        return new ResponseEntity<>(allLists, HttpStatus.OK);
    }

    // get one by id
    @GetMapping("/{id}")
    public ResponseEntity<ToDoListDTO> getListById(@PathVariable Long id) throws NotFoundException {
        Optional<ToDoListDTO> maybeList = this.toDoListService.getById(id);
        ToDoListDTO foundList = maybeList.orElseThrow(() -> new NotFoundException(ToDoList.class, id));
        return new ResponseEntity<>(foundList, HttpStatus.OK);
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostById(@PathVariable Long id) throws NotFoundException {
        boolean isDeleted = this.toDoListService.deleteById(id);
        if (!isDeleted) {
            throw new NotFoundException(ToDoList.class, id);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // update
    @PatchMapping("/{id}")
    public ResponseEntity<ToDoListDTO> updatePostById(@PathVariable Long id, @Valid @RequestBody UpdateToDoListDTO data)
            throws NotFoundException {
        Optional<ToDoListDTO> maybeList = this.toDoListService.updateById(id, data);
        ToDoListDTO updatedList = maybeList.orElseThrow(() -> new NotFoundException(ToDoList.class, id));
        return new ResponseEntity<>(updatedList, HttpStatus.OK);
    }
}