package haokee

class Calculator {
  companion object {
    /**
     * 加法运算。
     *
     * @param T1 第一个操作数的类型。
     * @param T2 第二个操作数的类型。
     * @param a 第一个操作数。
     * @param b 第二个操作数。
     * @return 两个操作数相加的结果。
     * @throws IllegalArgumentException 如果类型不匹配或不支持的类型。
     */
    inline fun <reified T1 : Number, reified T2 : Number> plus(a: T1, b: T2): Number {
      if (T1::class != T2::class) {
        throw IllegalArgumentException("Types do not match: ${T1::class} and ${T2::class}")
      }
      return when (a) {
        is Short -> a + b.toShort()
        is Int -> a + b.toInt()
        is Long -> a + b.toLong()
        is Float -> a + b.toFloat()
        is Double -> a + b.toDouble()
        else -> throw IllegalArgumentException("Unsupported number type: ${T1::class}")
      }
    }

    /**
     * 减法运算。
     *
     * @param T1 第一个操作数的类型。
     * @param T2 第二个操作数的类型。
     * @param a 第一个操作数。
     * @param b 第二个操作数。
     * @return 两个操作数相减的结果。
     * @throws IllegalArgumentException 如果类型不匹配或不支持的类型。
     */
    inline fun <reified T1 : Number, reified T2 : Number> minus(a: T1, b: T2): Number {
      if (T1::class != T2::class) {
        throw IllegalArgumentException("Types do not match: ${T1::class} and ${T2::class}")
      }
      return when (a) {
        is Short -> a - b.toShort()
        is Int -> a - b.toInt()
        is Long -> a - b.toLong()
        is Float -> a - b.toFloat()
        is Double -> a - b.toDouble()
        else -> throw IllegalArgumentException("Unsupported number type: ${T1::class}")
      }
    }

    /**
     * 乘法运算。
     *
     * @param T1 第一个操作数的类型。
     * @param T2 第二个操作数的类型。
     * @param a 第一个操作数。
     * @param b 第二个操作数。
     * @return 两个操作数相乘的结果。
     * @throws IllegalArgumentException 如果类型不匹配或不支持的类型。
     */
    inline fun <reified T1 : Number, reified T2 : Number> times(a: T1, b: T2): Number {
      if (T1::class != T2::class) {
        throw IllegalArgumentException("Types do not match: ${T1::class} and ${T2::class}")
      }
      return when (a) {
        is Short -> a * b.toShort()
        is Int -> a * b.toInt()
        is Long -> a * b.toLong()
        is Float -> a * b.toFloat()
        is Double -> a * b.toDouble()
        else -> throw IllegalArgumentException("Unsupported number type: ${T1::class}")
      }
    }

    /**
     * 取模运算。
     *
     * @param T1 第一个操作数的类型。
     * @param T2 第二个操作数的类型。
     * @param a 第一个操作数。
     * @param b 第二个操作数。
     * @return 两个操作数取模的结果。
     * @throws IllegalArgumentException 如果类型不匹配或不支持的类型。
     */
    inline fun <reified T1 : Number, reified T2 : Number> rem(a: T1, b: T2): Number {
      if (T1::class != T2::class) {
        throw IllegalArgumentException("Types do not match: ${T1::class} and ${T2::class}")
      }
      return when (a) {
        is Short -> a % b.toShort()
        is Int -> a % b.toInt()
        is Long -> a % b.toLong()
        is Float -> a % b.toFloat()
        is Double -> a % b.toDouble()
        else -> throw IllegalArgumentException("Unsupported number type: ${T1::class}")
      }
    }

    /**
     * 除法运算。
     *
     * @param T1 第一个操作数的类型。
     * @param T2 第二个操作数的类型。
     * @param a 第一个操作数。
     * @param b 第二个操作数。
     * @return 两个操作数相除的结果。
     * @throws IllegalArgumentException 如果类型不匹配或不支持的类型。
     */
    inline fun <reified T1 : Number, reified T2 : Number> equal(a: T1, b: T2): Boolean {
      if (T1::class != T2::class) {
        throw IllegalArgumentException("Types do not match: ${T1::class} and ${T2::class}")
      }
      return when (a) {
        is Short -> a == b.toShort()
        is Int -> a == b.toInt()
        is Long -> a == b.toLong()
        is Float -> a == b.toFloat()
        is Double -> a == b.toDouble()
        else -> throw IllegalArgumentException("Unsupported number type: ${T1::class}")
      }
    }

    /**
     * 不等运算。
     *
     * @param T1 第一个操作数的类型。
     * @param T2 第二个操作数的类型。
     * @param a 第一个操作数。
     * @param b 第二个操作数。
     * @return 两个操作数不等的结果。
     * @throws IllegalArgumentException 如果类型不匹配或不支持的类型。
     */
    inline fun <reified T1 : Number, reified T2 : Number> notEqual(a: T1, b: T2): Boolean {
      if (T1::class != T2::class) {
        throw IllegalArgumentException("Types do not match: ${T1::class} and ${T2::class}")
      }
      return when (a) {
        is Short -> a == b.toShort()
        is Int -> a != b.toInt()
        is Long -> a != b.toLong()
        is Float -> a != b.toFloat()
        is Double -> a != b.toDouble()
        else -> throw IllegalArgumentException("Unsupported number type: ${T1::class}")
      }
    }

    /**
     * 小于运算。
     *
     * @param T1 第一个操作数的类型。
     * @param T2 第二个操作数的类型。
     * @param a 第一个操作数。
     * @param b 第二个操作数。
     * @return 两个操作数小于的结果。
     * @throws IllegalArgumentException 如果类型不匹配或不支持的类型。
     */
    inline fun <reified T1 : Number, reified T2 : Number> less(a: T1, b: T2): Boolean {
      if (T1::class != T2::class) {
        throw IllegalArgumentException("Types do not match: ${T1::class} and ${T2::class}")
      }
      return when (a) {
        is Short -> a < b.toShort()
        is Int -> a < b.toInt()
        is Long -> a < b.toLong()
        is Float -> a < b.toFloat()
        is Double -> a < b.toDouble()
        else -> throw IllegalArgumentException("Unsupported number type: ${T1::class}")
      }
    }

    /**
     * 大于运算。
     *
     * @param T1 第一个操作数的类型。
     * @param T2 第二个操作数的类型。
     * @param a 第一个操作数。
     * @param b 第二个操作数。
     * @return 两个操作数大于的结果。
     * @throws IllegalArgumentException 如果类型不匹配或不支持的类型。
     */
    inline fun <reified T1 : Number, reified T2 : Number> greater(a: T1, b: T2): Boolean {
      if (T1::class != T2::class) {
        throw IllegalArgumentException("Types do not match: ${T1::class} and ${T2::class}")
      }
      return when (a) {
        is Short -> a > b.toShort()
        is Int -> a > b.toInt()
        is Long -> a > b.toLong()
        is Float -> a > b.toFloat()
        is Double -> a > b.toDouble()
        else -> throw IllegalArgumentException("Unsupported number type: ${T1::class}")
      }
    }

    /**
     * 小于等于运算。
     *
     * @param T1 第一个操作数的类型。
     * @param T2 第二个操作数的类型。
     * @param a 第一个操作数。
     * @param b 第二个操作数。
     * @return 两个操作数小于等于的结果。
     * @throws IllegalArgumentException 如果类型不匹配或不支持的类型。
     */
    inline fun <reified T1 : Number, reified T2 : Number> lessEqual(a: T1, b: T2): Boolean {
      if (T1::class != T2::class) {
        throw IllegalArgumentException("Types do not match: ${T1::class} and ${T2::class}")
      }
      return when (a) {
        is Short -> a <= b.toShort()
        is Int -> a <= b.toInt()
        is Long -> a <= b.toLong()
        is Float -> a <= b.toFloat()
        is Double -> a <= b.toDouble()
        else -> throw IllegalArgumentException("Unsupported number type: ${T1::class}")
      }
    }

    /**
     * 大于等于运算。
     *
     * @param T1 第一个操作数的类型。
     * @param T2 第二个操作数的类型。
     * @param a 第一个操作数。
     * @param b 第二个操作数。
     * @return 两个操作数大于等于的结果。
     * @throws IllegalArgumentException 如果类型不匹配或不支持的类型。
     */
    inline fun <reified T1 : Number, reified T2 : Number> greaterEqual(a: T1, b: T2): Boolean {
      if (T1::class != T2::class) {
        throw IllegalArgumentException("Types do not match: ${T1::class} and ${T2::class}")
      }
      return when (a) {
        is Short -> a >= b.toShort()
        is Int -> a >= b.toInt()
        is Long -> a >= b.toLong()
        is Float -> a >= b.toFloat()
        is Double -> a >= b.toDouble()
        else -> throw IllegalArgumentException("Unsupported number type: ${T1::class}")
      }
    }
  }
}