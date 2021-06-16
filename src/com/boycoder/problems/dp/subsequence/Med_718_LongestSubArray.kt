package com.boycoder.problems.dp.subsequence

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/16
 * @desc: https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray/
 */
object Med_718_LongestSubArray {

    fun longestSize(array1: IntArray, array2: IntArray): Int {
        return find1(array1, array2)
    }

    private fun find1(array1: IntArray, array2: IntArray): Int {
        val dp = Array(array1.size) {IntArray(array2.size) { 0 } }
        var max = 0

        // init when i = 0
        for (j in 0 until array2.size) {
            if (array2[j] == array1[0]) {
                dp[0][j] = 1
            }
        }

        // init when j = 0
        for (i in 0 until array1.size) {
            if (array1[i] == array2[0]) {
                dp[i][0] = 1
            }
        }

        for (i in 1 until array1.size) {
            for (j in 1 until array2.size) {
                if (array1[i] == array2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1
                }
                max = Math.max(max, dp[i][j])
            }
        }

        return max
    }


    /**
     * dp
     *
     * dp[i][j] means array1[0, i - 1] and array2[0, j - 1] longest size
     */
    private fun find(array1: IntArray, array2: IntArray): Int {
        val dp = Array(array1.size + 1) { IntArray(array2.size + 1) { 0 } }
        var max = 0

        for (i in 1..array1.size) {
            for (j in 1..array2.size) {
                if (array1[i - 1] == array2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1
                }

                max = Math.max(max, dp[i][j])
            }
        }

        return max
    }

}

fun main() {
    val res = Med_718_LongestSubArray.longestSize(intArrayOf(1, 2, 3, 2, 1), intArrayOf(3, 2, 1, 4, 7))
    asserts(res, 3)
}