package bddtests;

import BusinessLogic.TodoBusinessImpl;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.internal.hamcrest.HamcrestArgumentMatcher;
import service.TodoService;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BDDTest {

    @Test
    public void testRetrieveTodosRelatedToSpring_usingBDD(){

        // Given
        TodoService todoServiceMock=mock(TodoService.class);

        List<String> todos=List.of("Learn Spring MVC","Learn Spring","Learn to Dance");

        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

        TodoBusinessImpl todoBusinessImpl=new TodoBusinessImpl(todoServiceMock);


        // When (Actual action ie. method call)
        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");

        // Then
        assertThat(filteredTodos,is(List.of("Learn Spring MVC","Learn Spring")));

        /*assertArrayEquals(new String[]{"Learn Spring MVC","Learn Spring"},filteredTodos.toArray());*/

    }

    @Test
    public void testListGetMethod_usingBDD(){

        // Given
        List<String> listMock=mock(List.class);
        given(listMock.get(anyInt())).willReturn("Hello");

        // When
        String firstElement=listMock.get(0);
        String secondElement=listMock.get(1);

        // Then
        assertThat(firstElement,is("Hello"));
        assertThat(secondElement,is("Hello"));
    }
}
