package mockito;

import BusinessLogic.TodoBusinessImpl;
import org.junit.Test;
import service.TodoService;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TodoBusinessImplMockTest {

    @Test
    public void testRetrieveTodosRelatedToSpring_usingAMock(){
        TodoService todoServiceMock=mock(TodoService.class);

        // When retrieveTodos is called on todoServiceMock return this list of values
        when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(List.of("Learn Spring MVC","Learn Spring","Learn to Dance"));

        TodoBusinessImpl todoBusinessImpl=new TodoBusinessImpl(todoServiceMock);

        // This retrieveTodosRelatedToSpring will call retrieveTodos of todoServiceMock
        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");

        assertArrayEquals(new String[]{"Learn Spring MVC","Learn Spring"},filteredTodos.toArray());

    }

    // Able to test 2 diff scenarios without much code
    @Test
    public void testRetrieveTodosRelatedToSpring_withEmptyList(){
        TodoService todoServiceMock=mock(TodoService.class);

        // When retrieveTodos is called on todoServiceMock return this list of values
        when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(List.of());

        TodoBusinessImpl todoBusinessImpl=new TodoBusinessImpl(todoServiceMock);

        // This retrieveTodosRelatedToSpring will call retrieveTodos of todoServiceMock
        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");

        assertArrayEquals(new String[]{},filteredTodos.toArray());

    }

}
