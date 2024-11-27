package haokee

class Statistics {
  companion object {
    fun <T: Number> mean(data: List<T>): Double {
      return if (data.isEmpty()) {
        Double.NaN
      } else {
        data.sumOf { it.toDouble() } / data.size
      }
    }

    fun <T: Number> variance(data: List<T>): Double {
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

    fun <T: Number> standardDeviation(data: List<T>): Double {
      return kotlin.math.sqrt(variance(data))
    }
  }
}