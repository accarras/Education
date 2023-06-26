import org.junit.Test;

import static org.junit.Assert.*;

public class twoSumHashTest {
    @Test
    public void twoSumHashTest() {
        int[] test = {2, 7, 11, 15};
        int[] result = {0, 1};
        assertEquals(twoSumHash(test, 9), result);
    }

}
