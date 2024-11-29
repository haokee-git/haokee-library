package haokee

class Sort {
  companion object {
    private fun <T: Comparable<T>> merge(arr: Array<T>, left: Int, right: Int) {
      val mid = (left + right) / 2
      var i = left
      var j = mid + 1
      var k = left
      val temp = ArrayList<T>()
      while (i <= mid && j <= right) {
        temp.add(if (arr[i] < arr[j]) arr[i++] else arr[j++])
      }
      while (i <= mid) {
        temp.add(arr[i++])
      }
      while (j <= right) {
        temp.add(arr[j++])
      }
      for (index in left..right) {
        arr[index] = temp[index - left]
      }
    }

    fun <T: Comparable<T>> mergeSort(arr: Array<T>, left: Int = 0, right: Int = arr.lastIndex) {
      if (left >= right) {
        return
      }
      val mid = (left + right) / 2
      mergeSort(arr, left, mid)
      mergeSort(arr, mid + 1, right)
      merge(arr, left, right)
    }
  }
}