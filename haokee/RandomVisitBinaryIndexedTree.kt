package haokee

import kotlin.random.Random

class RandomVisitBinaryIndexedTreeNode(var data: Int,
                                       var size: Int) {
  var leftSon: RandomVisitBinaryIndexedTreeNode? = null
  var rightSon: RandomVisitBinaryIndexedTreeNode? = null
}

class RandomVisitBinaryIndexedTree {
  private var root: RandomVisitBinaryIndexedTreeNode? = null

  companion object {
    private fun hasNotSon(current: RandomVisitBinaryIndexedTreeNode?): Boolean {
      return current != null && current.leftSon == null && current.rightSon == null
    }

    private fun deleteLeft(current: RandomVisitBinaryIndexedTreeNode): Int {
      val data = current.leftSon!!.data
      current.leftSon = null
      return data
    }

    private fun deleteRight(current: RandomVisitBinaryIndexedTreeNode): Int {
      val data = current.rightSon!!.data
      current.rightSon = null
      return data
    }
  }

  private fun swapData(left: RandomVisitBinaryIndexedTreeNode, right: RandomVisitBinaryIndexedTreeNode) {
    val temp = left.data
    left.data = right.data
    right.data = temp
  }

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

  fun add(value: Int) {
    if (root == null) {
      root = RandomVisitBinaryIndexedTreeNode(value, 1)
      return
    }
    insertNode(root!!, value)
  }

  fun get(): Int {
    if (root == null) {
      throw NoSuchElementException("The tree is empty.")
    }
    return randomGetData(root!!)
  }

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

  fun size() = root?.size ?: 0

  fun isEmpty() = size() == 0

  fun isNotEmpty() = size() != 0
}