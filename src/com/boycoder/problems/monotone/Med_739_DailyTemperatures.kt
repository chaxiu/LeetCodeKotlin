package com.boycoder.problems.monotone

import com.boycoder.utils.assertCollection
import java.util.*

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Med_739_DailyTemperatures {
    private var array = intArrayOf()

    fun dailyTemperatures(temperatures: IntArray): IntArray {
        array = temperatures
        return find1()
    }

    /**
     * Brute Force
     */
    private fun find(): IntArray {
        val res = IntArray(array.size)

        // calculate every index
        for (i in 0 until array.size) {
            for (j in i + 1 until array.size) {
                if (array[j] > array[i]) {
                    res[i] = j - i
                    break
                }
            }
        }

        return res
    }

    private fun find1(): IntArray {
        val res = IntArray(array.size)
        // 4, 3, 3, 2, 1
        val stk = ArrayDeque<Int>()

        for (i in 0 until array.size) {
            while (!stk.isEmpty() && array[stk.getLast()] < array[i]) {
                val index = stk.removeLast()
                res[index] = i - index
            }

            stk.addLast(i)
        }

        return res
    }
}

fun main() {
    val res = Med_739_DailyTemperatures.dailyTemperatures(intArrayOf(73, 74, 75, 71, 69, 72, 76, 73))
    assertCollection(res.asList(), intArrayOf(1, 1, 4, 2, 1, 1, 0, 0).asList())
}