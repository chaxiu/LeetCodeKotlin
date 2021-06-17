package com.boycoder.problems.dp.subsequence

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/17
 * @desc: https://leetcode-cn.com/problems/is-subsequence/
 */
object Easy_392_IsSubSequence {
    fun isSubsequence(s: String, t: String): Boolean {
        return isValid(s, t)
    }

    private fun isValid(s: String, t: String): Boolean {
        return false
    }
}

fun main() {
    val res = Easy_392_IsSubSequence.isSubsequence("abc", "ahbgdc")
    asserts(res, true)
    val res1 = Easy_392_IsSubSequence.isSubsequence("axc", "ahbgdc")
    asserts(res1, false)
}