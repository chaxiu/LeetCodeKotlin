package com.boycoder.problems.stack

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Easy_232_ImpQueueUseStack {
    /** Initialize your data structure here. */
    val outStk = ArrayDeque<Int>()
    val inStk = ArrayDeque<Int>()

    /** Push element x to the back of queue. */
    fun push(x: Int) {
        inStk.addLast(x)
    }

    /** Removes the element from in front of queue and returns that element. */
    fun pop(): Int {
        if (empty()) {
            return 0 // throw exception
        }

        // make sure in stack empty
        while (!inStk.isEmpty()) {
            outStk.addLast(inStk.removeLast())
        }

        return outStk.removeLast()
    }

    /** Get the front element. */
    fun peek(): Int {
        var value = pop()
        outStk.addLast(value)

        return value
    }

    /** Returns whether the queue is empty. */
    fun empty(): Boolean {
        return inStk.isEmpty() && outStk.isEmpty()
    }
}