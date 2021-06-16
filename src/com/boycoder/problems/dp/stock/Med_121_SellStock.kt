package com.boycoder.problems.dp.stock

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/16
 * @desc:
 */
object Med_121_SellStock {
    fun maxProfit(array: IntArray): Int {
        return max3(array)
    }

    /**
     * dp
     *
     * dp[i][0] means hold stock the max profit
     * dp[i][1] means sell stock the max profit
     *
     * dp[i][0] = max(dp[i - 1][0], -price[i])
     * dp[i][1] = max(dp[1 - 1][1], dp[i - 1][0] + price[i])
     */
    private fun max3(price: IntArray): Int {
        val dp = Array(price.size) { IntArray(2) }

        // first day to hold stock
        dp[0][0] = -price[0]

        // first day to not hold stock
        dp[0][1] = 0

        for (i in 1 until price.size) {
            dp[i][0] = Math.max(dp[i - 1][0], -price[i])
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + price[i])
        }

        // max profit must sell stock
        return dp[price.size - 1][1]
    }

    /**
     * dfs
     * Try to break the array into smaller array.
     * When array.size = 1, low = array[0], and profit = 0.
     * Use res[0] for lowest price, and res[1] for the max profit
     */
    private fun max2(array: IntArray, end: Int): IntArray {
        if (end == 0) return intArrayOf(array[0], 0)

        val res = max2(array, end - 1)
        val max = Math.max(res[1], array[end] - res[0])
        val low = Math.min(array[end], res[0])

        return intArrayOf(low, max)
    }

    /**
     * Greedy
     * There is no need to loop the array two times.
     */
    private fun max1(array: IntArray): Int {
        var low = Int.MAX_VALUE
        var max = 0

        for (i in array) {
            low = Math.min(low, i)
            max = Math.max(max, i - low)
        }

        return max
    }

    /**
     * Brute Force
     * Two loop, buy loop the [0, size)
     * sell loop [i + 1, size)
     * Then we find the max profit
     */
    private fun max(array: IntArray): Int {
        var max = 0

        for (buy in 0 until array.size) {
            for (sell in buy + 1 until array.size) {
                if (array[sell] - array[buy] > max) {
                    max = array[sell] - array[buy]
                }
            }
        }

        return max
    }
}

fun main() {
    val res = Med_121_SellStock.maxProfit(intArrayOf(7, 1, 5, 3, 6, 4))
    asserts(res, 5)
    val res1 = Med_121_SellStock.maxProfit(intArrayOf(7,6,4,3,1))
    asserts(res1, 0)
}