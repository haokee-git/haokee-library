package haokee

/**
 * 字典树节点类。
 */
class TrieNode {
  val children = Array<TrieNode?>(Char.MAX_VALUE.code) { null }
  var isEndOfWord = false
}

/**
 * 字典树类。
 *
 * @property root 根节点。
 * @property size 字典树中单词的数量。
 */
class Trie(private val root: TrieNode = TrieNode()) {
  private var size: Int = 0

  /**
   * 复制节点。
   *
   * @param current 当前节点。
   * @return 复制后的节点。
   */
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

  /**
   * 复制字典树。
   *
   * @return 复制后的字典树。
   */
  fun copyOf() = Trie(copyNode(root))

  /**
   * 添加单词。
   *
   * @param word 要添加的单词。
   */
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

  /**
   * 检查字典树中是否包含某个单词。
   *
   * @param word 要检查的单词。
   * @return 如果包含该单词则返回 true，否则返回 false。
   */
  fun contains(word: String): Boolean {
    var current = root
    for (char in word) {
      current = current.children[char.code] ?: return false
    }
    return current.isEndOfWord
  }

  /**
   * 检查字典树中是否包含某个前缀。
   *
   * @param word 要检查的前缀。
   * @return 如果包含该前缀则返回 true，否则返回 false。
   */
  fun containsPrefix(word: String): Boolean {
    var current = root
    for (char in word) {
      current = current.children[char.code] ?: return false
    }
    return true
  }

  /**
   * 获取字典树中单词的数量。
   *
   * @return 单词的数量。
   */
  fun getSize() = size

  /**
   * 检查字典树是否为空。
   *
   * @return 如果为空则返回 true，否则返回 false。
   */
  fun isEmpty() = size == 0
}