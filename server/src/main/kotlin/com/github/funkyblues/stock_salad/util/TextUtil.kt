import java.util.*


class AhoCorasick(words: List<Pair<String, String>>) {
    data class Node(
        var wordIndex: Int? = null,
        val children: MutableMap<Char, Node> = mutableMapOf(),
        var fail: Node? = null
    )

    private val root = Node()
    private val words: List<Pair<String, String>>

    init {
        this.words = words.sortedBy { it.first }

        // Make trie
        this.words.forEachIndexed { index, word ->
            var current = root
            word.first.forEach { ch ->
                current = current.children.getOrPut(ch) { Node() }
            }
            current.wordIndex = index
        }

        // Make fail links
        val queue: Queue<Node> = LinkedList<Node>().apply { add(root) }
        while (queue.isNotEmpty()) {
            val current = queue.remove()
            current.children.entries.forEach { (ch, child) ->
                var failTo = current.fail
                while (failTo != null && !failTo.children.containsKey(ch)) {
                    failTo = failTo.fail
                }
                child.fail = if (failTo == null) root else failTo.children[ch]
                queue.add(child)
            }
        }
    }

    /**
     * @return list of words found in text
     */
    fun searchWordsIn(text: String): List<String> {
        val foundWords = TreeSet<Int>()
        var current = root
        text.forEach { ch ->
            while (current != root && !current.children.containsKey(ch)) {
                current = current.fail!!
            }
            current = current.children[ch] ?: root
            var temp = current
            while (temp != root) {
                temp.wordIndex?.let { foundWords.add(it) }
                temp = temp.fail!!
            }
        }
        return foundWords.stream().map { words[it].second }.toList()
    }
}
