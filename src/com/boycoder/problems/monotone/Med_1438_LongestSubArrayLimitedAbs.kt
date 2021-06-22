package com.boycoder.problems.monotone

import java.util.*

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Med_1438_LongestSubArrayLimitedAbs {
    fun longestSubarray(nums: IntArray, limit: Int): Int {
        // 4, 3, 2, 1
        val maxQueue = LinkedList<Int>()
        // 1, 2, 3, 4
        val minQueue = LinkedList<Int>()
        val len = nums.size
        var left = 0
        var right = 0
        var max = 0

        while (right < len) {
            while (!maxQueue.isEmpty() && maxQueue.getLast() < nums[right]) {
                maxQueue.removeLast()
            }

            while (!minQueue.isEmpty() && minQueue.getLast() > nums[right]) {
                minQueue.removeLast()
            }

            maxQueue.addLast(nums[right])
            minQueue.addLast(nums[right])

            while (maxQueue.getFirst() - minQueue.getFirst() > limit) {
                if (maxQueue.getFirst() == nums[left]) maxQueue.removeFirst()
                if (minQueue.getFirst() == nums[left]) minQueue.removeFirst()
                left++
            }

            max = Math.max(max, right - left + 1)
            right++
        }

        return max
    }
}