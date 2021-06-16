package com.boycoder.problems.dp.stock

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/16
 * @desc: https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/
 */
object Med_123_SellStockIII {
    fun maxProfit(array: IntArray): Int {
        return max(array)
    }

    /**
     * dp
     *
     * dp[i][0] means no operation
     * dp[i][1] means first time buy
     * dp[i][2] means first time sell
     * dp[i][3] means second time buy
     * dp[i][4] means second time sell
     *
     * dp[i][0] = 0
     * dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - price[i])
     * dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + price[i])
     * dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - price[i])
     * dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + price[i])
     */
    private fun max(price: IntArray): Int {
        val dp = Array(price.size) { IntArray(5) }

        // init
        dp[0][0] = 0
        dp[0][1] = -price[0]
        dp[0][2] = 0
        dp[0][3] = -price[0]
        dp[0][4] = 0

        for (i in 1 until price.size) {
            dp[i][0] = 0
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - price[i])
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + price[i])
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - price[i])
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + price[i])
        }

        return dp[price.size - 1][4]
    }
}

fun main() {
    val res = Med_123_SellStockIII.maxProfit(intArrayOf(3, 3, 5, 0, 0, 3, 1, 4))
    asserts(res, 6)
    val res1 = Med_123_SellStockIII.maxProfit(intArrayOf(1, 2, 3, 4, 5))
    asserts(res1, 4)
    val res2 = Med_123_SellStockIII.maxProfit(intArrayOf(7, 6, 4, 3, 1))
    asserts(res2, 0)
}