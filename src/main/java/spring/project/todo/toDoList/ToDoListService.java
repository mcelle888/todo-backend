package spring.project.todo.toDoList;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class ToDoListService {
    @Autowired
    private ToDoListRepo repo;

    public ToDoList createPost(@Valid CreateListDTO data) {
        ToDoList newList = new ToDoList();
        newList.setTitle(data.getTitle().trim());
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
        if(maybeList.isEmpty()) {
            return maybeList;
        }
        ToDoList foundList = maybeList.get();
        String newTitle = data.getTitle();
        if(data.getTitle() != null ){
            foundList.setTitle(newTitle.trim());
        }
        ToDoList updatedList = this.repo.save(foundList);
        return Optional.of(updatedList);
        
    }

}
