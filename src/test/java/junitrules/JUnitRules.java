package junitrules;

import BusinessLogic.TodoBusinessImpl;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import service.TodoService;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

public class JUnitRules {

    /* Will run before and after a test */
    /* For MockitoJUnitRunner the rule is MockitoJUnit.rule() */
    /* Rules should be public */
    @Rule
    public MockitoRule mockitoRule= MockitoJUnit.rule();

    /* We can have multiple rules */


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
