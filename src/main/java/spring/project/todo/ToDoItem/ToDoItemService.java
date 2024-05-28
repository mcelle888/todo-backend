package spring.project.todo.ToDoItem;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
 
import spring.project.todo.exceptions.NotFoundException;
import spring.project.todo.toDoList.ToDoList;
import spring.project.todo.toDoList.ToDoListRepo;

@Service
@Transactional
public class ToDoItemService {

    @Autowired
    private ToDoItemRepo itemRepo;

    @Autowired
    private ToDoListRepo listRepo;

    public ToDoItemDTO addItemToList(Long listId, CreateItemDTO data) throws NotFoundException {
        Optional<ToDoList> maybeList = listRepo.findById(listId);
        if (maybeList.isEmpty()) {
            throw new NotFoundException(ToDoList.class, listId);
        }
        ToDoList list = maybeList.get();

        ToDoItem newItem = new ToDoItem(data.getName(), data.getDescription(), data.getDueDate(), list);
        ToDoItem savedItem = itemRepo.save(newItem);
        return convertToDTO(savedItem);
    }

    public List<ToDoItemDTO> getItemsByListId(Long listId) throws NotFoundException {
        Optional<ToDoList> maybeList = listRepo.findById(listId);
        if (maybeList.isEmpty()) {
            throw new NotFoundException(ToDoList.class, listId);
        }
        return maybeList.get().getItems().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ToDoItemDTO updateItem(Long listId, Long itemId, UpdateItemDTO data) throws NotFoundException {
        Optional<ToDoList> maybeList = listRepo.findById(listId);
        if (maybeList.isEmpty()) {
            throw new NotFoundException(ToDoList.class, listId);
        }

        Optional<ToDoItem> maybeItem = itemRepo.findById(itemId);
        if (maybeItem.isEmpty()) {
            throw new NotFoundException(ToDoItem.class, itemId);
        }

        ToDoItem item = maybeItem.get();
        item.setName(data.getName());
        item.setDescription(data.getDescription());
        item.setDueDate(data.getDueDate());

        ToDoItem updatedItem = itemRepo.save(item);
        return convertToDTO(updatedItem);
    }

    public void deleteItem(Long listId, Long itemId) throws NotFoundException {
        Optional<ToDoList> maybeList = listRepo.findById(listId);
        if (maybeList.isEmpty()) {
            throw new NotFoundException(ToDoList.class, listId);
        }

        Optional<ToDoItem> maybeItem = itemRepo.findById(itemId);
        if (maybeItem.isEmpty()) {
            throw new NotFoundException(ToDoItem.class, itemId);
        }

        itemRepo.deleteById(itemId);
    }

    private ToDoItemDTO convertToDTO(ToDoItem item) {
        return new ToDoItemDTO(item.getId(), item.getName(), item.getDescription(), item.getDueDate());
    }
}