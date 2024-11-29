package haokee

class BinarySearch {
  companion object {
    fun searchAnswer(range: IntRange, checker: (Int) -> Boolean): Int {
      var left = range.first
      var right = range.last
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

    inline fun <reified T: Number> searchArray(array: Array<T>, target: T): Int {
      return searchAnswer(array.indices) { Calculator.greaterEqual(array[it], target) }
    }
  }
}