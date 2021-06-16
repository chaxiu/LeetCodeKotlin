package com.boycoder.problems.dp.knapsack

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/15
 * @desc: https://leetcode-cn.com/problems/coin-change/
 * Similar to the [Med_279_PerfectSquares.find4]
 * This problem can use bfs too.
 */
object Med_322_CoinChange {
    private var min = Int.MAX_VALUE
    private var coins = intArrayOf()

    private var memo = hashMapOf<String, Int>()

    fun coinChange(coins: IntArray, amount: Int): Int {
        this.coins = coins
        val res = change3(amount)
        return if (res == Int.MAX_VALUE) -1 else res
    }

    /**
     * coins[] is value[] and weight[], and amount is capacity.
     * We need to know the min amount that fit the knapsack.
     * dp[j] means: to fill the j capacity knapsack, min amount is dp[j]
     * This is similar to [Med_377_CombinationSumIV.count3]
     */
    private fun change3(amount: Int): Int {
        val dp = IntArray(amount + 1) { Int.MAX_VALUE }
        dp[0] = 0

        // loop coins
        for (i in 0 until coins.size) {
            // loop capacity
            for (j in coins[i]..amount) {
                if (dp[j - coins[i]] != Int.MAX_VALUE) {
                    // if it's initialized
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1)
                }
            }
        }

        return dp[amount]
    }

    /**
     * dp
     * bottom to top
     */
    private fun change2(amount: Int): Int {
        val dp = IntArray(amount + 1)
        dp[0] = 0

        /**
         * this loop is equal to the recursion [change1]
         */
        for (i in 1..amount) {
            // This loop will count dp[1]/dp[2]/dp[3]...dp[amount]
            var min = Int.MAX_VALUE
            for (j in 0 until coins.size) {
                if (i >= coins[j] && dp[i - coins[j]] < min) {
                    min = dp[i - coins[j]] + 1
                }
            }
            dp[i] = min
        }

        return dp[amount]
    }

    /**
     * dfs + cache
     * For every coin, we can choose more than one time.
     */
    private fun change1(amount: Int): Int {
        if (amount < 0) {
            return Int.MAX_VALUE
        }

        if (amount == 0) {
            return 0
        }

        val key = "${amount}"
        if (memo.containsKey(key)) {
            return memo.getOrDefault(key, Int.MAX_VALUE)
        }

        var min = Int.MAX_VALUE
        for (i in 0 until coins.size) {
            var res = change1(amount - coins[i])
            if (res != Int.MAX_VALUE) {
                res++
            }
            min = Math.min(min, res)
        }

        memo[key] = min
        return min
    }

    /**
     * For every coin, we can choose more than one time.
     */
    private fun change(size: Int, amount: Int) {
        if (amount == 0) {
            if (size < min) {
                min = size
            }
            return
        }

        if (amount < 0) {
            return
        }

        for (i in 0 until coins.size) {
            change(size + 1, amount - coins[i])
        }
    }
}

fun main() {
    val res = Med_322_CoinChange.coinChange(intArrayOf(1, 2, 5), 11)
    asserts(res, 3)
}