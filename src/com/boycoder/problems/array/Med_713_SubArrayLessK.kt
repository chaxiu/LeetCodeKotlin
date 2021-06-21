package com.boycoder.problems.array

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/21
 * @desc: https://leetcode-cn.com/problems/subarray-product-less-than-k/
 * Brutte force
 * Two pointer
 */
object Med_713_SubArrayLessK {
    fun numSubarrayProductLessThanK(nums: IntArray, k: Int): Int {
        return count(nums, k)
    }

    private fun count(nums: IntArray, k: Int): Int {
        if (nums.size == 0) return 0
        var left = 0
        var product = 1
        var count = 0

        for (right in 0 until nums.size) {
            product = product * nums[right]

            while (product >= k && left <= right) {
                product = product / nums[left]
                left++
            }

            // find the min left index that [left, right] product less than k
            // and in this range, there is (right - left + 1) count
            count = count + right - left + 1
        }

        return count
    }
}

fun main() {
    val res = Med_713_SubArrayLessK.numSubarrayProductLessThanK(intArrayOf(10,5,2,6), 100)
    asserts(res, 8)
}