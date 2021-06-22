package com.boycoder.problems.dp.editdistance

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/20
 * @desc: https://leetcode-cn.com/problems/palindromic-substrings/
 */
object Med_647_PalindromicSubString {
    var array = charArrayOf()
    fun countSubstrings(s: String): Int {
        array = s.toCharArray()
        return count()
    }

    private fun count(): Int {
        val memo = Array(array.size){ IntArray(array.size){ -1 } }
        var res = 0
        // split every sub string and check it by using dfs
        for (start in 0 until array.size) {
            for (end in start until array.size) {
                res = res + (if (dfs1(memo, start, end)) 1 else 0)
            }
        }

        return res
    }

    private fun dfs1(memo: Array<IntArray>, start: Int, end: Int): Boolean {
        if (start == end) return true
        if (start + 1 == end) return array[start] == array[end]

        if (memo[start][end] != -1) {
            return memo[start][end] == 1
        }

        val res = if (array[start] == array[end]) dfs(start + 1, end - 1) else false
        memo[start][end] = if (res) 1 else 0

        return res
    }

    private fun dfs(start: Int, end: Int): Boolean {
        if (start == end) return true
        if (start + 1 == end) return array[start] == array[end]

        return if (array[start] == array[end]) dfs(start + 1, end - 1) else false
    }
}

fun main() {
    val res = Med_647_PalindromicSubString.countSubstrings("abc")
    asserts(res, 3)
    val res1 = Med_647_PalindromicSubString.countSubstrings("aaa")
    asserts(res1, 6)
}
