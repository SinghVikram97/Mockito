package staticmethodtesting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import staticcode.Dependency;
import staticcode.SystemUnderTest;
import staticcode.UtilityClass;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({UtilityClass.class})  // Class which contains static method
public class PowerMock {

    @Mock
    Dependency dependencyMock;

    @InjectMocks
    SystemUnderTest systemUnderTest;

    @Test
    public void testStaticMethod(){

        List<Integer> stats=List.of(1,2,3);

        when(dependencyMock.retrieveAllStats()).thenReturn(stats);

        // Class which contains static method
        PowerMockito.mockStatic(UtilityClass.class);

        // Mock that static method
        when(UtilityClass.staticMethod(anyLong())).thenReturn(150);

        int result=systemUnderTest.methodCallingAStaticMethod();

        assertEquals(150, result);

        // Check if Utility.StaticMethod called
        // 2 Steps-> 1. PowerMockito.verifyStatic() and 2. Actual Static Method call to verify

        PowerMockito.verifyStatic(UtilityClass.class);
        //UtilityClass.staticMethod(5);
        // Fail as it was expecting 6 as systemUnderTest would call staticMethod with value 6 (1+2+3 in stats List)
        UtilityClass.staticMethod(6);
    }

    @Test
    public void testPrivateMethod() throws Exception {

        List<Integer> stats=List.of(1,2,3);

        when(dependencyMock.retrieveAllStats()).thenReturn(stats);

        /*int result=systemUnderTest.privateMethodUnderTest();*/ // Private method can't call it like this

        long result=Whitebox.invokeMethod(systemUnderTest,"privateMethodUnderTest");

        assertEquals(6,result);

    }
}
