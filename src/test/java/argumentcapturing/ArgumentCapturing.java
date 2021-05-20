package argumentcapturing;

import BusinessLogic.TodoBusinessImpl;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import service.TodoService;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

public class ArgumentCapturing {

    @Test
    public void deleteTodosNotRelatedToSpring_BDD_argumentCapture(){

        // Declare Argument Captor
        ArgumentCaptor<String> stringArgumentCaptor=ArgumentCaptor.forClass(String.class);

        // Given
        TodoService todoServiceMock=mock(TodoService.class);

        List<String> todos=List.of("Learn Spring MVC","Learn Spring","Learn to Dance");

        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

        TodoBusinessImpl todoBusinessImpl=new TodoBusinessImpl(todoServiceMock);


        // When
        todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");

        // Then
        // Check what argument was passed to deleteTodo
        // Define Argument Captor on specific method call
        then(todoServiceMock).should().deleteTodo(stringArgumentCaptor.capture());

        // Capture the argument
        Assert.assertThat(stringArgumentCaptor.getValue(),is("Learn to Dance"));

    }

    @Test
    public void deleteTodosNotRelatedToSpring_BDD_argumentCaptureMultipleTimes(){

        // Declare Argument Captor
        ArgumentCaptor<String> stringArgumentCaptor=ArgumentCaptor.forClass(String.class);

        // Given
        TodoService todoServiceMock=mock(TodoService.class);

        List<String> todos=List.of("Learn rock and roll","Learn Spring","Learn to Dance");

        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

        TodoBusinessImpl todoBusinessImpl=new TodoBusinessImpl(todoServiceMock);


        // When
        todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");

        // Then
        // Check what argument was passed to deleteTodo
        // Define Argument Captor on specific method call
        then(todoServiceMock).should(times(2)).deleteTodo(stringArgumentCaptor.capture());

        // Capture the argument
        Assert.assertThat(stringArgumentCaptor.getAllValues(),is(List.of("Learn rock and roll","Learn to Dance")));

    }
}
