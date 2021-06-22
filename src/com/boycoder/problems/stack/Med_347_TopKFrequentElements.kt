package com.boycoder.problems.stack

import java.util.*

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Med_347_TopKFrequentElements {
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val map = hashMapOf<Int, Int>()
        val heap = PriorityQueue<Map.Entry<Int, Int>>(k, {o1, o2 ->
            // sorted by value, and min heap
            o2.value - o1.value
        })

        for (i in nums) {
            map.put(i, map.getOrDefault(i, 0) + 1)
        }

        map.entries.forEach {
            heap.offer(it)
        }

        val array = IntArray(k)
        for (i in 0 until k) {
            array[i] = heap.poll().key
        }

        return array;
    }
}