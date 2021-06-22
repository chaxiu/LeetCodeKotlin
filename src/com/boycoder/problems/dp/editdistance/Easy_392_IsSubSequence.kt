package com.boycoder.problems.dp.editdistance

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/17
 * @desc: https://leetcode-cn.com/problems/is-subsequence/
 * 1. two pointer
 * 2. dfs
 * 3. dp
 */
object Easy_392_IsSubSequence {
    var array1 = charArrayOf()
    var array2 = charArrayOf()

    fun isSubsequence(s: String, t: String): Boolean {
        array1 = s.toCharArray()
        array2 = t.toCharArray()
        return dp()
    }

    /**
     * The basic case of Edit Distance
     *
     * dp[i][j] means the LCS length
     * Check dp[array1.size][array2.size] == array1.size
     */
    private fun dp(): Boolean {
        val dp = Array(array1.size + 1) { IntArray(array2.size + 1)}

        for (i in 1..array1.size) {
            for (j in 1..array2.size) {
                if (array1[i - 1] == array2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1
                } else {
                    /**
                     * Here is little bit tricky
                     *   |
                     * abc
                     * ahbgdc
                     *    |
                     * Because  array1[2] != array2[3]
                     * So       dp[2][3] = dp[2][2]
                     */
                    dp[i][j] = dp[i][j - 1]
                }
            }
        }

        return dp[array1.size][array2.size] == array1.size
    }

    /**
     * dp
     *
     * Bottom to top
     *
     * dp[i][j] means the result of array1[0, i-1] array2[0, j-1]
     */
    private fun dp1(): Boolean {
        val dp = Array(array1.size) { BooleanArray(array2.size) }

        // init when i = 0
        for (j in 0 until array2.size) {
            if (array2[j] == array1[0]) {
                for (k in j until array2.size) {
                    dp[0][k] = true
                }
                break
            }
        }

        // init when j = 0, by default is false

        for (i in 1 until array1.size) {
            for (j in 1 until array2.size) {
                if (dp[i][j - 1]) {
                    dp[i][j] = true
                } else {
                    if (array1[i] == array2[j]) {
                        dp[i][j] = dp[i - 1][j - 1]
                    }
                }
            }
        }

        return dp[array1.size - 1][array2.size - 1]
    }

    /**
     * dfs
     *
     * Try to break it down into smaller problems
     */
    private fun isValid1(end1: Int, end2: Int): Boolean {
        if (end1 < 0) {
            return true
        } else if (end2 < 0) {
            return false
        }

        return if (array1[end1] == array2[end2]) {
            isValid1(end1 - 1, end2 - 1)
        } else {
            isValid1(end1, end2 - 1)
        }
    }

    /**
     * check s is subsequence of t.
     */
    private fun isValid(s: String, t: String): Boolean {
        // two pointer go through two string.
        var start1 = 0
        var start2 = 0

        val array1 = s.toCharArray()
        val array2 = t.toCharArray()

        while (start1 < s.length && start2 < t.length) {
            if (array1[start1] == array2[start2]) {
                start1++
                start2++
            } else {
                start2++
            }
        }

        return start1 == s.length
    }
}

fun main() {
    val res = Easy_392_IsSubSequence.isSubsequence("abc", "ahbgdc")
    asserts(res, true)
    val res1 = Easy_392_IsSubSequence.isSubsequence("axc", "ahbgdc")
    asserts(res1, false)
}