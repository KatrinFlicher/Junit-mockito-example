package by.training.katrinflicher.powermock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
/*To be able to mock the Constructor, we need to add in the Class that creates the new object*/
@PrepareForTest(SystemUnderTest.class)
public class PowerMockitoMockingConstructorTest {
    @Mock
    Dependency dependencyMock;

    @InjectMocks
    SystemUnderTest systemUnderTest;

    @Test
    public void powerMockito_MockingAConstructor() throws Exception {
        ArrayList<String> listMock = mock(ArrayList.class);
        when(listMock.size()).thenReturn(100);
        PowerMockito.whenNew(ArrayList.class).withAnyArguments().thenReturn(listMock);
        int sizeList = systemUnderTest.methodUsingAnArrayListConstructor();
        assertEquals(100, sizeList);
    }
}
