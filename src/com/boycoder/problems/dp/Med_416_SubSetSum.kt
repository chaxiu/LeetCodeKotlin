package com.boycoder.problems.dp

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/3
 * @desc:
 */
object Med_416_SubSetSum {
    private var sum = 0
    private var numSum = 0
    private val map = hashMapOf<String, Boolean>()
    private var array = intArrayOf()


    fun canPartition(nums: IntArray): Boolean {
        nums.sort()
        numSum = nums.sumBy{it}
        if (numSum % 2 != 0) {
            return false
        }
        return dfs(0, 0)
    }

    private fun dfs(sum: Int, start: Int): Boolean {
        if (sum * 2 == numSum) {
            return true
        }

        if (sum * 2 > numSum) {
            return false
        }

        if (start >= array.size) {
            return false
        }


        val res = dfs(sum, start + 1) || dfs(sum + array[start], start + 1)
        return res
    }
}

fun main() {
    val res = Med_416_SubSetSum.canPartition(intArrayOf(1,5,11,5))
    asserts(res, true)
}