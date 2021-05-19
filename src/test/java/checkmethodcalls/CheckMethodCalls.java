package checkmethodcalls;

import BusinessLogic.TodoBusinessImpl;
import org.junit.Test;
import service.TodoService;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class CheckMethodCalls {

    // Check if todoService.deleteTodo got called only on todos not related to Spring
    @Test
    public void deleteTodosNotRelatedToSpring_BDD(){

        // Given
        TodoService todoServiceMock=mock(TodoService.class);

        List<String> todos=List.of("Learn Spring MVC","Learn Spring","Learn to Dance");

        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

        TodoBusinessImpl todoBusinessImpl=new TodoBusinessImpl(todoServiceMock);


        // When (Actual action ie. method call)
        todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");

        // Then
        // Check that deleteTodo called only with todos not related to spring

        verify(todoServiceMock).deleteTodo("Learn to Dance");
        /*verify(todoServiceMock).deleteTodo("Learn Spring");*/ // Fails

        verify(todoServiceMock,never()).deleteTodo("Learn Spring MVC");
        verify(todoServiceMock,never()).deleteTodo("Learn Spring");
    }
}
