package com.boycoder.problems.backtrack

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 *
 * Ideal
 * Use recursion and backtrack to solve permutations problem
 *
 * Because we need permutations, so we can reuse the same array in every level. But the same element can not duplicate in the same path, so we need to check the element going to visit, if it in the path already.
 *
 * The difference between combination and permutation is :
 *  1. No start index passed
 *  2. Always loop from 0
 *  3. No duplicate for the path elements
 */
object Med_46_Permutations {
    private val list: MutableList<List<Int>> = mutableListOf()
    private val path: MutableList<Int> = mutableListOf()

    fun permute(nums: IntArray): List<List<Int>> {
        backtrack(nums)
        return list
    }

    private fun backtrack(array: IntArray) {
        if (path.size == array.size) {
            list.add(path.toList())
        }

        for (i in 0 until array.size) {
            // we can do this only if the elements in array wont duplicate (are distinct)
            if (path.contains(array[i])) {
                continue
            }
            path.add(array[i])
            backtrack(array)
            path.removeAt(path.size - 1)
        }
    }
}