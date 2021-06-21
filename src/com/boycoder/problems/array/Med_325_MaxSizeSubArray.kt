package com.boycoder.problems.array

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/21
 * @desc: https://leetcode-cn.com/problems/maximum-size-subarray-sum-equals-k/
 * Brute force
 * Prefix sum
 */
object Med_325_MaxSizeSubArray {

    /**
     * ----------sum[i]---------------
     * ------prefix[j]-------
     *                       ----k----
     * length = i - j
     *
     *
     *       [1, -1, 5, -2, 3]
     * sum [0,1, 0,  5,  3, 6]
     * map [0 to 0, 1 to 1, 5 to 3, 3 to 4, 6 to 5]
     *
     * j from end to 1
     * j = 5   sum[5] - k = 3, map[3] = 4, max = 5 - 4
     * j = 4   sum[4] - k = 0, map[0] = 0, max = 4 - 0
     * j = 3   sum[3] - k = 2, map[0] not found
     * j = 2   sum[2] - k = -3, map[-3] not found
     * j = 1   sum[1] - k = -2, map[-2] not found
     * j = 0   sum[0] - k = -3, map[-3] not found
     */
    fun maxSubArrayLen(nums: IntArray, k: Int): Int {
        // <PrefixSum, Index>
        val map = hashMapOf<Int, Int>()
        val sum = IntArray(nums.size + 1)
        var max = 0
        map.put(0, 0)

        // Get the prefix sum, and put them into map
        for (i in 1..nums.size) {
            sum[i] = sum[i - 1] + nums[i - 1]

            // For the same prefix sum, we always save the min index
            if (!map.containsKey(sum[i])) {
                map.put(sum[i], i)
            }
        }

        // find the max length
        // so we need to from end to start
        // prefix + k = sum[i]
        for (j in nums.size downTo 1) {
            if (!map.containsKey(sum[j] - k)) continue

            val start = map.getOrDefault(sum[j] - k, 0)
            max = Math.max(max, j - start)
        }

        return max
    }
}

fun main() {
    val res = Med_325_MaxSizeSubArray.maxSubArrayLen(intArrayOf(1, -1, 5, -2, 3), 3)
    asserts(res, 4)
    val res1 = Med_325_MaxSizeSubArray.maxSubArrayLen(intArrayOf(-2, -1, 2, 1), 1)
    asserts(res1, 2)
}