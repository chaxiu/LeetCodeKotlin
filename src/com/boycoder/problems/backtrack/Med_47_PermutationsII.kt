package com.boycoder.problems.backtrack

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 *
 * Ideal
 * This is a little bit like https://leetcode-cn.com/problems/permutations/
 * For this case,
 * we can sort the array and make sure in the same loop we dont visit the same element.
 * And because the elements in array can be duplicate,
 * so we can not use path.contains(value) to check,
 * we need to record the index, not the value, for the path.
 */
object Med_47_PermutationsII {
    private val list: MutableList<List<Int>> = mutableListOf()
    private val path: MutableList<Int> = mutableListOf()
    private val used = BooleanArray(21)

    fun permuteUnique(nums: IntArray): List<List<Int>> {
        nums.sort()
        backtrack(nums)
        return list
    }

    private fun backtrack(array: IntArray) {
        if (path.size == array.size) {
            list.add(path.toList())
            return
        }

        val set = hashSetOf<Int>()

        for (i in 0 until array.size) {
            // avoid duplication for the same result
            if (used[i + 10]) {
                continue
            }

            // for the same level loop we avoid duplicate value
            if (set.contains(array[i])) {
                continue
            }
            set.add(array[i])

            used[i + 10] = true
            path.add(array[i])
            backtrack(array)
            used[i + 10] = false
            path.removeAt(path.size - 1)
        }
    }
}