package spring.project.todo.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.project.todo.ToDoItem.CreateItemDTO;
import spring.project.todo.ToDoItem.ToDoItem;
import spring.project.todo.ToDoItem.ToDoItemDTO;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);
        mapper.typeMap(String.class, String.class).setConverter(new StringTrimConverter());
        mapper.typeMap(CreateItemDTO.class, ToDoItem.class)
                .addMappings(m -> m.skip(ToDoItem::setToDoList));
        mapper.typeMap(ToDoItem.class, ToDoItemDTO.class)
                .addMappings(m -> m.map(src -> src.getToDoList().getId(), ToDoItemDTO::setToDoListId));

        return mapper;
    }

    private class StringTrimConverter implements Converter<String, String> {
        @Override
        public String convert(MappingContext<String, String> context) {
            if (context.getSource() == null) {
                return null;
            }
            return context.getSource().trim();
        }
    }
}
