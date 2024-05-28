package spring.project.todo.toDoList;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import spring.project.todo.ToDoItem.ToDoItemDTO;

@Service
@Transactional
public class ToDoListService {

    @Autowired
    private ToDoListRepo listRepo;

    public ToDoListDTO createPost(CreateListDTO data) {
        ToDoList newList = new ToDoList(data.getTitle(), new Date());
        ToDoList savedList = listRepo.save(newList);
        return convertToDTO(savedList);
    }

    public List<ToDoListDTO> getAllLists() {
        return listRepo.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ToDoListDTO> getById(Long id) {
        return listRepo.findById(id).map(this::convertToDTO);
    }

    public boolean deleteById(Long id) {
        if (listRepo.existsById(id)) {
            listRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<ToDoListDTO> updateById(Long id, UpdateToDoListDTO data) {
        return listRepo.findById(id).map(list -> {
            list.setTitle(data.getTitle());
            return convertToDTO(listRepo.save(list));
        });
    }

    private ToDoListDTO convertToDTO(ToDoList list) {
        List<ToDoItemDTO> itemDTOs = list.getItems().stream()
                .map(item -> new ToDoItemDTO(item.getId(), item.getName(), item.getDescription(), item.getDueDate(), false))
                .collect(Collectors.toList());
        return new ToDoListDTO(list.getId(), list.getTitle(), list.getDateCreated(), itemDTOs);
    }
}