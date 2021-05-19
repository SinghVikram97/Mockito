package mockitoannotations;

import BusinessLogic.TodoBusinessImpl;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.TodoService;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class) /* To enable @Mock annotation */
public class MockitoAnnotations {

    @Mock /* Creates a mock of this type automatically*/
    TodoService todoServiceMock;

    @InjectMocks
    TodoBusinessImpl todoBusinessImpl;
    /* Mockito looks at all the dependencies of TodoBusinessImpl and checks if it matches with any of the mocks */

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @Test
    public void retrieveTodosRelatedToSpring(){
        List<String> todos=List.of("Learn Spring MVC","Learn Spring","Learn to Dance");

        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");

        assertThat(filteredTodos,is(List.of("Learn Spring MVC","Learn Spring")));
    }

    @Test
    public void deleteTodosNotRelatedToSpring_BDD() {
        List<String> todos=List.of("Learn Spring MVC","Learn Spring","Learn to Dance");

        // @Mock eliminates this
        /*TodoService todoServiceMock=mock(TodoService.class);*/

        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

        // @InjectMocks eliminates this
        /*TodoBusinessImpl todoBusinessImpl=new TodoBusinessImpl(todoServiceMock);*/

        todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");

        then(todoServiceMock).should().deleteTodo("Learn to Dance");
    }

    @Test
    public void deleteTodosNotRelatedToSpring_BDD_argumentCapture(){

        List<String> todos=List.of("Learn Spring MVC","Learn Spring","Learn to Dance");

        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

        todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");

        then(todoServiceMock).should().deleteTodo(stringArgumentCaptor.capture());

        Assert.assertThat(stringArgumentCaptor.getValue(), CoreMatchers.is("Learn to Dance"));

    }

}
