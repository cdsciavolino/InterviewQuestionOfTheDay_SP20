import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    @Test
    void testKNearestRestaurants() {
        QuestionSolutions.Coordinate rest1 = new QuestionSolutions.Coordinate(1, 1);
        QuestionSolutions.Coordinate rest2 = new QuestionSolutions.Coordinate(2, 0);
        QuestionSolutions.Coordinate rest3 = new QuestionSolutions.Coordinate(3, 4);
        QuestionSolutions.Coordinate rest4 = new QuestionSolutions.Coordinate(-1, 0);
        QuestionSolutions.Coordinate home = new QuestionSolutions.Coordinate(0, 0);
        QuestionSolutions.Coordinate[] computed = QuestionSolutions.kNearestRestaurants(
                new QuestionSolutions.Coordinate[] { rest1, rest2, rest3, rest4},
                home,
                1
        );

        assertEquals("(-1, 0)", computed[0].toString());
    }

    @Test
    void testKMostFrequentWords() {
        String corpus = "the cat sat on the red fluffy carpet with another cat and the third fluffy cat joined the others";
        String[] omit = new String[]{ "a", "on", "the", "with" };
        String[] computed = QuestionSolutions.kMostFrequentWords(corpus, omit, 2);
        assertEquals("cat", computed[0]);
        assertEquals("fluffy", computed[1]);
    }

    @Test
    void testNumIslands() {
        int[][] example = {
                { 1, 1, 0, 0, 1 },
                { 1, 1, 0, 1, 1 },
                { 0, 0, 1, 0, 0 },
                { 0, 0, 1, 1, 1 },
        };
        assertEquals(3, QuestionSolutions.numIslands(example));
    }

    @Test
    void testMinWordTransform() {
        List<String> wordList = new ArrayList<String>();
        Collections.addAll(wordList, "hot","dot","dog","lot","log","cog");
        assertEquals(5, QuestionSolutions.minWordTransform("hit", "cog", wordList));

        wordList.remove("cog");
        assertEquals(0, QuestionSolutions.minWordTransform("hit", "cog", wordList));
        assertEquals(2, QuestionSolutions.minWordTransform("hit", "hot", wordList));
        assertEquals(0, QuestionSolutions.minWordTransform("cog", "hit", wordList));
    }

    @Test
    void testLRUCache() {
        QuestionSolutions.LRUCache cache = new QuestionSolutions.LRUCache(2);
        cache.put(1, 1);
        assertEquals(1, cache.get(1));
        cache.put(2, 2);
        assertEquals(1, cache.get(1));
        assertEquals(2, cache.get(2));
        cache.put(3, 3);
        assertEquals(3, cache.get(3));
        assertEquals(2, cache.get(2));
        assertEquals(-1, cache.get(1));
        cache.put(4, 4);
        assertEquals(-1, cache.get(3));
        assertEquals(2, cache.get(2));
        assertEquals(4, cache.get(4));
        cache.put(4, 5);
        assertEquals(5, cache.get(4));
    }
}
