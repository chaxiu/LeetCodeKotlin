package com.boycoder.problems.hash

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Med_454_FourSum {
    fun fourSumCount(nums1: IntArray, nums2: IntArray, nums3: IntArray, nums4: IntArray): Int {
        val map1 = hashMapOf<Int, Int>()
        var res = 0


        for (i in nums1) {
            for (j in nums2) {
                map1.put(i + j, map1.getOrDefault(i + j, 0) + 1)
            }
        }

        for (i in nums3) {
            for (j in nums4) {
                if (map1.contains(-i -j)) {
                    res = res + map1[-i-j]!!
                }
            }
        }

        return res
    }
}