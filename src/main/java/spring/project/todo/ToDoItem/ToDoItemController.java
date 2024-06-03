package spring.project.todo.ToDoItem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import spring.project.todo.exceptions.NotFoundException;

import java.util.List;

@RestController
@RequestMapping("/todo/{listId}/items")
@Validated
public class ToDoItemController {

    private static final Logger logger = LogManager.getLogger(ToDoItemController.class);

    @Autowired
    private ToDoItemService toDoItemService;

    // create
    @PostMapping()
    public ResponseEntity<ToDoItemDTO> addItemToList(@PathVariable Long listId, @Valid @RequestBody CreateItemDTO data)
            throws NotFoundException {
        logger.info("Adding item to list: {}", listId);
        ToDoItemDTO createdItem = toDoItemService.addItemToList(listId, data);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    // get all items from list
    @GetMapping()
    public ResponseEntity<List<ToDoItemDTO>> getAllItems(@PathVariable Long listId) throws NotFoundException {
        logger.info("Fetching all items from list: {}", listId);
        List<ToDoItemDTO> items = toDoItemService.getItemsByListId(listId);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    // update item from list
    @PutMapping("/{itemId}")
    public ResponseEntity<ToDoItemDTO> updateItem(@PathVariable Long listId, @PathVariable Long itemId,
            @Valid @RequestBody UpdateItemDTO data) throws NotFoundException {
        logger.info("Updating item: {} in list: {}", itemId, listId);
        ToDoItemDTO updatedItem = toDoItemService.updateItem(listId, itemId, data);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }

    // delete item from list
    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long listId, @PathVariable Long itemId)
            throws NotFoundException {
        logger.info("Deleting item: {} from list: {}", itemId, listId);
        toDoItemService.deleteItem(listId, itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
