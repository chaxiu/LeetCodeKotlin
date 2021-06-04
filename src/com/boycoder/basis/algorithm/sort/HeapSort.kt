package com.boycoder.basis.algorithm.sort

import com.boycoder.basis.datastructure.BinaryHeap
import com.boycoder.utils.asserts
import com.boycoder.utils.isSorted

/**
 * @Author: zhutao
 * @datetime: 2021/6/4
 * @desc:
 */
object HeapSort {
    fun sort(array: IntArray): IntArray {

        // min heap
        val heap = BinaryHeap{o1, o2 -> o2 -o1}

        for (i in array) {
            heap.add(i)
        }

        val list = mutableListOf<Int>()
        repeat(heap.size()) {
            list.add(heap.remove())
        }

        return list.toIntArray()
    }
}

fun main() {
    val array = (1..100).shuffled().toIntArray()
    val res = isSorted(HeapSort.sort(array))
    asserts(res, true)
}