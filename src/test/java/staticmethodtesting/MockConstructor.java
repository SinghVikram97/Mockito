package staticmethodtesting;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import staticcode.SystemUnderTest;

import java.util.ArrayList;
import java.util.List;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SystemUnderTest.class)
public class MockConstructor {

    //PrepareForTest => Class using the Constructor to create the object => SystemUnderTest.class
    //Mock the constructor

    @InjectMocks
    SystemUnderTest systemUnderTest;

    @Mock
    ArrayList mockList;

    @Test
    public void testMethodCallingConstructor() throws Exception {

        Mockito.when(mockList.size()).thenReturn(5);

        // Override the constructor
        PowerMockito.whenNew(ArrayList.class).withAnyArguments().thenReturn(mockList);

        int size = systemUnderTest.methodUsingAnArrayListConstructor();

        Assert.assertEquals(5,size); // Line 33 we specified size 5


    }

}
