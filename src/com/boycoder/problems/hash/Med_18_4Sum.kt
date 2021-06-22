package com.boycoder.problems.hash

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc: https://leetcode-cn.com/problems/4sum/
 */
object Med_18_4Sum {
    fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
        nums.sort()
        val list: MutableList<List<Int>> = mutableListOf()

        var i = 0
        var j = 0
        while (i < nums.size) {
            j = i + 1
            while (j < nums.size - 1) {

                var left = j + 1
                var right = nums.size -1
                while (left < right) {
                    val sum = nums[i] + nums[j] + nums[left] + nums[right]

                    if (sum > target) {
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--
                        }
                        right--
                    } else if (sum < target) {
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++
                        }
                        left++
                    } else {
                        list.add(listOf(nums[i], nums[j], nums[left], nums[right]))
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--
                        }
                        right--

                        while (left < right && nums[left] == nums[left + 1]) {
                            left++
                        }
                        left++
                    }
                }
                while (j < nums.size - 1 && nums[j] == nums[j + 1]) {
                    j++
                }
                j++

            }
            while (i < nums.size - 1 && nums[i] == nums[i + 1]) {
                i++
            }
            i++
        }
        return list
    }
}