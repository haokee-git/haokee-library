package haokee

import kotlin.math.max
import kotlin.random.Random

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
    private fun <T : Comparable<T>> merge(arr: Array<T>, left: Int, right: Int) {
      val mid = (left + right) / 2
      var i = left
      var j = mid
      val temp = ArrayList<T>()
      while (i < mid && j < right) {
        temp.add(
          if (arr[i] <= arr[j]) {
            arr[i++]
          } else {
            arr[j++]
          }
        )
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
    fun <T : Comparable<T>> mergeSort(arr: Array<T>, left: Int = 0, right: Int = arr.size) {
      if (left >= right - 1) {
        return
      }
      val mid = (left + right) / 2
      mergeSort(arr, left, mid)
      mergeSort(arr, mid, right)
      merge(arr, left, right)
    }

    /**
     * 选择排序
     *
     * @param arr 待排序数组
     * @param left 左边界（包含）
     * @param right 右边界（不包含）
     * @param T 数组元素类型
     */
    fun <T : Comparable<T>> selectionSort(arr: Array<T>, left: Int = 0, right: Int = arr.size) {
      for (i in left..<right - 1) {
        var minIndex = i
        for (j in i + 1..<right) {
          if (arr[j] < arr[minIndex]) {
            minIndex = j
          }
        }
        if (minIndex != i) {
          val temp = arr[i]
          arr[i] = arr[minIndex]
          arr[minIndex] = temp
        }
      }
    }

    /**
     * 冒泡排序
     *
     * @param arr 待排序数组
     * @param left 左边界
     * @param right 右边界
     * @param T 数组元素类型
     */
    fun <T : Comparable<T>> bubbleSort(arr: Array<T>, left: Int = 0, right: Int = arr.size) {
      for (i in left..<right - 1) {
        for (j in left..<right - 1 - i + left) {
          if (arr[j] > arr[j + 1]) {
            val temp = arr[j]
            arr[j] = arr[j + 1]
            arr[j + 1] = temp
          }
        }
      }
    }

    /**
     * 插入排序
     *
     * @param arr 待排序数组
     * @param left 左边界
     * @param right 右边界
     * @param T 数组元素类型
     */
    fun <T : Comparable<T>> insertionSort(arr: Array<T>, left: Int = 0, right: Int = arr.size) {
      for (i in left + 1..<right) {
        val key = arr[i]
        var j = i - 1
        while (j >= left && arr[j] > key) {
          arr[j + 1] = arr[j]
          j--
        }
        arr[j + 1] = key
      }
    }

    /**
     * 桶排序
     *
     * @param arr 待排序数组
     * @param left 左边界
     * @param right 右边界
     * @param T 数组元素类型
     */
    fun bucketSort(arr: Array<UInt>, left: Int = 0, right: Int = arr.size) {
      if (arr.isEmpty()) {
        return
      }
      var maximum: UInt = 0U
      for (i in left..<right) {
        maximum = max(maximum, arr[i])
      }
      val bucket = IntArray(maximum.toInt() + 1)
      for (i in left..<right) {
        bucket[arr[i].toInt()]++
      }
      var index = 0
      for (i in 0..maximum.toInt()) {
        while (bucket[i] > 0) {
          arr[index++] = i.toUInt()
          bucket[i]--
        }
      }
    }

    /**
     * 猴子排序
     *
     * @param arr 待排序数组
     * @param left 左边界
     * @param right 右边界
     * @param T 数组元素类型
     */
    fun <T: Comparable<T>> bogoSort(arr: Array<T>, left: Int = 0, right: Int = arr.size) {
      if (arr.isEmpty() || left >= right) {
        return
      }
      fun shuffle() {
        for (i in left..<right) {
          val j = Random.nextInt(right - left) + left
          val temp = arr[i]
          arr[i] = arr[j]
          arr[j] = temp
        }
      }
      fun isSorted(): Boolean {
        for (i in left + 1..<right) {
          if (arr[i] < arr[i - 1]) {
            return false
          }
        }
        return true
      }
      while (!isSorted()) {
        shuffle()
      }
    }
  }
}