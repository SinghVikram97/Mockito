package mockitospy;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class Spy {

    @Test
    public void test(){
        List arrayListMock = mock(ArrayList.class);

        // mocks return default value if not stubbed
        assertEquals(0,arrayListMock.size());

        // stub
        given(arrayListMock.size()).willReturn(5);
        assertEquals(5,arrayListMock.size());

        arrayListMock.add("Dummy"); // No change in size of array, dummy add
        assertEquals(5, arrayListMock.size()); // same, passes
    }

    @Test
    public void test_spy1(){
        List arrayListSpy=spy(ArrayList.class);
        assertEquals(0,arrayListSpy.size());
        arrayListSpy.add("Dummy");
        assertEquals(1,arrayListSpy.size()); // Actual array list so size increase by one
        arrayListSpy.remove("Dummy");
        assertEquals(0,arrayListSpy.size());
    }

    @Test
    public void test_spy2(){
        List arrayListSpy = spy(ArrayList.class);

        // All other methods will remain same but size method will return 5 always as we have specified
        given(arrayListSpy.size()).willReturn(5);

        arrayListSpy.add("Dummy"); //Array list changed but

        assertEquals(5, arrayListSpy.size()); //  passes as we have specified that it will return 5 whenever size method called

    }

    @Test
    public void test_sp3(){
        List arrayListSpy=spy(ArrayList.class);

        arrayListSpy.add("Dummy");

        verify(arrayListSpy).add("Dummy");

        verify(arrayListSpy,never()).clear();
    }

}
