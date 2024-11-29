package haokee

class BinarySearch {
  companion object {
    /**
     * 区间二分查找。
     *
     * @param array 有序数组。
     * @param target 目标值。
     * @param checker 检查函数，`true` 时左移，`false` 时右移
     * @return 目标值的下标，如果不存在则返回 `range.last + 1`。
     */
    fun searchInRange(range: IntRange, checker: (Int) -> Boolean): Int {
      var left = range.first
      var right = range.last + 1
      while (left < right) {
        val mid = left + (right - left) / 2
        if (checker(mid)) {
          right = mid
        } else {
          left = mid + 1
        }
      }
      return left
    }

    /**
     * 序列二分查找，寻找第一个大于等于 `target` 的元素
     *
     * @param array 有序数组。
     * @param target 目标值。
     * @return 目标值的下标，如果不存在则返回最后一个元素的后面一个元素下标。
     */
    inline fun <reified T: Comparable<T>> searchGreaterEqual(array: Array<T>, target: T): Int {
      return searchInRange(array.indices) { array[it] >= target }
    }

    /**
     * 序列二分查找，寻找第一个大于 `target` 的元素
     *
     * @param array 有序数组。
     * @param target 目标值。
     * @return 目标值的下标，如果不存在则返回最后一个元素的后面一个元素下标。
     */
    inline fun <reified T: Comparable<T>> searchGreater(array: Array<T>, target: T): Int {
      return searchInRange(array.indices) { array[it] > target }
    }
  }
}