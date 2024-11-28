package haokee

import kotlin.math.sqrt

class NumberTheory {
  companion object {
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

    @JvmStatic
    fun lcm(a: Long, b: Long): Long {
      return a / gcd(a, b) * b
    }

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