package by.training.katrinflicher.mockito;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class ListTest {

    @Test
    public void letsMockListSize(){
        List list = mock(List.class);
        when(list.size()).thenReturn(2);
        assertEquals(2, list.size());
    }

    @Test
    public void letsMockListSizeWithMultipleReturnValues(){
        List list = mock(List.class);
        when(list.size()).thenReturn(10).thenReturn(20);
        assertEquals(10, list.size());
        assertEquals(20, list.size());
    }

    @Test
    public void letsMockListGet(){
        List list = mock(List.class);
        when(list.get(0)).thenReturn("KatrinFlicher");
        assertEquals("KatrinFlicher", list.get(0));
        assertEquals(null, list.get(1));
    }

    @Test(expected = RuntimeException.class)
    public void letsMockListGetToThrowException(){
        List list = mock(List.class);
        when(list.get(anyInt())).thenThrow( new RuntimeException("Something went wrong"));
        list.get(1);
    }

    @Test
    public void letsMockListGetWithAny(){
        List list = mock(List.class);
        when(list.get(anyInt())).thenReturn("KatrinFlicher");
        assertEquals("KatrinFlicher",list.get(0));
        assertEquals("KatrinFlicher",list.get(1));
    }

    @Test
    public void bddAliases_UsingGivenWillReturn(){
        List<String> list = mock(List.class);
        given(list.get(anyInt())).willReturn("Katrin");
        assertThat(list.get(0), is("Katrin"));
        assertThat(list.get(1), is("Katrin"));
    }
}
