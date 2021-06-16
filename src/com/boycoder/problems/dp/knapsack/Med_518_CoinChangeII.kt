package com.boycoder.problems.dp.knapsack

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/14
 * @desc:
 */
object Med_518_CoinChangeII {
    var sum = 0
    var count = 0
    var amount = 0
    var coins = intArrayOf()

    var memo = hashMapOf<String, Int>()

    fun change(amount: Int, coins: IntArray): Int {
        this.sum = 0
        this.count = 0
        this.amount = amount
        this.coins = coins
        memo.clear()
//        find(0)
//        return find1(amount, coins.size - 1)
        return find5()
    }

    /**
     * dp
     * For dp[i][j], we only need the result of dp[i - 1][j]
     * So we can optimize the space complexity
     */
    private fun find5(): Int {
        val dp = Array(amount + 1) { 0 }

        // init when j = 0, it means amount = 0
        dp[0] = 1

        for (i in 1..coins.size) {
            val value = coins[i - 1]
            /**
             * Because the two dimension we need to use:
             * while (value * count <= j) {
             *    dp[i][j] = dp[i][j] + dp[i - 1][j - (value * count)]
             *    count++
             * }
             *
             * So, in one dimension array, we need:
             * From start to end
             * Every element pick more than once
             * That's the difference between [Med_494_TargetSum.find]
             */
            for (j in value..amount) {
                // automatically reused
//                dp[i][j] = dp[i - 1][j]
                dp[j] = dp[j] + dp[j - value]
            }
        }

        return dp[amount]
    }

    /**
     * dp
     * bottom to top
     * We choose i coins, to get amount, we got dp[i][amount] count combinations
     */
    private fun find4(): Int {
        val dp = Array(coins.size + 1) { IntArray(amount + 1) { 0 } }

        // init when j = 0, it means amount = 0
        dp[0][0] = 1
        for (i in 1..coins.size) {
            dp[i][0] = 0
        }

        for (i in 1..coins.size) {
            val value = coins[i - 1]
            for (j in 0..amount) {
                dp[i][j] = dp[i - 1][j]
                var count = 1
                while (value * count <= j) {
                    dp[i][j] = dp[i][j] + dp[i - 1][j - (value * count)]
                    count++
                }
            }
        }

        return dp[coins.size][amount]

    }

    /**
     * dfs + cache
     */
    private fun find3(cur: Int, index: Int): Int {
        if (cur == amount) {
            return 1
        }

        if (index > coins.size - 1) {
            return 0
        }

        val key = "${cur}.${index}"
        if (memo.containsKey(key)) {
            return memo.getOrDefault(key, 0)
        }

        var count = 0
        var total = 0

        while (cur + coins[index] * count <= amount) {
            total = total + find2(cur + coins[index] * count, index + 1)
            count++
        }
        memo[key] = total

        return total
    }

    /**
     * From start to end
     */
    private fun find2(cur: Int, index: Int): Int {
        if (cur == amount) {
            return 1
        }

        if (index > coins.size - 1) {
            return 0
        }

        var count = 0
        var total = 0

        while (cur + coins[index] * count <= amount) {
            total = total + find2(cur + coins[index] * count, index + 1)
            count++
        }

        return total
    }

    /**
     * Divide this problem into smaller problems
     * From end to start
     */
    private fun find1(remain: Int, end: Int): Int {
        if (remain == 0) {
            return 1
        }

        if (end < 0) {
            return 0
        }

        var count = 0
        var total = 0

        // For the specific coin, we can choose more than once
        while (count * coins[end] <= remain) {
            total = total + find1(remain - count * coins[end], end - 1)
            count++
        }
        return total
    }

    /**
     * similar to https://leetcode-cn.com/problems/combination-sum/
     * But we just need combination count, not the combinations
     */
    private fun find(start: Int) {
        if (sum == amount) {
            count++
            return
        }

        if (sum > amount) return

        for (i in start until coins.size) {
            sum = sum + coins[i]
            // pass i not i + 1, means we can choose the same index
            // more than once
            find(i)
            sum = sum - coins[i]
        }
    }
}

fun main() {
    val res = Med_518_CoinChangeII.change(5, intArrayOf(1, 2, 5))
    asserts(res, 4)
    val res1 = Med_518_CoinChangeII.change(10, intArrayOf(10))
    asserts(res1, 1)
}