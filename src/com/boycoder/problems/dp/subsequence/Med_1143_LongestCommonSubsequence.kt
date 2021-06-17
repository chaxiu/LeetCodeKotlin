package com.boycoder.problems.dp.subsequence

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/17
 * @desc: https://leetcode-cn.com/problems/longest-common-subsequence/
 * This problem is similar to [Med_718_LongestSubArray]
 */
object Med_1143_LongestCommonSubsequence {

    private var array1 = charArrayOf()
    private var array2 = charArrayOf()
    private var memo = hashMapOf<String, Int>()

    /**
     * dp[i][j] means text1[0, i) text2[0, j) LCS size
     *
     * if (text1[i] == text2[j]) dp[i][j] = dp[i - 1][j - 1] + 1 else Math.max(dp[i][j - 1], dp[i - 1][j])
     */
    fun longestCommonSubsequence(text1: String, text2: String): Int {
        array1 = text1.toCharArray()
        array2 = text2.toCharArray()
        memo.clear()
        return find2(array1.size - 1, array2.size - 1)
    }


    /**
     * dp
     *
     * bottom to top
     *
     * As we can see, sometimes dp is straight forward
     */
    private fun find1(text1: String, text2: String): Int {
        val size1 = text1.length
        val size2 = text2.length

        // dp with init
        val dp = Array(size1 + 1) { IntArray(size2 + 1) {0} }

        for (i in 1..size1) {
            for (j in 1..size2) {
                if (array1[i - 1] == array2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j])
                }
            }
        }

        return dp[size1][size2]
    }

    /**
     * dfs + cache
     */
    private fun find2(end1: Int, end2: Int): Int {
        val key = "${end1}.${end2}"

        if (memo.containsKey(key)) {
            return memo.getOrDefault(key, 0)
        }

        var res = 0

        if (end1 == 0 && end2 == 0) {
            res =  if (array1[0] == array2[0]) 1 else 0
        } else if (end1 == 0 && end2 > 0) {

            for (j in 0..end2) {
                if (array2[j] == array1[0]) {
                    res =  1
                }
            }
        } else if (end2 == 0 && end1 > 0) {
            for (i in 0..end1) {
                if (array1[i] == array2[0]) {
                    res = 1
                }
            }
        } else if (end1 < 0 || end2 < 0) {
            return 0
        } else {
            // end1 > 0 && end2 > 0
            if (array1[end1] == array2[end2]) {
                res = find2(end1 -1, end2 -1) + 1
            } else {
                res = Math.max(find2(end1, end2 - 1), find2(end1 - 1, end2))
            }
        }

        memo[key] = res
        return res
    }

    /**
     * dfs
     *
     * top to bottom
     *
     * Try to break it into smaller problems
     * We need to find [0, end1] [0, end2]
     *                 [0, end1 -1] [0, end2 -1]
     *                 [0, end1 -2] [0, end2 -2]
     *                 .
     *                 .
     *                 .
     *                 [0, 1] [0, 1]
     *                 [0, 0] [0, 0]
     */
    private fun find(end1: Int, end2: Int): Int {
        if (end1 == 0 && end2 == 0) {
            return if (array1[0] == array2[0]) 1 else 0
        } else if (end1 == 0 && end2 > 0) {
            for (j in 0..end2) {
                if (array2[j] == array1[0]) {
                    return 1
                }
            }
            return 0
        } else if (end2 == 0 && end1 > 0) {
            for (i in 0..end2) {
                if (array1[i] == array2[0]) {
                    return 1
                }
            }
            return 0
        } else if (end1 < 0 || end2 < 0) {
            return 0
        } else {
            // end1 > 0 && end2 > 0
            if (array1[end1] == array2[end2]) {
                return find(end1 -1, end2 -1) + 1
            } else {
                return Math.max(find(end1, end2 - 1), find(end1 - 1, end2))
            }
        }
    }
}

fun main() {
    val res = Med_1143_LongestCommonSubsequence.longestCommonSubsequence("abcde", "ace")
    asserts(res, 3)
    val res1 = Med_1143_LongestCommonSubsequence.longestCommonSubsequence("abc", "def")
    asserts(res1, 0)
}