package com.boycoder.problems.array

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/21
 * @desc: https://leetcode-cn.com/problems/continuous-subarray-sum/
 */
object Med_523_ContinuousSubArraySum {
    fun checkSubarraySum(nums: IntArray, k: Int): Boolean {
        return check1(nums, k)
    }

    /**
     * prefix sum
     *
     * similar to [Med_560_SubArrayEqualK.count3]
     *
     * Ideal from:https://leetcode-cn.com/problems/continuous-subarray-sum/solution/gong-shui-san-xie-tuo-zhan-wei-qiu-fang-1juse/
     */
    private fun check1(nums: IntArray, k: Int): Boolean {
        val sum = IntArray(nums.size + 1)
        // <prefixSum, index>
        val set = hashSetOf<Int>()

        for (i in 1..nums.size) {
            sum[i] = sum[i - 1] + nums[i - 1]
        }

        for (j in 2..nums.size) {
            set.add(sum[j - 2] % k)
            if (set.contains(sum[j] % k)) return true
        }

        return false
    }

    /**
     * Brute force
     */
    private fun check(nums: IntArray, k: Int): Boolean {

        for (start in 0 until nums.size) {
            var sum = nums[start]
            for (end in (start + 1) until nums.size) {
                sum = sum + nums[end]
                if (sum % k == 0) {
                    return true
                }
            }
        }

        return false
    }
}

fun main() {
    val res = Med_523_ContinuousSubArraySum.checkSubarraySum(intArrayOf(23,2,4,6,7), 6)
    asserts(res, true)
    val res1 = Med_523_ContinuousSubArraySum.checkSubarraySum(intArrayOf(23,2,6,4,7), 6)
    asserts(res1, true)
    val res2 = Med_523_ContinuousSubArraySum.checkSubarraySum(intArrayOf(23,2,6,4,7), 13)
    asserts(res2, false)
}