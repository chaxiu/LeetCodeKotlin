package com.boycoder.problems.backtrack

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 *
 *     Ideal
 * For the given string, we check if it can be split into several substring, that every substring is palindrome.
 * We need a loop to help us split at index. For every index, we decide how many chars we split, and then save the split result and pass the remain into the recursion.
 *
 * eg:                aab         down recursion
 *                 /a  |aa   \        |
 *                ab   b     []       |
 *               a/\   |        when start index = size - 1
 *               b [] [aa, b]
 *               |
 *           [a, a, b]
 *
 * ------------->
 * Loop to decide how many char to split
 *
 */
object Med_131_PalindromePartition {
    private val list: MutableList<List<String>> = mutableListOf()
    private val path: MutableList<String> = mutableListOf()

    fun partition(s: String): List<List<String>> {
        part(s.toCharArray(), 0)
        return list
    }

    private fun part(array: CharArray, start: Int) {
        if (start == array.size) {
            list.add(path.toMutableList())
            return
        }

        // pruming
        if (start > array.size) {
            return
        }

        for (i in start until array.size) { // [start, size - 1]
            if (!isPalindrome(array, start, i)) {
                continue
            }
            path.add(array.slice(start..i).joinToString(""))
            part(array, i + 1)
            path.removeAt(path.size - 1)
        }
    }

    private fun isPalindrome(array: CharArray, left: Int, right: Int): Boolean {
        var left = left
        var right = right

        while (left < right) {
            if (array[left] != array[right]) {
                return false
            }
            left++
            right--
        }

        return true
    }
}