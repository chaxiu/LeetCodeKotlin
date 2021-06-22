package com.boycoder.problems.stack

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Hard_239_SlidingWindowMax {
    fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
        val queue = MaxQueue()
        val list = mutableListOf<Int>()

        // fill the window with [0, k -1]
        for (i in 0 until k) {
            queue.addLast(nums[i])
        }
        list.add(queue.max())

        for (i in k until nums.size) {
            queue.removeIfMatch(nums[i - k])
            queue.addLast(nums[i])

            list.add(queue.max())
        }

        return list.toIntArray()
    }

    fun maxSlidingWindow1(nums: IntArray, k: Int): IntArray {
        val queue = MaxQueue()
        val list = mutableListOf<Int>()

        var left = 0
        for (right in 0 until nums.size) {
            queue.addLast(nums[right])
            if (right < k - 1) {
                continue
            }

            // window full
            if (right == k - 1) {
                list.add(queue.max())
            } else {
                // bigger
                val leftEdge = nums[right - k]
                if (leftEdge == queue.max()) {
                    queue.removeFront()
                    list.add(queue.max())
                } else {
                    list.add(queue.max())
                }
            }
        }

        return list.toIntArray()
    }
}

/**
This is a queue only keep the max value at the front
5, 5, 4, 3, 2
 */
class MaxQueue {
    val queue = ArrayDeque<Int>()

    fun addLast(x: Int) {
        if (queue.isEmpty()) {
            queue.addLast(x)
        } else {
            while (!queue.isEmpty() && queue.last() < x) {
                queue.removeLast()
            }
            queue.addLast(x)
        }
    }

    fun removeFront(): Int {
        return queue.removeFirst()
    }

    fun removeIfMatch(x: Int) {
        if (!queue.isEmpty() && x == max()) {
            removeFront()
        }
    }

    fun max(): Int {
        return queue.first()
    }

}