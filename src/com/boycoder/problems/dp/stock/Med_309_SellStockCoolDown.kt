package com.boycoder.problems.dp.stock

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/16
 * @desc: https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 */
object Med_309_SellStockCoolDown {
    fun maxProfit(array: IntArray): Int {
        return max(array)
    }

    /**
     * dp[i][j]
     *
     * dp[i][0] means hold stock
     * dp[i][1] means not hold stock, and after cool down
     * dp[i][2] means not hold stock, today sell stock
     * dp[i][3] means not hold stock, today is cool down day
     *
     * from state 1 and 3
     * dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1] - price[i], dp[i - 1][3] - price[i]))
     * from itself or 3
     * dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][3])
     * from state 0
     * dp[i][2] = dp[i - 1][0] + price[i]
     * from state 2
     * dp[i][3] = dp[i - 1][2]
     */
    private fun max(price: IntArray): Int {
        val dp = Array(price.size) { IntArray(4) }

        // init
        dp[0][0] = -price[0]
        dp[0][1] = 0
        dp[0][2] = 0
        dp[0][3] = 0

        for (i in 1 until price.size) {
            dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1] - price[i], dp[i - 1][3] - price[i]))
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][3])
            dp[i][2] = dp[i - 1][0] + price[i]
            dp[i][3] = dp[i - 1][2]
        }

        return Math.max(dp[price.size - 1][1], Math.max(dp[price.size - 1][2], dp[price.size - 1][3]))
    }

}

fun main() {
    val res = Med_309_SellStockCoolDown.maxProfit(intArrayOf(1,2,3,0,2))
    asserts(res, 3)
}