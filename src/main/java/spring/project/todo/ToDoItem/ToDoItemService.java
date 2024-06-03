package spring.project.todo.ToDoItem;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper modelMapper;

    public ToDoItemDTO addItemToList(Long listId, CreateItemDTO data) throws NotFoundException {
        Optional<ToDoList> maybeList = listRepo.findById(listId);
        if (maybeList.isEmpty()) {
            throw new NotFoundException(ToDoList.class, listId);
        }
        ToDoList list = maybeList.get();

        ToDoItem newItem = modelMapper.map(data, ToDoItem.class);
        newItem.setToDoList(list);
        ToDoItem savedItem = itemRepo.save(newItem);
        return modelMapper.map(savedItem, ToDoItemDTO.class);
    }

    public List<ToDoItemDTO> getItemsByListId(Long listId) throws NotFoundException {
        Optional<ToDoList> maybeList = listRepo.findById(listId);
        if (maybeList.isEmpty()) {
            throw new NotFoundException(ToDoList.class, listId);
        }
        return maybeList.get().getItems().stream()
                .map(item -> modelMapper.map(item, ToDoItemDTO.class))
                .collect(Collectors.toList());
    }

    public ToDoItemDTO updateItem(Long listId, Long itemId, UpdateItemDTO data) throws NotFoundException {
        Optional<ToDoItem> maybeItem = itemRepo.findById(itemId);
        if (maybeItem.isEmpty()) {
            throw new NotFoundException(ToDoItem.class, itemId);
        }
        ToDoItem item = maybeItem.get();
        if (!item.getToDoList().getId().equals(listId)) {
            throw new NotFoundException(ToDoItem.class, itemId);
        }

        modelMapper.map(data, item);
        ToDoItem updatedItem = itemRepo.save(item);
        return modelMapper.map(updatedItem, ToDoItemDTO.class);
    }

    public void deleteItem(Long listId, Long itemId) throws NotFoundException {
        Optional<ToDoItem> maybeItem = itemRepo.findById(itemId);
        if (maybeItem.isEmpty()) {
            throw new NotFoundException(ToDoItem.class, itemId);
        }
        ToDoItem item = maybeItem.get();
        if (!item.getToDoList().getId().equals(listId)) {
            throw new NotFoundException(ToDoItem.class, itemId);
        }

        itemRepo.delete(item);
    }
}
