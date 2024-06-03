package spring.project.todo.toDoList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import spring.project.todo.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todo")
public class ToDoController {

    private static final Logger logger = LogManager.getLogger(ToDoController.class);

    @Autowired
    private ToDoListService toDoListService;

    // create
    @PostMapping()
    public ResponseEntity<ToDoListDTO> createPost(@Valid @RequestBody CreateListDTO data) {
        logger.info("Created a new list with title: {}", data.getTitle());
        ToDoListDTO createdList = toDoListService.createPost(data);
        return new ResponseEntity<>(createdList, HttpStatus.CREATED);
    }

    // get  
    @GetMapping()
    public ResponseEntity<List<ToDoListDTO>> getAllLists() {
        logger.info("Fetching Lists");
        List<ToDoListDTO> allLists = toDoListService.getAllLists();
        return new ResponseEntity<>(allLists, HttpStatus.OK);
    }

    // get one by id
    @GetMapping("/{id}")
    public ResponseEntity<ToDoListDTO> getListById(@PathVariable Long id) throws NotFoundException {
        logger.info("Fetching List with ID: {}", id);
        Optional<ToDoListDTO> maybeList = toDoListService.getById(id);
        ToDoListDTO foundList = maybeList.orElseThrow(() -> new NotFoundException(ToDoList.class, id));
        return new ResponseEntity<>(foundList, HttpStatus.OK);
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostById(@PathVariable Long id) throws NotFoundException {
        logger.info("Deleting List with ID: {}", id);
        boolean isDeleted = toDoListService.deleteById(id);
        if (!isDeleted) {
            throw new NotFoundException(ToDoList.class, id);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // update
    @PatchMapping("/{id}")
    public ResponseEntity<ToDoListDTO> updatePostById(@PathVariable Long id, @Valid @RequestBody UpdateToDoListDTO data)
            throws NotFoundException {
        logger.info("Updating List with ID: {}", id);
        Optional<ToDoListDTO> maybeList = toDoListService.updateById(id, data);
        ToDoListDTO updatedList = maybeList.orElseThrow(() -> new NotFoundException(ToDoList.class, id));
        return new ResponseEntity<>(updatedList, HttpStatus.OK);
    }
}
