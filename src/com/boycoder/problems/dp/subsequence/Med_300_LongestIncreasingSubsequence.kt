package com.boycoder.problems.dp.subsequence

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/16
 * @desc:
 */
object Med_300_LongestIncreasingSubsequence {
    fun find(array: IntArray): Int {
    }
}

fun main() {
    val res = Med_300_LongestIncreasingSubsequence.find(intArrayOf(10,9,2,5,3,7,101,18))
    asserts(res, 4)
    val res1 = Med_300_LongestIncreasingSubsequence.find(intArrayOf(0,1,0,3,2,3))
    asserts(res1, 4)
    val res2 = Med_300_LongestIncreasingSubsequence.find(intArrayOf(7,7,7,7,7,7,7))
    asserts(res2, 1)
}
