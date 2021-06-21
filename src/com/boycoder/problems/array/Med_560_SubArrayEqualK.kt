package com.boycoder.problems.array

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/21
 * @desc: https://leetcode-cn.com/problems/subarray-sum-equals-k/
 * Brute force
 * Brute force optimize
 * Prefix sum
 */
object Med_SubArrayEqualK {
    fun subarraySum(nums: IntArray, k: Int): Int {
        return count3(nums, k)
    }

    /**
     * ----------sum[i]---------------
     * ------prefix[j]-------
     *                       ----k----
     * length = i - j
     *
     *           [1,1,1]  k = 2
     * prefix [0, 1,2,3]
     * map   [0 to 1, 1 to 1, 2 to 2, 3 to 3]
     * end = 3, 3 - k = 1, map[1] = 1, count++
     * end = 2, 2 - k = 0, map[0] = 1, count++
     * end = 1, 1 - k = -1, map[-1] not found
     *
     *           [-1, -1, 1] k = 0
     * prefix  [0,-1, -2, -1]
     * map    [0 to 1, -1 to 2, -2 to 1]
     * end = 3, -1 - k = -1, count = 2
     *
     *
     *
     * This solution is similar to [Med_325_MaxSizeSubArray.maxSubArrayLen]
     */
    private fun count3(nums: IntArray, k: Int): Int {
        var count = 0
        var prefix = 0
        val map = hashMapOf<Int, Int>()
        map.put(0 , 1)

        for (num in nums) {
            prefix = prefix + num
            if (map.containsKey(prefix - k)) {
                count  = count + map.getOrDefault(prefix - k, 0)
            }

            map.put(prefix, map.getOrDefault(prefix, 0) + 1)
        }

        return count
    }

    /**
     * ----------sum[i]---------------
     * ------prefix[j]-------
     *                       ----k----
     * loop start end end
     * if (prefix[end] - prefix[start]) count++
     *
     * This solution is similar to [Med_325_MaxSizeSubArray.maxSubArrayLen]
     */
    private fun count2(nums: IntArray, k: Int): Int {
        val prefix = IntArray(nums.size + 1)
        var count = 0

        for (i in 1..nums.size) {
            prefix[i] = prefix[i - 1] + nums[i - 1]
        }

        for (start in 0 until nums.size) {
            for (end in (start+1)..nums.size) {
                if (prefix[end] - prefix[start] == k) {
                    count++
                }
            }
        }

        return count
    }



    /**
     * Brute force + optimize
     */
    private fun count1(nums: IntArray, k: Int): Int {
        var count = 0

        for (start in 0 until nums.size) {
            var sum = 0
            for (end in start until nums.size) {
                sum = sum + nums[end]

                if (sum == k) {
                    count++
                }
            }
        }

        return count
    }

    /**
     * Brute force
     */
    private fun count(nums: IntArray, k: Int): Int {
        var count = 0

        for (start in 0 until nums.size) {

            for (end in start until nums.size) {
                var sum = 0
                for (k in start..end) {
                    sum = sum + nums[k]
                }

                if (sum == k) {
                    count++
                }
            }
        }

        return count
    }
}

fun main() {
    val res = Med_SubArrayEqualK.subarraySum(intArrayOf(1, 1, 1), 2)
    asserts(res, 2)
    val res1 = Med_SubArrayEqualK.subarraySum(intArrayOf(-1, -1, 1), 1)
    asserts(res1, 1)
}