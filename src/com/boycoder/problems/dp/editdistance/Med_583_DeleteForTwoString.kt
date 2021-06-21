package com.boycoder.problems.dp.editdistance

import com.boycoder.problems.dp.subsequence.Med_1143_LongestCommonSubsequence
import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/19
 * @desc:
 */
object Med_583_DeleteForTwoString {
    var array1 = charArrayOf()
    var array2 = charArrayOf()

    val memo = hashMapOf<String, Int>()
    fun minDistance(word1: String, word2: String): Int {
        array1 = word1.toCharArray()
        array2 = word2.toCharArray()

        return distance3()
    }

    /**
     * dp[i][j] means array1[0, i - 1] array2[0, j - 1] distance
     */
    private fun distance3(): Int {
        val dp = Array(array1.size + 1) {IntArray(array2.size + 1)}

        // init when word2 is empty string
        for (i in 0..array1.size) {
            dp[i][0] = i
        }

        // init when word1 is empty string
        for (j in 0..array2.size) {
            dp[0][j] = j
        }

        for (i in 1..array1.size) {
            for (j in 1..array2.size) {
                if (array1[i - 1] == array2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1]
                } else {
                    val res1 = dp[i - 1][j] + 1
                    val res2 = dp[i][j - 1] + 1
                    val res3 = dp[i - 1][j - 1] + 2
                    dp[i][j] = Math.min(res1, Math.min(res2, res3))
                }
            }
        }

        return dp[array1.size][array2.size]
    }

    /**
     * dfs + cache
     */
    private fun distance2(end1: Int, end2: Int): Int {
        if (end1 < 0) {
            return end2 + 1
        }

        if (end2 < 0) {
            return end1 + 1
        }

        val key = "${end1}.${end2}"
        if (memo.containsKey(key)) {
            return memo.getOrDefault(key, 0)
        }

        var result = 0

        if (array1[end1] == array2[end2]) {
            result = distance1(end1 - 1, end2 - 1)
        } else {
            val res1 = distance1(end1 - 1, end2) + 1
            val res2 = distance1(end1, end2 - 1) + 1
            val res3 = distance1(end1 - 1, end2 - 1) + 2
            result = Math.min(res1, Math.min(res2, res3))
        }

        memo[key] = result
        return result
    }

    /**
     * dfs
     */
    private fun distance1(end1: Int, end2: Int): Int {
        if (end1 < 0) {
            return end2 + 1
        }

        if (end2 < 0) {
            return end1 + 1
        }

        if (array1[end1] == array2[end2]) {
            return distance1(end1 - 1, end2 - 1)
        } else {
            val res1 = distance1(end1 - 1, end2) + 1
            val res2 = distance1(end1, end2 - 1) + 1
            val res3 = distance1(end1 - 1, end2 - 1) + 2
            return Math.min(res1, Math.min(res2, res3))
        }
    }

    /**
     * Calculate the size of LCS and return m + n - 2 * LCS
     */
    private fun distance(word1: String, word2: String): Int {
        val lcs = Med_1143_LongestCommonSubsequence.longestCommonSubsequence(word1, word2)
        return word1.length + word2.length - 2 * lcs
    }
}

fun main() {
    val res = Med_583_DeleteForTwoString.minDistance("sea", "eat")
    asserts(res, 2)
}