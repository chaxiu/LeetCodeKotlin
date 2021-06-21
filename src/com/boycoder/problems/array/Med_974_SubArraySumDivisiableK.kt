package com.boycoder.problems.array

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/21
 * @desc: https://leetcode-cn.com/problems/subarray-sums-divisible-by-k/
 */
object Med_974_SubArraySumDivisiableK {
    fun subarraysDivByK(nums: IntArray, k: Int): Int {
        return count1(nums, k)
    }

    /**
     * Pre sum
     *
     * ---------------------sum[i]%k=3---------------------------
     * ----------sum[j]%k=3-----------
     *                                ---------sum[i,j]%k=0------
     *
     *
     * similar to [Med_523_ContinuousSubArraySumK]
     */
    private fun count1(nums: IntArray, k: Int): Int {
        var presum = 0
        var count = 0
        val map = hashMapOf<Int, Int>()
        map[0] = 1

        for (i in nums) {
            presum = presum + i
            // presum maybe negative
            val key = (presum % k + k) % k
            if (map.containsKey(key)) {
                count = count + map.getOrDefault(key, 0)
            }
            map[key] = map.getOrDefault(key, 0) + 1
        }

        return count
    }

    /**
     * Brute force
     */
    private fun count(nums: IntArray, k: Int): Int {
        var count = 0

        for (start in 0 until nums.size) {
            var sum = 0
            for (end in start until nums.size) {
                sum = sum + nums[end]

                if (sum % k == 0) {
                    count++
                }
            }
        }

        return count
    }
}

fun main() {
    val res = Med_974_SubArraySumDivisiableK.subarraysDivByK(intArrayOf(4,5,0,-2,-3,1), 5)
    asserts(res, 7)
}