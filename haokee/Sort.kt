package haokee

class Sort {
  companion object {
    /**
     * 合并两个有序数组
     *
     * @param arr 待合并数组
     * @param left 左边界
     * @param right 右边界
     * @param T 数组元素类型
     * @see mergeSort
     */
    private fun <T: Comparable<T>> merge(arr: Array<T>, left: Int, right: Int) {
      val mid = (left + right) / 2
      var i = left
      var j = mid
      val temp = ArrayList<T>()
      while (i < mid && j < right) {
        temp.add(if (arr[i] <= arr[j]) {
          arr[i++]
        } else {
          arr[j++]
        })
      }
      while (i < mid) {
        temp.add(arr[i++])
      }
      while (j < right) {
        temp.add(arr[j++])
      }
      for (index in left..<right) {
        arr[index] = temp[index - left]
      }
    }

    /**
     * 归并排序
     *
     * @param arr 待排序数组
     * @param left 左边界
     * @param right 右边界
     * @param T 数组元素类型
     * @return 排序后的数组
     * @see merge
     */
    fun <T: Comparable<T>> mergeSort(arr: Array<T>, left: Int = 0, right: Int = arr.size) {
      if (left >= right - 1) {
        return
      }
      val mid = (left + right) / 2
      mergeSort(arr, left, mid)
      mergeSort(arr, mid, right)
      merge(arr, left, right)
    }
  }
}