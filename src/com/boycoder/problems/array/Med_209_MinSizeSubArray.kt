package com.boycoder.problems.array

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/21
 * @desc: https://leetcode-cn.com/problems/minimum-size-subarray-sum/
 */
object Med_209_MinSizeSubArray {
    fun minSubArrayLen(target: Int, nums: IntArray): Int {
        return min2(target, nums)
    }

    /**
     * slide window
     */
    private fun min2(target: Int, nums: IntArray): Int {
        var left = 0
        var sum = 0
        var min = Int.MAX_VALUE

        for (right in 0 until nums.size) {
            sum = sum + nums[right]

            while (sum >= target) {
                min = Math.min(min, right - left + 1)
                sum = sum - nums[left]
                left++
            }
        }

        return min
    }

    /**
     * Brute Force
     */
    private fun min(target: Int, nums: IntArray): Int {
        var min = Int.MAX_VALUE
        for(i in 0 until nums.size) {
            for (j in i until nums.size) {
                // sum
                var sum = 0
                for (k in i..j) {
                    sum = sum + nums[k]
                }
                if (sum >= target) {
                    min = Math.min(min, j - i + 1)
                }
            }
        }

        return if (min == Int.MAX_VALUE) 0 else min
    }

    /**
     * Two pointer
     */
    private fun min1(target: Int, nums: IntArray): Int {
        if (nums.size == 0) return 0

        var left = 0
        var right = 0
        var sum = nums[0]
        var min = Int.MAX_VALUE

        while (right < nums.size && left < nums.size) {
            if (sum >= target) {
                min = Math.min(min, right - left + 1)
                sum = sum - nums[left]
                left++
            } else {
                right++
                if (right == nums.size) break
                sum = sum + nums[right]
            }
        }

        return if (min == Int.MAX_VALUE) 0 else min
    }
}

fun main() {
    val res = Med_209_MinSizeSubArray.minSubArrayLen(7, intArrayOf(2,3,1,2,4,3))
    asserts(res, 2)
    val res1 = Med_209_MinSizeSubArray.minSubArrayLen(4, intArrayOf(1,4,4))
    asserts(res1, 1)
    val res2 = Med_209_MinSizeSubArray.minSubArrayLen(11, intArrayOf(1,1,1,1,1,1,1,1))
    asserts(res2, 0)
}