import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AhoCorasickTest {
    @Test
    fun searchWordsInTest() {
        val ahoCorasick = AhoCorasick(ArrayList<Pair<String, String>>().apply{
            add(Pair("he", "A"))
            add(Pair("she", "B"))
            add(Pair("his", "C"))
            add(Pair("hers", "D"))
        })

        var foundWords = ahoCorasick.searchWordsIn("ahishers")
        assertEquals(listOf("A", "D", "C", "B"), foundWords)

        foundWords = ahoCorasick.searchWordsIn("hishers")
        assertEquals(listOf("A", "D", "C", "B"), foundWords)

        foundWords = ahoCorasick.searchWordsIn("hishis")
        assertEquals(listOf("C"), foundWords)

        foundWords = ahoCorasick.searchWordsIn("hishershershis")
        assertEquals(listOf("A", "D", "C", "B"), foundWords)
    }
}
