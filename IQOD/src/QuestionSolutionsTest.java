import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionSolutionsTest {
    @Test
    void testMaxProfit() {
        assertEquals(9, QuestionSolutions.maxProfit(new int[]{3, 1, 5, 10, 2}));
        assertEquals(0, QuestionSolutions.maxProfit(new int[]{5, 4, 3, 2}));
        assertEquals(6, QuestionSolutions.maxProfit(new int[]{1, 2, 3, 5, 7}));
        assertEquals(45, QuestionSolutions.maxProfit(new int[]{5, 50, 1, 8}));
    }

    @Test
    void testOceanView() {
        assertEquals(3, QuestionSolutions.oceanView(new int[]{7, 6, 5, 5}));
        assertEquals(1, QuestionSolutions.oceanView(new int[]{5, 6, 7, 8}));
        assertEquals(5, QuestionSolutions.oceanView(new int[]{5, 4, 3, 2, 1}));
    }
}
