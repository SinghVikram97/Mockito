package mockito;

import BusinessLogic.TodoBusinessImpl;
import org.junit.Test;
import service.TodoService;
import service.TodoServiceStub;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class TodoBusinessImplStubTest {

    @Test
    public void testRetrieveTodosRelatedToSpring_usingAStub(){
        TodoService todoService=new TodoServiceStub();
        TodoBusinessImpl todoBusinessImpl=new TodoBusinessImpl(todoService);

        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");

        assertArrayEquals(new String[]{"Learn Spring MVC","Learn Spring"},filteredTodos.toArray());
    }


}
