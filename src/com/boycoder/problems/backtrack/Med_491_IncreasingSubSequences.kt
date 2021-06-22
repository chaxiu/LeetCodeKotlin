package com.boycoder.problems.backtrack

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Med_491_IncreasingSubSequences {
    private val list: MutableList<List<Int>> = mutableListOf()
    private val path: MutableList<Int> = mutableListOf()

    fun findSubsequences(nums: IntArray): List<List<Int>> {
        backtrack(nums, 0)
        return list
    }

    private fun backtrack(array: IntArray, start: Int) {
        if (path.size > 1) {
            list.add(path.toList())
        }

        if (start >= array.size) {
            return
        }

        val used = BooleanArray(210)
        for (i in start until array.size) {
            if (path.size > 0 && array[i] < path[path.size - 1]) {
                continue
            }

            if (used[array[i] + 100]) {
                continue
            }

            used[array[i] + 100] = true

            path.add(array[i])
            backtrack(array, i + 1)
            path.removeAt(path.size - 1)
        }
    }

    private fun find(array: IntArray, start: Int) {
        if (path.size > 1) {
            list.add(path.toList())
        }

        if (start >= array.size) {
            return
        }

        val set = hashSetOf<Int>()
        for (i in start until array.size) {
            if (!isValid(set, array[i])) {
                continue
            }

            set.add(array[i])
            path.add(array[i])
            find(array, i + 1)
            path.removeAt(path.size - 1)
        }
    }

    // current elment is smaller than path end element
    // current element has been visited
    private fun isValid(set: HashSet<Int>, num: Int): Boolean {
        if (path.size > 0 &&
                num < path[path.size - 1]) {
            // not increasing
            return false
        }

        if (set.contains(num)) {
            return false
        }

        return true
    }
}