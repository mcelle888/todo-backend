package spring.project.todo.toDoList;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import spring.project.todo.ToDoItem.CreateItemDTO;
import spring.project.todo.ToDoItem.ToDoItem;
import spring.project.todo.ToDoItem.ToDoItemRepo;

@Service
@Transactional
public class ToDoListService {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ToDoListRepo repo;

    @Autowired
    private ToDoItemRepo itemRepo;

    public ToDoList createPost(@Valid CreateListDTO data) {
        // ToDoList newList = new ToDoList();

        // newList.setTitle(data.getTitle().trim());
        ToDoList newList = mapper.map(data, ToDoList.class);

        newList.setDateCreated(new Date());
        return this.repo.save(newList);

    }

    public List<ToDoList> getAllLists() {
        return this.repo.findAll();
    }

    public Optional<ToDoList> getById(Long id) {
        return this.repo.findById(id);
    }

    public boolean deleteById(Long id) {
        Optional<ToDoList> maybeList = this.getById(id);
        if (maybeList.isEmpty()) {
            return false;
        }
        this.repo.delete(maybeList.get());
        return true;
    }

    public Optional<ToDoList> updateById(Long id, @Valid UpdateToDoListDTO data) {
        Optional<ToDoList> maybeList = this.getById(id);
        if (maybeList.isEmpty()) {
            return maybeList;
        }
        ToDoList foundList = maybeList.get();

        // String newTitle = data.getTitle();
        // if(data.getTitle() != null ){
        // foundList.setTitle(newTitle.trim());
        // }

        mapper.map(data, foundList);
        ToDoList updatedList = this.repo.save(foundList);
        return Optional.of(updatedList);

    }


}
