package com.boycoder.problems.dp

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/3
 * @desc:
 */
object Easy_509_Fibonacci {
    private val cache = IntArray(31)

    fun fib(n: Int): Int {
        return count(n)
    }

    // dp + space optimize
    // a little bit like slide window
    private fun count(n: Int): Int {
        if (n == 0) return 0
        if (n == 1 || n == 2) return 1

        var dp1 = 1
        var dp2 = 1

        for (i in 3..n) {
            val temp = dp1 + dp2
            dp1 = dp2
            dp2 = temp
        }

        return dp2
    }

    // dp
    private fun count3(n: Int): Int {
        if (n == 0) return 0
        if (n == 1 || n == 2) return 1

        val dp = IntArray(31)
        dp[1] = 1
        dp[2] = 1

        for (i in 3..n) {
            dp[i] = dp[i - 1] + dp[i - 2]
        }

        return dp[n]
    }

    // recursion + cache
    private fun count2(n: Int): Int {
        if (n == 0) return 0
        if (n == 1 || n == 2) return 1

        if (cache[n] != 0) {
            return cache[n]
        }
        val v = count(n - 1) + count(n - 2)
        cache[n] = v
        return v
    }

    // recursion
    private fun count1(n: Int): Int {
        if (n == 0) return 0
        if (n == 1 || n == 2) return 1
        return count(n - 1) + count(n - 2)
    }
}

fun main() {
    val res = Easy_509_Fibonacci.fib(4)
    asserts(res, 4)
    println(res)

}