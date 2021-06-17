package com.boycoder.problems.dp.subsequence

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/16
 * @desc: https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence/
 *
 */
object Med_674_LongestIncreasingSubsequenceII {

    fun longestSize(array: IntArray): Int {
        return find1(array)
    }


    /**
     * dp
     *
     * This problem can be break into smaller problems.
     * dp[i] means, the size of LIS that end with array[i]
     * if (array[i + 1] > array[i]) dp[i + 1] = dp[i] + 1
     *
     * For the longest subsequence could end with any index
     * so we need to store the max value of dp[i]
     */
    private fun find(array: IntArray): Int {
        // init every dp with 1
        val dp = IntArray(array.size) { 1 }

        var max = 1

        for (i in 1 until array.size) {
            if (array[i] > array[i - 1]) {
                dp[i] = dp[i - 1] + 1
            }

            max = Math.max(max, dp[i])
        }

        return max
    }

    /**
     * Greedy
     *
     * Use a loop go through the array.
     * count = 1
     * count = if (array[i] > array[i - 1]) count++ else 1
     * mark the max value
     */
    private fun find1(array: IntArray): Int {
        var count = 1
        var max = 1

        for (i in 1 until array.size) {
            if (array[i] > array[i - 1]) {
                count++
            } else {
                count = 1
            }

            max = Math.max(count, max)
        }

        return max
    }

    /**
     * Brute Force + pruning
     * We assume the longest subsequence should start from i
     * And we loop j [i + 1, size), if (array[i] > array[i - 1]) count++ else break
     */
    private fun find2(array: IntArray): Int {
        var max = 0

        for (i in 0 until array.size) {
            var count = 1
            for (j in i + 1 until array.size) {
                if (array[j] > array[j - 1]) {
                    count++
                } else {
                    break
                }
            }
            max = Math.max(count, max)
        }
        return max
    }
}

fun main() {
    val res = Med_674_LongestIncreasingSubsequenceII.longestSize(intArrayOf(1, 3, 5, 4, 7))
    asserts(res, 3)
    val res1 = Med_674_LongestIncreasingSubsequenceII.longestSize(intArrayOf(2, 2, 2, 2, 2))
    asserts(res1, 1)
}