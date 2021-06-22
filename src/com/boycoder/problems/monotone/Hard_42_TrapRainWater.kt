package com.boycoder.problems.monotone

import com.boycoder.utils.asserts
import java.util.*

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Hard_42_TrapRainWater {
    private var array = intArrayOf()
    fun trap(height: IntArray): Int {
        array = height
        return count1()
    }

    /**
     * Monotone stack
     */
    private fun count1(): Int {
        var sum = 0
        // put index
        val stk = LinkedList<Int>()

        for (i in 0 until array.size) {
            while (!stk.isEmpty() && array[stk.getLast()] < array[i]) {
                val cur = stk.removeLast()
                if (stk.isEmpty()) break

                val left = stk.getLast()
                val right = i
                val low = Math.min(array[left], array[right])
                if (low > array[cur]) {
                    sum = sum + (right - left - 1) * (low - array[cur])
                }
            }
            stk.addLast(i)
        }

        return sum
    }

    /**
     * Brute Force
     *
     * Count every col of water and then sum then up.
     */
    fun count(): Int {
        var sum = 0

        for (i in 0 until array.size) {
            if (i == 0 || i == array.size - 1) {
                continue
            }

            // highest index to the left and right
            var left = i
            var right = i

            // here can be optimized by dp
            for (j in i - 1 downTo 0) {
                if (array[j] > array[left]) {
                    left = j
                }
            }

            // here can be optimized by dp
            for (k in i + 1 until array.size) {
                if (array[k] > array[right]) {
                    right = k
                }
            }

            val low = Math.min(array[left], array[right])

            if (low > array[i]) {
                sum = sum + (low - array[i])
            }
        }

        return sum
    }
}

fun main() {
    val res = Hard_42_TrapRainWater.trap(intArrayOf(0,1,0,2,1,0,1,3,2,1,2,1))
    asserts(res, 6)
    val res1 = Hard_42_TrapRainWater.trap(intArrayOf(4,2,0,3,2,5))
    asserts(res1, 9)
}