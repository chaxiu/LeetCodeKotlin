package com.boycoder.problems.stack

import java.util.*

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Easy_225_ImpStackUseQueue {

    /** Initialize your data structure here. */
    val queue = LinkedList<Int>()
    val backup = LinkedList<Int>()

    /** Push element x onto stack. */
    fun push(x: Int) {
        queue.addLast(x)
    }

    /** Removes the element on top of the stack and returns that element. */
    fun pop(): Int {
        return popByType(true)
    }

    private fun popByType(isOut: Boolean): Int {
        var value = 0
        while (!queue.isEmpty()) {
            if (queue.size == 1) {
                value = queue.removeFirst()
                if (!isOut) {
                    backup.addLast(value)
                }
            } else {
                backup.addLast(queue.removeFirst())
            }
        }

        while (!backup.isEmpty()) {
            queue.addLast(backup.removeFirst())
        }

        return value
    }

    /** Get the top element. */
    fun top(): Int {
        return popByType(false)
    }

    /** Returns whether the stack is empty. */
    fun empty(): Boolean {
        return queue.isEmpty() && backup.isEmpty()
    }
}