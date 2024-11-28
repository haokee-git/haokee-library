package haokee

import kotlin.math.sqrt

class NumberTheory {
  companion object {
    /**
     * 判断一个数是否是素数。
     *
     * @param n 待判断的数。
     * @return 如果是素数则返回 true，否则返回 false。
     */
    @JvmStatic
    fun isPrime(n: Long): Boolean {
      if (n <= 1) {
        return false
      }
      for (i in 2..sqrt(n.toDouble()).toLong()) {
        if (n % i == 0L) {
          return false
        }
      }
      return true
    }

    /**
     * 计算两个数的最大公约数。
     *
     * @param a 第一个数。
     * @param b 第二个数。
     * @return 两个数的最大公约数。
     */
    @JvmStatic
    fun gcd(a: Long, b: Long): Long {
      var x = a
      var y = b
      while (y != 0L) {
        val temp = y
        y = x % y
        x = temp
      }
      return x
    }

    /**
     * 计算两个数的最小公倍数。
     *
     * @param a 第一个数。
     * @param b 第二个数。
     * @return 两个数的最小公倍数。
     */
    @JvmStatic
    fun lcm(a: Long, b: Long): Long {
      return a / gcd(a, b) * b
    }

    /**
     * 计算欧拉函数值。
     *
     * @param n 待计算的数。
     * @return 欧拉函数值。
     */
    @JvmStatic
    fun eulerTotient(n: Long): Long {
      var result = n
      var number = n
      for  (i in 2..sqrt(n.toDouble()).toLong()) {
        if (number % i == 0L) {
          result = result / i * (i - 1)
          while (number % i == 0L) {
            number /= i
          }
        }
      }
      if (number > 1) {
        result = result / number * (number - 1)
      }
      return result
    }
  }
}