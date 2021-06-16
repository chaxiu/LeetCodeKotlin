package com.boycoder.problems.dp.stock

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/16
 * @desc: https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
 */
object Med_122_SellStockII {
    fun maxProfit(array: IntArray): Int {
        return max1(array)
    }

    /**
     * dp
     *
     * dp[i][0] means hold stock, the max profit
     * dp[i][1] means not hold stock, the max profit
     */
    private fun max1(price: IntArray): Int {
        val dp = Array(price.size) {IntArray(2)}

        // first day hold stock
        dp[0][0] = -price[0]
        // first day not hold stock
        dp[0][1] = 0

        for (i in 1 until price.size) {
            // yesterday already hold or yesterday not hold, but buy today
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - price[i])
            // yesterday already not hold or yesterday hold, but today sell
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + price[i])
        }

        return dp[price.size - 1][1]
    }

    /**
     * Greedy
     * Collect all the raising range
     */
    private fun max(price: IntArray): Int {
        var max = 0
        for (i in 1 until price.size) {
            if (price[i] > price[i - 1]) {
                max = max + price[i] - price[i - 1]
            }
        }

        return max
    }
}

fun main() {
    val res = Med_122_SellStockII.maxProfit(intArrayOf(7, 1, 5, 3, 6, 4))
    asserts(res, 7)
    val res1 = Med_122_SellStockII.maxProfit(intArrayOf(1,2,3,4,5))
    asserts(res1, 4)
    val res2 = Med_122_SellStockII.maxProfit(intArrayOf(7,6,4,3,1))
    asserts(res2, 0)
}

