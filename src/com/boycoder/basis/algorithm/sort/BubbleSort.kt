package com.boycoder.basis.algorithm.sort

import com.boycoder.utils.asserts
import com.boycoder.utils.isSorted
import kotlin.random.Random

/**
 * @Author: zhutao
 * @datetime: 2021/6/4
 *
 * @desc:  The basic ideal of bubble sort is, compare every pair of element,
 * make sure the biggest element go to the end.
 *      eg: 6,4,5,1,5
 *          | |
 *          swap
 *          4,6,5,1,5
 *            | |
 *          4,5,6,1,5
 *              | |
 *          4,5,1,6,5
 *                | |
 *          4,5,1,5,6
 *                | |
 *          4,5,1,5
 *          | |
 * Time: O(N^2)
 * Space: O(1)
 * In-Place: Yes
 * Stable: Yes
 */
object BubbleSort {
    fun sort(array: IntArray): IntArray {
        return sort3(array)
    }

    /**
     * Bubble sort + optimize
     * For the case that the tail elements already sorted,
     * we can just skip.
     */
    private fun sort2(array: IntArray): IntArray {
        var end = array.size - 1

        while (end >= 1) {
            var endIndex = 1
            for (begin in 1..end) {
                if (array[begin - 1] > array[begin]) {
                    val temp = array[begin - 1]
                    array[begin - 1] = array[begin]
                    array[begin] = temp

                    endIndex = begin
                }
            }
            end = endIndex
            end--
        }

        return array
    }

    // Basic bubble sort with while
    private fun sort3(array: IntArray): IntArray {
        var end = array.size - 1

        while (end >= 1) {
            var begin = 1
            while (begin <= end) {
                if (array[begin - 1] > array[begin]) {
                    val temp = array[begin - 1]
                    array[begin - 1] = array[begin]
                    array[begin] = temp
                }
                begin++
            }
            end--
        }

        return array
    }

    // Basic bubble sort
    private fun sort1(array: IntArray): IntArray {
        for (end in (array.size - 1) downTo 1) {
            for (begin in 1..end) {
                if (array[begin - 1] > array[begin]) {
                    val temp = array[begin - 1]
                    array[begin - 1] = array[begin]
                    array[begin] = temp
                }
            }
        }

        return array
    }
}

fun main() {
    val array = (1..100).shuffled().toIntArray()
    val res = isSorted(BubbleSort.sort(array))
    asserts(res, true)
}