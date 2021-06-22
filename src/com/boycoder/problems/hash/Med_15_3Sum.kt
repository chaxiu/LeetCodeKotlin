package com.boycoder.problems.hash

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc: https://leetcode-cn.com/problems/3sum/
 */
object Med_15_3Sum {
    fun threeSum(nums: IntArray): List<List<Int>> {
        nums.sort()

        var i = 0
        val list: MutableList<List<Int>> = mutableListOf()

        while (i < nums.size) {
            if (nums[i] > 0) return list
            var left = i + 1
            var right = nums.size - 1

            while (left < right) {
                if (nums[i] + nums[left] + nums[right] == 0) {
                    list.add(listOf(nums[i], nums[left], nums[right]))
                    while (left < right && nums[right] == nums.getOrNull(right - 1)) {
                        right--
                    }
                    while (left < right && nums[left] == nums.getOrNull(left + 1)) {
                        left++
                    }
                    left++
                    right--
                } else if (nums[i] + nums[left] + nums[right] > 0) {
                    while (left < right && nums[right] == nums.getOrNull(right - 1)) {
                        right--
                    }
                    right--
                } else if (nums[i] + nums[left] + nums[right] < 0) {
                    while (left < right && nums[left] == nums.getOrNull(left + 1)) {
                        left++
                    }
                    left++
                }
            }

            while (nums[i] == nums.getOrNull(i + 1)) {
                i++
            }

            i++
        }

        return list
    }
}