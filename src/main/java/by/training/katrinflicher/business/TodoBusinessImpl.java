package by.training.katrinflicher.business;

import by.training.katrinflicher.data.api.TodoService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
//TodoBusinessImpl - SUT
//TodoService - dependency
public class TodoBusinessImpl {
    private TodoService todoService;

    public TodoBusinessImpl(TodoService todoService) {
        this.todoService = todoService;
    }

    public List<String> retrieveTodosRelatedToSpring(String user){
        List<String> filteredTodos = new ArrayList<String>();
        //List<String> filteredTodos = Collections.emptyList();
        List<String> allTodos = todoService.retrieveTodos(user);
        for (String todo: allTodos){
            if (todo.contains("Spring")){
                filteredTodos.add(todo);
            }
        }
        return filteredTodos;
    }

    public void deleteTodosNotRelatedToSpring(String user){
        List<String> allTodos = todoService.retrieveTodos(user);
        for (String todo: allTodos){
            if(!todo.contains("Spring")){
                todoService.deleteTodo(todo);
            }
        }
    }
}
