package com.boycoder.problems.monotone

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
class Easy_155_MinStack {

    /** initialize your data structure here. */
    val array = mutableListOf<Int>()
    val minArray = mutableListOf<Int>()

    fun push(x: Int) {
        array.add(x)
        val top = minArray.getOrNull(minArray.size - 1)?: Int.MAX_VALUE
        val min = Math.min(top, x)
        minArray.add(min)
    }

    fun pop() {
        array.removeAt(array.size - 1)
        minArray.removeAt(minArray.size - 1)
    }

    fun top(): Int {
        return array.get(array.size - 1)
    }

    fun getMin(): Int {
        return minArray.get(minArray.size - 1)
    }
}