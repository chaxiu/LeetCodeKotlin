package com.boycoder.basis.algorithm.sort

import com.boycoder.utils.asserts
import com.boycoder.utils.isSorted

/**
 * @Author: zhutao
 * @datetime: 2021/6/4
 * @desc: The ideal of count sort is a trade-off between time and space
 */
object CountSort {
    fun sort(array: IntArray, max: Int): IntArray {
        val count = IntArray(max + 1)
        for (i in array) {
            count[i]++
        }
        val list = mutableListOf<Int>()
        for (i in 0 until count.size) {
            for (j in 1..count[i]) {
                list.add(i)
            }
        }
        return list.toIntArray()
    }
}

fun main() {
    val array = (1..100).shuffled().toIntArray()
    val res = isSorted(CountSort.sort(array, 100))
    asserts(res, true)
}