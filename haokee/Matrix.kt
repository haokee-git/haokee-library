package haokee

/**
 * 表示一个矩阵的类，支持基本的矩阵运算。
 *
 * @param T 数字类型，必须是 Number 的子类。
 * @property row 矩阵的行数。
 * @property col 矩阵的列数。
 */
class Matrix<T : Number>(private val row: Int, private val col: Int) {
  private val a = Array(row) { Array<Number?>(col) { null } }

  /**
   * 创建当前矩阵的副本。
   *
   * @return 矩阵的副本。
   */
  fun copyOf(): Matrix<T> {
    val result = Matrix<T>(row, col)
    for (i in 0..<row) {
      for (j in 0..<col) {
        result[i, j] = this[i, j]
      }
    }
    return result
  }

  /**
   * 获取矩阵的行数。
   *
   * @return 矩阵的行数。
   */
  fun getRow() = row

  /**
   * 获取矩阵的列数。
   *
   * @return 矩阵的列数。
   */
  fun getCol() = col

  /**
   * 获取指定行的元素。
   *
   * @param index 行索引。
   * @return 指定行的元素数组。
   */
  fun getRow(index: Int) = a[index]

  /**
   * 获取指定列的元素。
   *
   * @param index 列索引。
   * @return 指定列的元素数组。
   */
  fun getCol(index: Int): Array<Number?> {
    val result = arrayOfNulls<Number>(row)
    for (i in 0..<row) {
      result[i] = get(i, index)
    }
    return result
  }

  /**
   * 获取指定位置的元素。
   *
   * @param i 行索引。
   * @param j 列索引。
   * @return 指定位置的元素。
   */
  operator fun get(i: Int, j: Int) = a[i][j]

  /**
   * 设置指定位置的元素。
   *
   * @param i 行索引。
   * @param j 列索引。
   * @param value 要设置的值。
   */
  operator fun set(i: Int, j: Int, value: Number?) {
    a[i][j] = value
  }

  /**
   * 矩阵加法运算。
   *
   * @param other 另一个矩阵。
   * @return 两个矩阵相加的结果。
   */
  operator fun plus(other: Matrix<T>): Matrix<T> {
    assert(row == other.row && col == other.col)
    val result = Matrix<T>(row, col)
    for (i in 0..<row) {
      for (j in 0..<col) {
        result[i, j] = plus(this[i, j]!!, other[i, j]!!)
      }
    }
    return result
  }

  /**
   * 矩阵减法运算。
   *
   * @param other 另一个矩阵。
   * @return 两个矩阵相减的结果。
   */
  operator fun minus(other: Matrix<T>): Matrix<T> {
    assert(row == other.row && col == other.col)
    val result = Matrix<T>(row, col)
    for (i in 0..<row) {
      for (j in 0..<col) {
        result[i, j] = minus(this[i, j]!!, other[i, j]!!)
      }
    }
    return result
  }

  /**
   * 矩阵乘法运算。
   *
   * @param other 另一个矩阵。
   * @return 两个矩阵相乘的结果。
   */
  operator fun times(other: Matrix<T>): Matrix<T> {
    assert(col == other.row)
    val result = Matrix<T>(row, other.col)
    for (i in 0..<row) {
      for (j in 0..<other.col) {
        for (k in 0..<col) {
          result[i, j] = if (result[i, j] == null) {
            times(this[i, k]!!, other[k, j]!!)
          } else {
            plus(result[i, j]!!, times(this[i, k]!!, other[k, j]!!))
          }
        }
      }
    }
    return result
  }

  /**
   * 矩阵取模运算。
   *
   * @param value 模值。
   * @return 矩阵取模的结果。
   */
  operator fun rem(value: Number?): Matrix<T> {
    val result = Matrix<T>(row, col)
    for (i in 0..<row) {
      for (j in 0..<col) {
        result[i, j] = rem(this[i, j]!!, value!!)
      }
    }
    return result
  }
}

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
private inline fun <reified T1 : Number, reified T2 : Number> plus(a: T1, b: T2): Number {
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
private inline fun <reified T1 : Number, reified T2 : Number> minus(a: T1, b: T2): Number {
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
private inline fun <reified T1 : Number, reified T2 : Number> times(a: T1, b: T2): Number {
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
private inline fun <reified T1 : Number, reified T2 : Number> rem(a: T1, b: T2): Number {
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
 * 创建一个矩阵，并使用给定的因子函数初始化。
 *
 * @param T 数字类型，必须是 Number 的子类。
 * @param row 矩阵的行数。
 * @param col 矩阵的列数。
 * @param factor 因子函数，用于初始化矩阵的元素。
 * @return 初始化后的矩阵。
 */
inline fun <reified T : Number> matrixOf(row: Int, col: Int, factor: (Int, Int) -> T): Matrix<T> {
  val result = Matrix<T>(row, col)
  for (i in 0..<row) {
    for (j in 0..<col) {
      result[i, j] = factor(i, j)
    }
  }
  return result
}