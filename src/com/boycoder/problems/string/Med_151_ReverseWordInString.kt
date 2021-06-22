package com.boycoder.problems.string

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc: https://leetcode-cn.com/problems/reverse-words-in-a-string/
 */
object Med_151_ReverseWordInString {
    fun reverseWords(s: String): String {
        var string = s.trim()

        var array = string.toCharArray()
        // remove space
        var left = 0
        var right = 0

        while (left <= right && right < array.size) {
            if (array[right] != ' ') {
                array[left] = array[right]
                left++
                right++
            } else {
                while (array[right + 1] == ' ') {
                    right++
                }
                array[left] = array[right]
                left++
                right++
            }
        }
        if (left < array.size) {
            array = array.slice(0..left-1).toCharArray()
        }

        // reverse the whole array
        reverse(array, 0, array.size - 1)

        left = 0
        right = 0
        while (right < array.size) {
            if (right == array.size - 1) {
                reverse(array, left, right)
                right++
                left = right
            } else if (array[right + 1] == ' ') {
                reverse(array, left, right)
                right = right + 2
                left = right
            } else {
                right++
            }
        }

        return array.joinToString("")
    }

    private fun reverse(array: CharArray, i: Int, j: Int) {
        var left = i
        var right = j

        while (left <= right) {
            val temp = array[left]
            array[left] = array[right]
            array[right] = temp
            left++
            right--
        }
    }
}