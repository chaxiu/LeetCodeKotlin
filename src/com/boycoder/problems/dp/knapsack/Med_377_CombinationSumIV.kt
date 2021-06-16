package com.boycoder.problems.dp.knapsack

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/15
 * @desc: https://leetcode-cn.com/problems/combination-sum-iv/
 */
object Med_377_CombinationSumIV {
    var array = intArrayOf()
    var target = 0

    var count = 0
    var memo = hashMapOf<Int, Int>()

    fun combinationSum4(nums: IntArray, target: Int): Int {
        this.array = nums
        this.target = target
        this.count = 0
        return count3()
    }

    /**
     * dp[i] means, to get target i, we can get dp[i] permutations
     */
    private fun count3(): Int {
        val dp = IntArray(target + 1)
        dp[0] = 1

        /**
         * To get permutations count, we loop capacity then loop value
         * This is different from [Med_518_CoinChangeII.find5]
         */
        for (i in 1..target) {
            for (j in 0 until array.size) {
                if (i >= array[j])
                dp[i] = dp[i] + dp[i - array[j]]
            }
        }

        return dp[target]
    }

    /**
     * dfs + cache
     */
    private fun count2(target: Int): Int {
        if (target == 0) return 1

        if (memo.containsKey(target)) {
            return memo.getOrDefault(target, 0)
        }

        var res = 0

        /**
         * To get the permutations count.
         * Every time, we loop the whole array.
         */
        for (num in array) {
            if (target >= num) {
                res = res + count2(target - num)
            }
        }
        memo[target] = res

        return res
    }

    /**
     * dfs
     */
    private fun count1(target: Int): Int {
        if (target == 0) return 1
        var res = 0

        /**
         * To get the permutations count.
         * Every time, we loop the whole array.
         */
        for (num in array) {
            if (target >= num) {
                res = res + count1(target - num)
            }
        }
        return res
    }

    /**
     * dfs that can get all the permutations
     * If we need the permutations, we can use a path to store.
     */
    private fun count(target: Int) {
        if (target < 0) return
        if (target == 0) {
            count++
            return
        }

        for (i in 0 until array.size) {

            count(target - array[i])
        }
    }
}

fun main() {
    val res = Med_377_CombinationSumIV.combinationSum4(intArrayOf(1, 2, 3), 4)
    asserts(res, 7)
    val res1 = Med_377_CombinationSumIV.combinationSum4(intArrayOf(9), 3)
    asserts(res1, 0)
}