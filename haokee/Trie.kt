package haokee

class TrieNode {
  val children = Array<TrieNode?>(Char.MAX_VALUE.code) { null }
  var isEndOfWord = false
}

class Trie(private val root: TrieNode = TrieNode()) {
  private var size: Int = 0

  private fun copyNode(current: TrieNode): TrieNode {
    val result = TrieNode()
    result.isEndOfWord = current.isEndOfWord
    current.children.forEachIndexed { index, child ->
      if (child != null) {
        result.children[index] = copyNode(child)
      }
    }
    return result
  }

  fun copyOf() = Trie(copyNode(root))

  fun add(word: String) {
    size++
    var current = root
    for (char in word) {
      if (current.children[char.code] == null) {
        current.children[char.code] = TrieNode()
      }
      current = current.children[char.code]!!
    }
    current.isEndOfWord = true
  }

  fun contains(word: String): Boolean {
    var current = root
    for (char in word) {
      current = current.children[char.code] ?: return false
    }
    return current.isEndOfWord
  }

  fun containsPrefix(word: String): Boolean {
    var current = root
    for (char in word) {
      current = current.children[char.code] ?: return false
    }
    return true
  }

  fun getSize() = size

  fun isEmpty() = size == 0
}