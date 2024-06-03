package spring.project.todo.ToDoItem;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import spring.project.todo.exceptions.NotFoundException;
import spring.project.todo.toDoList.ToDoList;
import spring.project.todo.toDoList.ToDoListRepo;

@Service
@Transactional
public class ToDoItemService {

    private static final Logger logger = LogManager.getLogger(ToDoItemService.class);

    @Autowired
    private ToDoItemRepo itemRepo;

    @Autowired
    private ToDoListRepo listRepo;

    @Autowired
    private ModelMapper modelMapper;

    public ToDoItemDTO addItemToList(Long listId, CreateItemDTO data) throws NotFoundException {
        logger.info("Adding items '{}' to list: {}", data.getName(), listId);
        Optional<ToDoList> maybeList = listRepo.findById(listId);
        if (maybeList.isEmpty()) {
            logger.error("ToDoList {} not found", listId);
            throw new NotFoundException(ToDoList.class, listId);
        }
        ToDoList list = maybeList.get();

        ToDoItem newItem = modelMapper.map(data, ToDoItem.class);
        newItem.setToDoList(list);
        ToDoItem savedItem = itemRepo.save(newItem);
        logger.info("Item '{}' added to list: {}", data.getName(), listId);
        return modelMapper.map(savedItem, ToDoItemDTO.class);
    }

    public List<ToDoItemDTO> getItemsByListId(Long listId) throws NotFoundException {
        logger.info("Fetching all items from list: {}", listId);
        Optional<ToDoList> maybeList = listRepo.findById(listId);
        if (maybeList.isEmpty()) {
            logger.error("ToDoList {} not found", listId);
            throw new NotFoundException(ToDoList.class, listId);
        }
        return maybeList.get().getItems().stream()
                .map(item -> modelMapper.map(item, ToDoItemDTO.class))
                .collect(Collectors.toList());
    }

    public ToDoItemDTO updateItem(Long listId, Long itemId, UpdateItemDTO data) throws NotFoundException {
        logger.info("Updating itemID: {} in list: {} with new name: '{}'", itemId, listId,
                data.getName());
        Optional<ToDoItem> maybeItem = itemRepo.findById(itemId);
        if (maybeItem.isEmpty()) {
            logger.error("ToDoItem {} not found", itemId);
            throw new NotFoundException(ToDoItem.class, itemId);
        }
        ToDoItem item = maybeItem.get();
        if (!item.getToDoList().getId().equals(listId)) {
            logger.error("ToDoItem {} cannot be found in list {}, failed to update", itemId, listId);
            throw new NotFoundException(ToDoItem.class, itemId);
        }

        modelMapper.map(data, item);
        ToDoItem updatedItem = itemRepo.save(item);
        logger.info("Item {} updated in list {}", itemId, listId);
        return modelMapper.map(updatedItem, ToDoItemDTO.class);
    }

    public void deleteItem(Long listId, Long itemId) throws NotFoundException {
        logger.info("Deleting item: {} from list: {}", itemId, listId);
        Optional<ToDoItem> maybeItem = itemRepo.findById(itemId);
        if (maybeItem.isEmpty()) {
            logger.error("ToDoItem {} not found", itemId);
            throw new NotFoundException(ToDoItem.class, itemId);
        }
        ToDoItem item = maybeItem.get();
        if (!item.getToDoList().getId().equals(listId)) {
            logger.error("ToDoItem {} cannot be found in list {}, failed to delete item", itemId, listId);
            throw new NotFoundException(ToDoItem.class, itemId);
        }

        itemRepo.delete(item);
        logger.info("Item {} deleted from list {}", itemId, listId);
    }
}
