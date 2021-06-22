package com.boycoder.problems.monotone

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc: https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof/
 *
 * This data structure should work as a normal queue,
 * and it can get the max value O(n)
 *
 * So, we should use a normal queue to hold the elements.
 * and another data structure to hold the max elements.
 */
class Med_MaxQueue() {

    private var queue: ArrayDeque<Int>
    private var max: ArrayDeque<Int>

    init {
        queue = ArrayDeque()
        max = ArrayDeque()
    }

    /**
     * get max
     */
    fun max_value(): Int {
        if (max.size == 0) {
            return -1
        }

        return max.first()
    }

    /**
     * add last
     */
    fun push_back(value: Int) {
        queue.addLast(value)

        if (max.size == 0) {
            max.addLast(value)
            return
        }

        // maintain the max queue
        // 4 2 1  add 3
        // 4      add 3
        while (max.size > 0 && max.last() < value) {
            max.removeLast()
        }

        max.addLast(value)
    }

    /**
     * remove first
     */
    fun pop_front(): Int {
        if (queue.size == 0) {
            return -1
        }

        val res = queue.removeFirst()
        if (res == max.first()) {
            max.removeFirst()
        }

        return res
    }
}

fun main() {
    val queue = Med_MaxQueue()
    queue.push_back(1)
    queue.push_back(2)
    println(queue.max_value())
    queue.pop_front()
    println(queue.max_value())
}