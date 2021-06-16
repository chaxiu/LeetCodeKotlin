package com.boycoder.problems.dp.knapsack

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/3
 * @desc: Knapsack problems
 * https://leetcode-cn.com/problems/partition-equal-subset-sum/
 *
 *      For some stuff, weigh[i], value[j], capacity = n
 *      dp[i][j] = max (dp[i - 1][j], dp[i - 1][j - weigh[i]] + value[i])
 */
object Med_416_SubSetSum {
    private var sum = 0
    private var numSum = 0
    private val map = hashMapOf<String, Boolean>()
    private var array = intArrayOf()


    fun canPartition(nums: IntArray): Boolean {
        this.array = nums
        nums.sort()
        numSum = nums.sumBy{it}
        if (numSum % 2 != 0) {
            return false
        }
//        return dfs1(0, 0)
        return canPart(array.size - 1, numSum / 2)
    }

    /**
     * dp
     *
     * dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - array[i]] + array[i])
     *
     * In this case, the result of row i only depends on row i -1.
     * So we can try to compress the dp array from two dimensions into one dimension
     * Try to reuse the i row, so we only keep j dimension
     *
     * dp[j] = Math.max(dp[j], dp[j - array[i]] + array[i]
     *
     * Attention!!!
     *
     * We still loop the i dimension, but we only use one dimension array.
     * eg: [1,5,11,5], sum/2 = 11
     *    i
     *    |        0 1 2 3 4 5 6 7 8 9 10 11  <--j
     * (1)row 0    0 1 1 1 1 1 1 1 1 1  1  1  -> here we need to reuse the same array
     * (5)row 1                            6 max(dp[11], dp[11 - 5] + 5)
     * (5)row 1                         6  6 max(dp[10], dp[10 - 5] + 5)
     * (5)row 1    0 1 1 1 1 5 6 6 6 6  6  6
     *
     * Every time we loop j, we need to from end to start.
     *
     * The advantage of dp is:
     * When capacity is not very big, space complexity is not big.
     * Some time capacity is very big, and array.size is not big, we should use back tracking
     */
    private fun canPart(num: Int, capacity: Int): Boolean {
        val dp = IntArray(capacity + 1)

        // init, we take i = 0, loop j
        for (j in 0..capacity) {
            // if capacity is ok
            if (j >= array[0]) {
                // fill the Knapsack with value
                dp[j] = array[0]
            }
        }

        // careful, start from 1
        for (i in 1..num) {
            for (j in capacity downTo 0) {
                if (j >= array[i]) {
                    dp[j] = Math.max(dp[j], dp[j - array[i]] + array[i])
                }
//                else {
//                    dp[j] = dp[j]
//                }
            }
        }

        return dp[capacity] == capacity
    }

    /**
     * dp
     * dp[i][j] means, for the 0..i elements, with j weigh Knapsack, we can choose the max value.
     * In this case, we need to check dp[n][sum/2] == sum/2
     *
     * m means the array.size
     * n means capacity
     */
    private fun canPart1(m: Int, n: Int): Boolean {
        val dp = Array(m + 1){ IntArray(n + 1) }

        // init when capacity = 0
        for (i in 0..m) {
            dp[i][0] = 0
        }

        // init when only array[0]
        for (j in 0..n) {
            if (j >= array[0]) {
                dp[0][j] = array[0]
            }
        }

        for (i in 1..m) {
            for (j in 1..n) {
                if (j < array[i]) {
                    dp[i][j] = dp[i - 1][j]
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - array[i]] + array[i])
                }
            }
        }

        return dp[m][n] == numSum / 2
    }

    // dfs + cache
    private fun dfs1(sum: Int, start: Int): Boolean {
        if (sum * 2 == numSum) {
            return true
        }

        if (sum * 2 > numSum) {
            return false
        }

        if (start >= array.size) {
            return false
        }

        val key = "${sum}.$start"
        if (map.containsKey(key)) {
            return map.getOrDefault(key, false)
        }

        val res = dfs1(sum, start + 1) || dfs1(sum + array[start], start + 1)
        map[key] = res
        return res
    }

    // This is another way to generate combination
    // out of time
    private fun dfs(sum: Int, start: Int): Boolean {
        if (sum * 2 == numSum) {
            return true
        }

        if (sum * 2 > numSum) {
            return false
        }

        if (start >= array.size) {
            return false
        }

        // choose or not choose
        val res = dfs(sum, start + 1) || dfs(sum + array[start], start + 1)
        return res
    }
}

fun main() {
    val res = Med_416_SubSetSum.canPartition(intArrayOf(1,2,5))
    asserts(res, true)
}