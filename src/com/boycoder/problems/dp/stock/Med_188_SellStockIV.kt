package com.boycoder.problems.dp.stock

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/16
 * @desc: https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/
 * This problem is based on [Med_123_SellStockIII]
 */
object Med_188_SellStockIV {
    fun maxProfit(k: Int, array: IntArray): Int {
        return max(k, array)
    }

    /**
     * dp[i][2 * k + 1]
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
    private fun max(k: Int, price: IntArray): Int {
        val dp = Array(price.size) { IntArray(2 * k + 1) }

        // init
        for (i in 0..2 * k) {
            when {
                i == 0 -> dp[0][i] = 0
                i % 2 == 1 -> dp[0][i] = -price[0]
                i % 2 == 0 -> dp[0][i] = 0
            }
        }

        for (i in 1 until price.size) {
            for(j in 0..2 * k) {
                when {
                    j == 0 -> dp[i][j] = 0
                    j % 2 == 1 -> dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] - price[i])
                    j % 2 == 0 -> dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] + price[i])
                }
            }
        }

        return dp[price.size - 1][2*k]
    }

}

fun main() {
    val res = Med_188_SellStockIV.maxProfit(2, intArrayOf(2, 4, 1))
    asserts(res, 2)
    val res1 = Med_188_SellStockIV.maxProfit(2, intArrayOf(3, 2, 6, 5, 0, 3))
    asserts(res1, 7)
}