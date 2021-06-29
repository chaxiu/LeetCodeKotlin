package com.boycoder.problems.backtrack

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc: https://leetcode-cn.com/problems/combination-sum/
 */
object Med_39_CombinationSum {
    private val list: MutableList<List<Int>> = mutableListOf()
    private val path: MutableList<Int> = mutableListOf()
    private var sum: Int = 0

    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        combine(candidates, target, 0)
        return list
    }

    private fun combine(nums: IntArray, target: Int, start: Int) {
        if (sum > target) return

        if (sum == target) {
            // careful toMutableList
            list.add(path.toMutableList())
            return
        }

        for (i in start until nums.size) {
            path.add(nums[i])
            sum = sum + nums[i]
            // We can pick the same element many times, so dont use i+1
            // By using i, every recursion can pick the same value
            combine(nums, target, i)
            sum = sum - nums[i]
            path.removeAt(path.size - 1)
        }
    }
}