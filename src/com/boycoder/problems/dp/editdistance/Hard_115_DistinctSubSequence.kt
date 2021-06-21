package com.boycoder.problems.dp.editdistance

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/19
 * @desc: https://leetcode-cn.com/problems/distinct-subsequences/
 *
 * dfs
 * dp
 */
object Hard_115_DistinctSubSequence {
    private var array1 = charArrayOf()
    private var array2 = charArrayOf()

    private val memo = hashMapOf<String, Int>()
    fun numDistinct(s: String, t: String): Int {
        array1 = s.toCharArray()
        array2 = t.toCharArray()
        memo.clear()
        return count2()
    }

    /**
     * dp[i][j] means array1[0, i -1] and array2[0, j - 1] count
     */
    private fun count2(): Int {
        val dp = Array(array1.size + 1) { IntArray(array2.size + 1)}

        // init when t is empty string
        for (i in 0..array1.size) {
            dp[i][0] = 1
        }

        // init when s is empty string
        for (j in 1..array2.size) {
            dp[0][j] = 0
        }

        for (i in 1..array1.size) {
            for (j in 1..array2.size) {
                if (array1[i - 1] == array2[j - 1]) {
                    // use (i - 1) or not
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j]
                } else {
                    dp[i][j] = dp[i - 1][j]
                }
            }
        }

        return dp[array1.size][array2.size]
    }

    /**
     * dfs + cache
     */
    private fun count1(end1: Int, end2: Int): Int {
        // base case
        if (end2 < 0) {
            return 1
        }

        if (end1 < 0) {
            return 0
        }

        val key = "${end1}.${end2}"
        if (memo.containsKey(key)) {
            return memo.getOrDefault(key, 0)
        }

        var res = 0
        if (array1[end1] == array2[end2]) {
            res = count1(end1 - 1, end2 - 1) + count1(end1 - 1, end2)
        } else {
            res =  count1(end1 - 1, end2)
        }

        memo[key] = res
        return res
    }

    /**
     * dfs
     */
    private fun count(end1: Int, end2: Int): Int {
        // base case
        if (end2 < 0) {
            return 1
        }

        if (end1 < 0) {
            return 0
        }

        if (array1[end1] == array2[end2]) {
            return count(end1 - 1, end2 - 1) + count(end1 - 1, end2)
        } else {
            return count(end1 - 1, end2)
        }
    }
}

fun main() {
    val res = Hard_115_DistinctSubSequence.numDistinct("rabbbit", "rabbit")
    asserts(res, 3)
    val res1 = Hard_115_DistinctSubSequence.numDistinct("babgbag", "bag")
    asserts(res1, 5)
}