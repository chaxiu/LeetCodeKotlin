package com.boycoder.problems.array

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/21
 * @desc: https://leetcode-cn.com/problems/count-number-of-nice-subarrays/
 * Brute force
 * slide window
 */
object Med_1248_CountNiceSubArray {
    fun numberOfSubarrays(nums: IntArray, k: Int): Int {
        return count(nums, k)
    }

    private fun count(nums: IntArray, k: Int): Int {
        var count = 0
        var presum = 0
        // <presum, count>
        var map = hashMapOf<Int, Int>()
        map[0] = 1

        for (right in 0 until nums.size) {
            presum = presum + (nums[right] and 1)

            if (map.containsKey(presum - k)) {
                count = count + map[presum - k]!!
            }

            map.put(presum, map.getOrDefault(presum, 0) + 1)
        }

        return count
    }
}

fun main() {
    val res = Med_1248_CountNiceSubArray.numberOfSubarrays(intArrayOf(1,1,2,1,1), 3)
    asserts(res, 2)
    val res1 = Med_1248_CountNiceSubArray.numberOfSubarrays(intArrayOf(2,4,6), 1)
    asserts(res1, 0)
    val res2 = Med_1248_CountNiceSubArray.numberOfSubarrays(intArrayOf(2,2,2,1,2,2,1,2,2,2), 2)
    asserts(res2, 16)
}