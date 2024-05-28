package spring.project.todo.ToDoItem;

import java.util.List;
import java.util.Optional;

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

    public ToDoItem addItemToList(Long listId, CreateItemDTO data) throws NotFoundException {
        Optional<ToDoList> maybeList = listRepo.findById(listId);
        if (maybeList.isEmpty()) {
            throw new NotFoundException(ToDoList.class, listId);
        }
        ToDoList list = maybeList.get();

        ToDoItem newItem = new ToDoItem(data.getName(), data.getDescription(), data.getDueDate(), list);
        return itemRepo.save(newItem);
    }

    public List<ToDoItem> getItemsByListId(Long listId) throws NotFoundException {
        Optional<ToDoList> maybeList = listRepo.findById(listId);
        if (maybeList.isEmpty()) {
            throw new NotFoundException(ToDoList.class, listId);
        }
        return maybeList.get().getItems();
    }

    public ToDoItem updateItem(Long listId, Long itemId, UpdateItemDTO data) throws NotFoundException {
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

        return itemRepo.save(item);
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
}