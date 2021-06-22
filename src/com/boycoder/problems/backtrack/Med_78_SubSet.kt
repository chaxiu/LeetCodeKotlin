package com.boycoder.problems.backtrack

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 *
 * Ideal
 * For the sub set of a set. When we trying to find the combination, all the node of this tree(state of path), are the no-duplicate subset
 *
 * eg: n = 3
 *
 *                     [1,2,3]
 *                1/     2|      \3
 *              [2,3]    [3]      []
 *             2/   \3    |3
 *            [3]   []    []
 *           3/
 *          []
 *
 */
object Med_78_SubSet {
    private val list: MutableList<List<Int>> = mutableListOf()
    private val path: MutableList<Int> = mutableListOf()

    fun subsets(nums: IntArray): List<List<Int>> {
        sub(nums, 0)
        return list
    }

    private fun sub(nums: IntArray, start: Int) {

        list.add(path.toList())

        if (start >= nums.size) {
            return
        }

        for (i in start until nums.size) {
            path.add(nums[i])
            sub(nums, i + 1)
            path.removeAt(path.size - 1)
        }
    }
}