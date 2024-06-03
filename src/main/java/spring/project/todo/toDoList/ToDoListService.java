package spring.project.todo.toDoList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import spring.project.todo.ToDoItem.ToDoItemDTO;

@Service
@Transactional
public class ToDoListService {

    private static final Logger logger = LogManager.getLogger(ToDoListService.class);

    private final ToDoListRepo listRepo;
    private final ModelMapper modelMapper;

    public ToDoListService(ToDoListRepo listRepo, ModelMapper modelMapper) {
        this.listRepo = listRepo;
        this.modelMapper = modelMapper;
    }

    public ToDoListDTO createPost(CreateListDTO data) {
        logger.info("Creating a new list with title: '{}'", data.getTitle());
        ToDoList newList = new ToDoList(data.getTitle(), new Date());
        ToDoList savedList = listRepo.save(newList);
        logger.info("To-do list '{}' created: {}", data.getTitle(), savedList.getId());
        return convertToDTO(savedList);
    }

    public List<ToDoListDTO> getAllLists() {
        logger.info("Fetching all lists");
        return listRepo.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ToDoListDTO> getById(Long id) {
        logger.info("Fetching list: {}", id);
        return listRepo.findById(id).map(this::convertToDTO);
    }

    public boolean deleteById(Long id) {
        if (listRepo.existsById(id)) {
            logger.info("Deleting list: {}", id);
            listRepo.deleteById(id);
            logger.info("List: {} deleted", id);
            return true;
        }
        logger.warn("List: {} not found", id);
        return false;
    }

    public Optional<ToDoListDTO> updateById(Long id, UpdateToDoListDTO data) {
        logger.info("Updating List: {} with new title: '{}'", id, data.getTitle());
        return listRepo.findById(id).map(list -> {
            list.setTitle(data.getTitle());
            ToDoList updatedList = listRepo.save(list);
            logger.info("List: {} updated with new title: '{}'", id, data.getTitle());
            return convertToDTO(updatedList);
        });
    }

    private ToDoListDTO convertToDTO(ToDoList list) {
        List<ToDoItemDTO> itemDTOs = list.getItems().stream()
                .map(item -> modelMapper.map(item, ToDoItemDTO.class))
                .collect(Collectors.toList());
        return new ToDoListDTO(list.getId(), list.getTitle(), list.getDateCreated(), itemDTOs);
    }
}
