package by.training.katrinflicher.business;

import by.training.katrinflicher.data.api.TodoService;
import by.training.katrinflicher.data.api.TodoServiceStub;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class TodoBusinessImplMockTest {

    @Test
    public void testUsingMockito() {
        TodoService todoServiceMock = mock(TodoService.class);
        List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
        when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(allTodos);
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoServiceMock);
        List<String> filteredTodos = todoBusiness.retrieveTodosRelatedToSpring("Dummy");
        assertEquals(2, filteredTodos.size());
        assertTrue(filteredTodos.get(0).contains("Spring")&&filteredTodos.get(1).contains("Spring"));
    }

    @Test
    public void testUsingMockito_UsingBDD(){
        TodoService todoService = mock(TodoService.class);
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoService);
        List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
        given(todoService.retrieveTodos("Dummy")).willReturn(allTodos);
        List<String> filteredTodos = todoBusiness.retrieveTodosRelatedToSpring("Dummy");
        assertThat(filteredTodos.size(), is(2));
    }

    @Test
    public void testDeleteNow(){
        TodoService todoService = mock(TodoService.class);
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoService);
        List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
        when(todoService.retrieveTodos("Dummy")).thenReturn(allTodos);
        todoBusiness.deleteTodosNotRelatedToSpring("Dummy");
        verify(todoService).deleteTodo("Learn to Dance");
        verify(todoService, never()).deleteTodo("Learn Spring MVC");
        verify(todoService, never()).deleteTodo("Learn Spring");
        // atLeastOnce, atLeast
    }

    @Test
    public void testCaptureArgument(){
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        TodoService todoService = mock(TodoService.class);
        List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
        when(todoService.retrieveTodos("Dummy")).thenReturn(allTodos);
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoService);
        todoBusiness.deleteTodosNotRelatedToSpring("Dummy");
        verify(todoService).deleteTodo(argumentCaptor.capture());
        assertEquals("Learn to Dance", argumentCaptor.getValue());
    }

}