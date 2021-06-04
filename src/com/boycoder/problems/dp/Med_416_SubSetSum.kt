package com.boycoder.problems.dp

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/3
 * @desc: Knapsack problems
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
//        return dfs(0, 0)
        return canPart(array.size - 1, numSum / 2)
    }


    /**
     * dp
     * dp[i][j] means, for the 0..i elements, with j weigh Knapsack, we can choose the max value.
     * In this case, we need to check dp[n][sum/2] == sum/2
     *
     * m means the array.size
     * n means capacity
     */
    private fun canPart(m: Int, n: Int): Boolean {
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

    // This is another way to generate combination
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


        val res = dfs(sum, start + 1) || dfs(sum + array[start], start + 1)
        return res
    }
}

fun main() {
    val res = Med_416_SubSetSum.canPartition(intArrayOf(1,5,11,5))
    asserts(res, true)
}