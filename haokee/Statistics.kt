package haokee

import java.util.*
import kotlin.math.pow

/**
 * 统计计算类，包含各种统计方法。
 */
class Statistics {
  companion object {

    /**
     * 计算均值（平均值）。
     *
     * @param data 数据列表。
     * @return 均值。
     */
    @JvmStatic
    fun <T : Number> mean(data: List<T>): Double {
      return if (data.isEmpty()) {
        Double.NaN
      } else {
        data.sumOf { it.toDouble() } / data.size
      }
    }

    /**
     * 计算方差。
     *
     * @param data 数据列表。
     * @return 方差。
     */
    @JvmStatic
    fun <T : Number> variance(data: List<T>): Double {
      return if (data.isEmpty()) {
        Double.NaN
      } else {
        val m = mean(data)
        var result = 0.0
        data.forEach {
          result += (it.toDouble() - m) * (it.toDouble() - m)
        }
        result / data.size
      }
    }

    /**
     * 计算标准差。
     *
     * @param data 数据列表。
     * @return 标准差。
     */
    @JvmStatic
    fun <T : Number> standardDeviation(data: List<T>): Double {
      return kotlin.math.sqrt(variance(data))
    }

    /**
     * 计算中位数。
     *
     * @param data 数据列表。
     * @return 中位数。
     */
    @JvmStatic
    fun <T : Number> median(data: List<T>): Double {
      if (data.isEmpty()) {
        return Double.NaN
      }
      val sortedList = data.map { it.toDouble() }.sorted()
      val middle = sortedList.size / 2
      return if (sortedList.size % 2 == 0) {
        (sortedList[middle - 1] + sortedList[middle]) / 2.0
      } else {
        sortedList[middle]
      }
    }

    /**
     * 计算众数。
     *
     * @param data 数据列表。
     * @return 众数列表。
     */
    @JvmStatic
    fun <T : Number> mode(data: List<T>): List<Double> {
      if (data.isEmpty()) return emptyList()
      val frequencyMap = data.groupingBy { it.toDouble() }.eachCount()
      val maxFrequency = frequencyMap.values.maxOrNull() ?: return emptyList()
      return frequencyMap.filter { it.value == maxFrequency }.keys.toList()
    }

    /**
     * 计算范围。
     *
     * @param data 数据列表。
     * @return 范围。
     */
    @JvmStatic
    fun <T : Number> range(data: List<T>): Double {
      if (data.isEmpty()) return Double.NaN
      return data.maxOf { it.toDouble() } - data.minOf { it.toDouble() }
    }

    /**
     * 计算四分位数。
     *
     * @param data 数据列表。
     * @return 三个四分位数。
     */
    @JvmStatic
    fun <T : Number> quartiles(data: List<T>): Triple<Double, Double, Double> {
      if (data.isEmpty()) return Triple(Double.NaN, Double.NaN, Double.NaN)
      val sortedList = data.map { it.toDouble() }.sorted()
      val q2 = median(sortedList)
      val q1 = median(sortedList.subList(0, sortedList.size / 2))
      val q3 = median(sortedList.subList((sortedList.size + 1) / 2, sortedList.size))
      return Triple(q1, q2, q3)
    }

    /**
     * 计算平均绝对偏差。
     *
     * @param data 数据列表。
     * @return 平均绝对偏差。
     */
    @JvmStatic
    fun <T : Number> meanAbsoluteDeviation(data: List<T>): Double {
      if (data.isEmpty()) return Double.NaN
      val mean = mean(data)
      return data.map { kotlin.math.abs(it.toDouble() - mean) }.average()
    }

    /**
     * 计算变异系数。
     *
     * @param data 数据列表。
     * @return 变异系数。
     */
    @JvmStatic
    fun <T : Number> coefficientOfVariation(data: List<T>): Double {
      if (data.isEmpty()) return Double.NaN
      val mean = mean(data)
      val standardDeviation = standardDeviation(data)
      return if (mean == 0.0) Double.NaN else (standardDeviation / mean) * 100
    }

    /**
     * 计算偏度。
     *
     * @param data 数据列表。
     * @return 偏度。
     */
    @JvmStatic
    fun <T : Number> skewness(data: List<T>): Double {
      if (data.isEmpty()) return Double.NaN
      val mean = mean(data)
      val n = data.size
      val m3 = data.sumOf { (it.toDouble() - mean).pow(3) } / n
      val m2 = data.sumOf { (it.toDouble() - mean).pow(2) } / n
      return m3 / m2.pow(1.5)
    }

    /**
     * 计算峰度。
     *
     * @param data 数据列表。
     * @return 峰度。
     */
    @JvmStatic
    fun <T : Number> kurtosis(data: List<T>): Double {
      if (data.isEmpty()) return Double.NaN
      val mean = mean(data)
      val n = data.size
      val m4 = data.sumOf { (it.toDouble() - mean).pow(4) } / n
      val m2 = data.sumOf { (it.toDouble() - mean).pow(2) } / n
      return m4 / m2.pow(2) - 3
    }
  }
}