package com.boycoder.problems.dp.stock

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/16
 * @desc:
 */
object Med_714_SellStockFee {
    /**
     * dp[i][j]
     *
     * dp[i][0] means hold stock
     * dp[i][1] means not hold stock
     */
    fun maxProfit(prices: IntArray, fee: Int): Int {
        val dp = Array(prices.size) { IntArray(2) }

        // init
        dp[0][0] = -prices[0]
        dp[0][1] = 0

        for (i in 1 until prices.size) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i])
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i] - fee)
        }

        // careful here
        return Math.max(dp[prices.size - 1][0], dp[prices.size - 1][1])
    }
}

fun main() {
    val res = Med_714_SellStockFee.maxProfit(intArrayOf(1, 3, 2, 8, 4, 9), 2)
    asserts(res, 8)
}