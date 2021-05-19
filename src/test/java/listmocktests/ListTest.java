package listmocktests;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListTest {

    @Test
    public void mockListSizeMethod_Simple(){

        List listMock= mock(List.class);
        when(listMock.size()).thenReturn(2);
        assertEquals(2,listMock.size());

    }

    @Test
    public void mockListSizeMethod_ReturnMultipleValues(){

        List listMock= mock(List.class);
        when(listMock.size()).thenReturn(2).thenReturn(3);
        assertEquals(2,listMock.size());
        assertEquals(3,listMock.size());

    }

    @Test
    public void mockListGetMethod_Simple(){

        List listMock= mock(List.class);
        when(listMock.get(0)).thenReturn("Hello");

        assertEquals("Hello",listMock.get(0));
        // Default value if no stubbing specified ie. null
        assertNull(listMock.get(1));

    }

    @Test
    public void mockListGetMethod_ArgumentMatcher(){

        List listMock= mock(List.class);

        // Argument matcher, returns Hello for every get call
        when(listMock.get(anyInt())).thenReturn("Hello");

        assertEquals("Hello",listMock.get(0));
        assertEquals("Hello",listMock.get(1));


    }

    @Test(expected = RuntimeException.class)
    public void mockListGetMethod_throwAnException(){

        List listMock= mock(List.class);

        // Argument matcher, returns Hello for every get call
        when(listMock.get(anyInt())).thenThrow(new RuntimeException("Something"));

        listMock.get(0);

    }
}
