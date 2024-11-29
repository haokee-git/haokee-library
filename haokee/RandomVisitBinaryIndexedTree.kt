package haokee

import kotlin.random.Random

/**
 * 随机访问二叉索引树的结点类
 *
 * @property data 结点的数据
 * @property size 结点的大小
 * @property leftSon 左儿子
 * @property rightSon 右儿子
 */
class RandomVisitBinaryIndexedTreeNode(var data: Int,
                                       var size: Int) {
  var leftSon: RandomVisitBinaryIndexedTreeNode? = null
  var rightSon: RandomVisitBinaryIndexedTreeNode? = null
}

/**
 * 随机访问二叉索引树
 *
 * @property root 根结点
 * @constructor 创建一个随机访问二叉索引树
 */
class RandomVisitBinaryIndexedTree {
  private var root: RandomVisitBinaryIndexedTreeNode? = null

  companion object {
    /**
     * 判断当前结点是否没有儿子
     *
     * @param current 当前结点
     * @return 是否没有儿子
     */
    private fun hasNotSon(current: RandomVisitBinaryIndexedTreeNode?): Boolean {
      return current != null && current.leftSon == null && current.rightSon == null
    }

    /**
     * 删除左儿子
     *
     * @param current 当前结点
     * @return 左儿子的数据
     */
    private fun deleteLeft(current: RandomVisitBinaryIndexedTreeNode): Int {
      val data = current.leftSon!!.data
      current.leftSon = null
      return data
    }

    /**
     * 删除右儿子
     *
     * @param current 当前结点
     * @return 右儿子的数据
     */
    private fun deleteRight(current: RandomVisitBinaryIndexedTreeNode): Int {
      val data = current.rightSon!!.data
      current.rightSon = null
      return data
    }

    /**
     * 交换两个结点的数据
     *
     * @param left 左结点
     * @param right 右结点
     */
    private fun swapData(left: RandomVisitBinaryIndexedTreeNode, right: RandomVisitBinaryIndexedTreeNode) {
      val temp = left.data
      left.data = right.data
      right.data = temp
    }
  }

  /**
   * 插入结点
   *
   * @param current 当前结点
   * @param data 插入的数据
   * @see swapData
   */
  private fun insertNode(current: RandomVisitBinaryIndexedTreeNode, data: Int) {
    current.size++
    if (current.leftSon == null) {
      current.leftSon = RandomVisitBinaryIndexedTreeNode(data, 1)
      return
    } else if (current.rightSon == null) {
      current.rightSon = RandomVisitBinaryIndexedTreeNode(data, 1)
      return
    }
    if (current.leftSon!!.size < current.rightSon!!.size) {
      if (Random.nextBoolean()) {
        swapData(current, current.leftSon!!)
      }
      insertNode(current.leftSon!!, data)
    } else if (current.leftSon!!.size > current.rightSon!!.size) {
      if (Random.nextBoolean()) {
        swapData(current, current.rightSon!!)
      }
      insertNode(current.rightSon!!, data)
    } else {
      val son = if (Random.nextBoolean()) current.leftSon else current.rightSon
      if (Random.nextBoolean()) {
        swapData(current, son!!)
      }
      insertNode(son!!, data)
    }
  }

  /**
   * 随机获取数据
   *
   * @param current 当前结点
   * @return 随机获取的数据
   * @see swapData
   */
  private fun randomGetData(current: RandomVisitBinaryIndexedTreeNode): Int {
    if (current.leftSon == null && current.rightSon == null) {
      return current.data
    } else if (current.leftSon != null && current.rightSon == null) {
      if (Random.nextBoolean()) {
        swapData(current, current.leftSon!!)
      }
      return randomGetData(current.leftSon!!)
    } else if (current.leftSon == null) {
      if (Random.nextBoolean()) {
        swapData(current, current.rightSon!!)
      }
      return randomGetData(current.rightSon!!)
    }
    val son = if (Random.nextBoolean()) current.leftSon else current.rightSon
    if (Random.nextBoolean()) {
      swapData(current, son!!)
    }
    return randomGetData(son!!)
  }

  /**
   * 随机删除结点
   *
   * @param current 当前结点
   * @return 删除的数据
   * @see swapData
   */
  private fun randomRemove(current: RandomVisitBinaryIndexedTreeNode): Int {
    current.size--
    if (hasNotSon(current.leftSon) && !hasNotSon(current.rightSon)) {
      return deleteLeft(current)
    } else if (!hasNotSon(current.leftSon) && hasNotSon(current.rightSon)) {
      return deleteRight(current)
    } else if (hasNotSon(current.leftSon) && hasNotSon(current.rightSon)) {
      return if (Random.nextBoolean()) deleteLeft(current) else deleteRight(current)
    }
    if (current.leftSon != null && current.rightSon == null) {
      if (Random.nextBoolean()) {
        swapData(current, current.leftSon!!)
      }
      return randomRemove(current.leftSon!!)
    } else if (current.leftSon == null && current.rightSon != null) {
      if (Random.nextBoolean()) {
        swapData(current, current.rightSon!!)
      }
      return randomRemove(current.rightSon!!)
    }
    val son = if (Random.nextBoolean()) current.leftSon else current.rightSon
    if (Random.nextBoolean()) {
      swapData(current, son!!)
    }
    return randomRemove(son!!)
  }

  /**
   * 添加数据
   *
   * @param value 添加的数据
   * @see insertNode
   */
  fun add(value: Int) {
    if (root == null) {
      root = RandomVisitBinaryIndexedTreeNode(value, 1)
      return
    }
    insertNode(root!!, value)
  }

  /**
   * 获取数据
   *
   * @return 获取的数据
   * @see randomGetData
   * @throws NoSuchElementException 如果树为空
   */
  fun get(): Int {
    if (root == null) {
      throw NoSuchElementException("The tree is empty.")
    }
    return randomGetData(root!!)
  }

  /**
   * 获取并删除数据
   *
   * @return 删除的数据
   * @see randomRemove
   * @throws NoSuchElementException 如果树为空
   */
  fun poll(): Int {
    if (root == null) {
      throw NoSuchElementException("The tree is empty.")
    }
    if (hasNotSon(root)) {
      val data = root!!.data
      root = null
      return data
    }
    return randomRemove(root!!)
  }

  /**
   * 获取大小
   *
   * @return 大小
   */
  fun size() = root?.size ?: 0

  /**
   * 判断是否为空
   *
   * @return 是否为空
   * @see size
   */
  fun isEmpty() = size() == 0

  /**
   * 判断是否不为空
   *
   * @return 是否不为空
   * @see size
   */
  fun isNotEmpty() = size() != 0
}