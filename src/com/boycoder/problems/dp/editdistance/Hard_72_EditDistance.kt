package com.boycoder.problems.dp.editdistance

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/20
 * @desc: https://leetcode-cn.com/problems/edit-distance/
 */
object Hard_72_EditDistance {
    var array1 = charArrayOf()
    var array2 = charArrayOf()

    val memo = hashMapOf<String, Int>()

    fun minDistance(word1: String, word2: String): Int {
        array1 = word1.toCharArray()
        array2 = word2.toCharArray()
        return distance2()
    }

    /**
     * dp
     *
     * dp[i][j] means array1[0, i - 1] array2[0, j - 1] edit distance
     */
    private fun distance2(): Int {
        val dp = Array(array1.size + 1) { IntArray(array2.size + 1) }

        // init when word2 is empty string
        for (i in 0..array1.size) {
            dp[i][0] = i
        }

        // init when word1 is empty string
        for (j in 0..array2.size) {
            dp[0][j] = j
        }

        // calculation
        for (i in 1..array1.size) {
            for (j in 1..array2.size) {
                if (array1[i - 1] == array2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1]
                } else {
                    // delete word1
                    val res1 = dp[i - 1][j] + 1
                    // delete word2
                    val res2 = dp[i][j - 1] + 1
                    // replace
                    val res3 = dp[i - 1][j - 1] + 1
                    dp[i][j] = Math.min(res1, Math.min(res2, res3))
                }
            }
        }

        return dp[array1.size][array2.size]
    }

    /**
     * dfs + cache
     */
    private fun distance1(end1: Int, end2: Int): Int {
        if (end1 < 0) {
            return end2 + 1
        }

        if (end2 < 0) {
            return end1 + 1
        }

        var result = 0
        val key = "${end1}.${end2}"
        if (memo.containsKey(key)) {
            return memo.getOrDefault(key, 0)
        }

        if (array1[end1] == array2[end2]) {
            result = distance(end1 - 1, end2 - 1)
        } else {
            // replace
            val res1 = distance(end1 - 1, end2 -1) + 1
            // delete word1
            val res2 = distance(end1 - 1, end2) + 1
            // delete word2
            val res3 = distance(end1, end2 - 1) + 1

            result = Math.min(res1, Math.min(res2, res3))
        }

        memo[key] = result
        return result
    }


    /**
     * dfs
     *
     * Try to break it into smaller problems
     */
    private fun distance(end1: Int, end2: Int): Int {
        if (end1 < 0) {
            return end2 + 1
        }

        if (end2 < 0) {
            return end1 + 1
        }

        if (array1[end1] == array2[end2]) {
            return distance(end1 - 1, end2 - 1)
        } else {
            // replace
            val res1 = distance(end1 - 1, end2 -1) + 1
            // delete word1
            val res2 = distance(end1 - 1, end2) + 1
            // delete word2
            val res3 = distance(end1, end2 - 1) + 1

            return Math.min(res1, Math.min(res2, res3))
        }
    }
}

fun main() {
    val res = Hard_72_EditDistance.minDistance("horse", "ros")
    asserts(res, 3)
    val res1 = Hard_72_EditDistance.minDistance("intention", "execution")
    asserts(res1, 5)
}