package com.boycoder.problems.dp.subsequence

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/17
 * @desc: https://leetcode-cn.com/problems/maximum-subarray/
 */
object Easy_53_MaxSubArray {

    private var array = intArrayOf()

    fun maxSubArray(nums: IntArray): Int {
        if (nums.size == 0) return 0
        this.array = nums
        return find1()
    }

    /**
     * dp
     *
     * dp[i] means the max sum that end with (i - 1)
     * dp[i] = if (dp[i - 1] < 0) array[i] else dp[i - 1] + array[i]
     * or
     * dp[i] = Math.max(dp[i - 1] + array[i], array[i])
     */
    private fun find1(): Int {
        val dp = IntArray(array.size)
        //init
        dp[0] = array[0]
        var max = dp[0]

        // calculate dp[i] and get max value
        for (i in 1 until array.size) {
            dp[i] = Math.max(dp[i - 1] + array[i], array[i])
            max = Math.max(max, dp[i])
        }

        return max
    }

    /**
     * Greedy + two pointer
     * -2,1,-3,4,-1,2,1,-5,4
     * |
     */
    private fun find(): Int{
        val size = array.size

        var max = Int.MIN_VALUE
        var left = 0
        var right = 0


        while (left < size && right < size) {
            var sum = 0
            // here can be optimized
            for (i in left..right) {
                sum = sum + array[i]
            }
            max = Math.max(max, sum)

            // here control the window slide
            if (sum < 0) {
                right++
                left = right
            } else {
                right++
            }
        }

        return max
    }
}

fun main() {
    val res = Easy_53_MaxSubArray.maxSubArray(intArrayOf(-2,1,-3,4,-1,2,1,-5,4))
    asserts(res, 6)
    val res1 = Easy_53_MaxSubArray.maxSubArray(intArrayOf(4,-1,2,1))
    asserts(res1, 6)
}