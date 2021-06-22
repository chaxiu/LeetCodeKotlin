package com.boycoder.problems.backtrack

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Med_90_SubSetII {
    private val list: MutableList<List<Int>> = mutableListOf()
    private val path: MutableList<Int> = mutableListOf()

    fun subsetsWithDup(nums: IntArray): List<List<Int>> {
        nums.sort()
        sub(nums, 0)
        return list
    }

    // using sorted array and num[i] == nums[i - 1] to check
    private fun sub(nums: IntArray, start: Int) {
        list.add(path.toList())

        if (path.size >= nums.size) {
            return
        }

        for (i in start until nums.size) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue
            }

            path.add(nums[i])
            sub(nums, i + 1)
            path.removeAt(path.size - 1)
        }
    }

    private fun sub1(nums: IntArray, start: Int) {
        list.add(path.toList())

        if (path.size >= nums.size) {
            return
        }

        val set = hashSetOf<Int>()
        for (i in start until nums.size) {
            if (set.contains(nums[i])) {
                continue
            }
            set.add(nums[i])

            path.add(nums[i])
            sub(nums, i + 1)
            path.removeAt(path.size - 1)
        }
    }
}