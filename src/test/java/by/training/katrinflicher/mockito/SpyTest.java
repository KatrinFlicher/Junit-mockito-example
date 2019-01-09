package by.training.katrinflicher.mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SpyTest {

    @Spy
    ArrayList<String> listSpy;
    //Equals to "List<String> listSpy = spy(ArrayList.class);"

    @Test
    public void creatingASpyOnArrayList(){
        listSpy.add("Katrin");
        listSpy.add("Flicher");

        verify(listSpy).add("Katrin");
        verify(listSpy).add("Flicher");

        assertEquals(2, listSpy.size());
        assertEquals("Katrin", listSpy.get(0));
    }

    @Test
    public void creatingASpyOnArrayList_overridingSpecificMethods(){
        listSpy.add("Katrin");
        listSpy.add("Flicher");

        stub(listSpy.size()).toReturn(-1);

        assertEquals(-1, listSpy.size());
        assertEquals("Katrin", listSpy.get(0));
    }
}
