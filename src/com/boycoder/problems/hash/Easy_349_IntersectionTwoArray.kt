package com.boycoder.problems.hash

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc: https://leetcode-cn.com/problems/intersection-of-two-arrays/
 */
object Easy_349_IntersectionTwoArray {
    fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
        val set1 = mutableSetOf<Int>()
        val set2 = mutableSetOf<Int>()
        val result = ArrayList<Int>()

        for (i in nums1) {
            set1.add(i)
        }

        for (j in nums2) {
            set2.add(j)
        }

        set1.forEach {
            if (set2.contains(it)) {
                result.add(it)
            }
        }

        return result.toIntArray()
    }
}