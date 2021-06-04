package com.boycoder.problems.string

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/4
 * @desc:
 */
object Easy_344_ReverseString {

    fun reverse(array: CharArray) {
        if (array.isEmpty()) return

        var left = 0
        var right = array.size - 1

        while (left < right) {
            val temp = array[left]
            array[left] = array[right]
            array[right] = temp
            left++
            right--
        }
    }
}

fun main() {
    val array = charArrayOf('h', 'e', 'l', 'l', 'o')
    Easy_344_ReverseString.reverse(array)

    var res = true
    val array1 = charArrayOf('o', 'l', 'l', 'e', 'h')
    for (i in 0 until array.size) {
        if (array1[i] != array[i]) {
            res = false
        }
    }

    asserts(res, true)
}