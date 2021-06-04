package com.boycoder.basis.algorithm.sort

import com.boycoder.utils.asserts
import com.boycoder.utils.isSorted

/**
 * @Author: zhutao
 * @datetime: 2021/6/4
 * @desc: The ideal of merge sort is, Divide Conquer
 *      We divide the array into many parts, and merge them to sort
 *
 * Time: O(NLogN)
 * Space: O(N)
 * In-Place: No
 * Stable: Yes
 */
object MergeSort {
    fun sort(array: IntArray): IntArray {
        mergeSort(array, 0, array.size - 1)
        return array
    }

    private fun mergeSort(array: IntArray, begin: Int, end: Int) {
        if (end - begin < 1) {
            // there is only one element
            return
        }

        val mid = begin + (end - begin) / 2

        // divide
        mergeSort(array, begin, mid)
        mergeSort(array, mid + 1, end)

        // conquer
        merge(array, begin, mid, end)
    }

    /**
     * [begin, mid]
     * [mid + 1, end]
     */
    private fun merge(array: IntArray, begin: Int, mid: Int, end: Int) {
        val backup = array.slice(begin..mid)
        var cur = begin
        var leftCur = 0
        var rightCur = mid + 1

        while (cur <= end) {
            // 1. pick left
            // 2. right side out of index
            if (rightCur > end || (leftCur < backup.size && backup[leftCur] < array[rightCur])) {
                array[cur] = backup[leftCur]
                leftCur++
            } else {
                array[cur] = array[rightCur]
                rightCur++
            }
            cur++
        }
    }
}

fun main() {
    val array = (1..100).shuffled().toIntArray()
    val res = isSorted(MergeSort.sort(array))
    asserts(res, true)
}