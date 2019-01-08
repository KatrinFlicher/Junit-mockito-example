package by.training.katrinflicher.business;

import by.training.katrinflicher.data.api.TodoService;
import by.training.katrinflicher.data.api.TodoServiceStub;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TodoBusinessImplStubTest {

    @Test
    public void testUsingAStub() {
        TodoService todoServiceStub = new TodoServiceStub();
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoServiceStub);
        List<String> filteredTodos = todoBusiness.retrieveTodosRelatedToSpring("Dummy");
        assertEquals(2, filteredTodos.size());
        assertTrue(filteredTodos.get(0).contains("Spring")&&filteredTodos.get(1).contains("Spring"));
    }
}