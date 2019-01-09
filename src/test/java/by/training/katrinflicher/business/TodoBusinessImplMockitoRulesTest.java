package by.training.katrinflicher.business;

import by.training.katrinflicher.data.api.TodoService;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.junit.JUnitRule;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TodoBusinessImplMockitoRulesTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    TodoService todoService;

    @InjectMocks
    TodoBusinessImpl todoBusiness;

    @Captor
    ArgumentCaptor<String> argumentCaptor;

    @Test
    public void testUsingMockito() {
        List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
        when(todoService.retrieveTodos("Dummy")).thenReturn(allTodos);
        List<String> filteredTodos = todoBusiness.retrieveTodosRelatedToSpring("Dummy");
        assertEquals(2, filteredTodos.size());
        assertTrue(filteredTodos.get(0).contains("Spring")&&filteredTodos.get(1).contains("Spring"));
    }

    @Test
    public void testUsingMockito_UsingBDD(){
        List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
        given(todoService.retrieveTodos("Dummy")).willReturn(allTodos);
        List<String> filteredTodos = todoBusiness.retrieveTodosRelatedToSpring("Dummy");
        assertThat(filteredTodos.size(), is(2));
    }

    @Test
    public void testDeleteNow(){
        List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
        when(todoService.retrieveTodos("Dummy")).thenReturn(allTodos);
        todoBusiness.deleteTodosNotRelatedToSpring("Dummy");
        verify(todoService).deleteTodo("Learn to Dance");
        verify(todoService, never()).deleteTodo("Learn Spring MVC");
        verify(todoService, never()).deleteTodo("Learn Spring");
        verify(todoService, times(1)).deleteTodo("Learn to Dance"); //equals to the first call
        // atLeastOnce, atLeast
    }

    @Test
    public void testCaptureArgument(){
        List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
        when(todoService.retrieveTodos("Dummy")).thenReturn(allTodos);
        todoBusiness.deleteTodosNotRelatedToSpring("Dummy");
        verify(todoService).deleteTodo(argumentCaptor.capture());
        assertEquals("Learn to Dance", argumentCaptor.getValue());
    }
}
