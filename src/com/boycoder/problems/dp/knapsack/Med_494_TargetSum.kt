package com.boycoder.problems.dp.knapsack

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/5
 * @desc: https://leetcode-cn.com/problems/target-sum/
 *      We need to divide the array into two part, like left and right
 *      left to add, right to subtract, result == target.
 *      left - right = target
 *      left - (sum - left) = target
 *      left = (target + sum)/2
 * So this problem become a 0-1 Knapsack Problem, and left is bag capacity.
 * We need to count how many combination can fill the bag exactly.
 *
 * dp[i][j] = dp[i - 1][j] + dp[i - 1][j - weight[i]]
 *
 * combination: dp[i - 1][j] mean:
 * We dont need weight[i], we already filled the j capacity.
 *
 * combination: dp[i - 1][j - weight[i]] means:
 * We need weight[i], so the combination count is dp[i - 1][j - weight[i]]
 */
object Med_494_TargetSum {
    private var array = intArrayOf()
    private var target = 0
    private var count = 0
    private var memo = hashMapOf<String, Int>()

    private var sum = 0
    private var capacity = 0
    fun findTargetSumWays(nums: IntArray, target: Int): Int {
        array = nums
        this.target = target
        sum = nums.sumBy{it}
        capacity = (target + sum) / 2
        count = find3(array.size - 1, capacity)
        return count
    }

    /**
     * dp with optimize
     */
    private fun find(num: Int, capacity: Int): Int {
        val dp = IntArray(capacity + 1)
        dp[0] = 1

        for (i in 0..num) {
            // from end to start
            // Every element pick only once
            for (j in capacity downTo array[i]) {
                dp[j] = dp[j] + dp[j - array[i]]
            }
        }

        return dp[capacity]
    }

    /**
     *    0     1
     * 0  2     0
     * 0  4     0
     * 0  8     0
     * 0  16    0
     * 0  32    0
     * 0  64    0
     * 0  128   0
     * 0  256   0
     * 1  256   256
     */
    private fun find3(num: Int, capacity: Int): Int {
        val dp = Array(num + 1){ IntArray(capacity + 1) }

        // init j = 0
        for (i in 0..num) {
            dp[i][0] = 1
        }

        for (j in 0..capacity) {
            if (array[0] == 0) {
                // choose array[0] or not to choose
                dp[0][0] = 2
            } else if (array[0] == j) {
                dp[0][j] = 1
            }
        }

        for (i in 1..num) {
            for (j in 0..capacity) {
                if (j >= array[i]) {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - array[i]]
                } else {
                    dp[i][j] = dp[i - 1][j]
                }
            }
        }

        return dp[num][capacity]
    }

    /**
     * back tracking + cache
     * To cache the result, we need to return the count.
     * key = "${cur}.$start"
     * We can still optimize the key into Int, and then use array as memo.
     */
    private fun find2(cur: Int, start: Int): Int {
        if (cur == target && start == array.size) {
            return 1
        }

        if (start >= array.size) {
            return 0
        }

        val key = "${cur}.$start"
        if (memo.containsKey(key)) {
            return memo.getOrDefault(key, 0)
        }
        // for the same index, try add/subtract
        val res = find2(cur + array[start], start + 1) + find2(cur - array[start], start + 1)
        memo[key] = res
        return res
    }

    /**
     * back tracking
     */
    private fun find1(cur: Int, start: Int) {
        if (cur == target && start == array.size) {
            count++
        }

        if (start >= array.size) {
            return
        }

        // for the same index, try add/subtract
        find1(cur + array[start], start + 1)
        find1(cur - array[start], start + 1)
    }
}

fun main() {
    val res = Med_494_TargetSum.findTargetSumWays(intArrayOf(0,0,0,0,0,0,0,0,1), 1)
    asserts(res, 256)
}