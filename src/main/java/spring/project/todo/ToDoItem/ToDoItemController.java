package spring.project.todo.ToDoItem;

import java.util.List;
  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import spring.project.todo.exceptions.NotFoundException;
 

@RestController
@RequestMapping("/todo/{listId}/items")
public class ToDoItemController {

    @Autowired
    private ToDoItemService toDoItemService;

    @PostMapping()
    public ResponseEntity<ToDoItem> addItemToList(@PathVariable Long listId, @Valid @RequestBody CreateItemDTO data)
            throws NotFoundException {
        ToDoItem createdItem = this.toDoItemService.addItemToList(listId, data);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<ToDoItem>> getAllItems(@PathVariable Long listId) throws NotFoundException {
        List<ToDoItem> items = this.toDoItemService.getItemsByListId(listId);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}