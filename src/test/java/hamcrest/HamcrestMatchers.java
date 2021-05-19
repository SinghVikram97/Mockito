package hamcrest;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class HamcrestMatchers {

    @Test
    public void test(){

        List<Integer> scores=List.of(99,100,101,105);

        // Scores has 4 items
        assertThat(scores, hasSize(4));

        // Scores check items
        assertThat(scores,hasItems(99,100));

        // Every item > 90


        // String related asserts
        assertThat("",isEmptyString());
        assertThat(null,isEmptyOrNullString());

        // Arrays
        Integer[] marks={1,2,3};

        assertThat(marks,arrayWithSize(3));
        assertThat(marks,arrayContaining(1,2,3)); // Same order
        assertThat(marks,arrayContainingInAnyOrder(2,3,1));
    }

}
