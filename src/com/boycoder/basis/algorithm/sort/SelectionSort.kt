package com.boycoder.basis.algorithm.sort

import com.boycoder.utils.asserts
import com.boycoder.utils.isSorted

/**
 * @Author: zhutao
 * @datetime: 2021/6/4
 * @desc: Selection sort is like Bubble sort, but it dont swap every time.
 *      Instead of swapping, we mark the max index, and swap it with the end.
 * Time: O(N^2)
 * Space: O(1)
 * In-Place: Yes
 * Stable: No
 */
object SelectionSort {
    fun sort(array: IntArray): IntArray {
        for (end in (array.size - 1) downTo 1) {

            var maxIndex = 0
            for (begin in 1..end) {
                if (array[begin] > array[maxIndex]) {
                    // mark max index
                    maxIndex = begin
                }
            }

            // swap
            val temp = array[maxIndex]
            array[maxIndex] = array[end]
            array[end] = temp
        }

        return array
    }
}

fun main() {
    val array = (1..100).shuffled().toIntArray()
    val res = isSorted(SelectionSort.sort(array))
    asserts(res, true)
}