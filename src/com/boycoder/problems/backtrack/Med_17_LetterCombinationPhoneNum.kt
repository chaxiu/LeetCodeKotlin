package com.boycoder.problems.backtrack

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Med_17_LetterCombinationPhoneNum {
    private val list: MutableList<String> = mutableListOf()
    private val path: MutableList<Char> = mutableListOf()

    fun letterCombinations(digits: String): List<String> {
        if (digits.isBlank()) return list
        combine(digits.toCharArray(), 0)
        return list
    }

    private fun combine(digits: CharArray, index: Int) {
        if (path.size == digits.size) {
            list.add(path.joinToString(""))
            return
        }

        if (index >= digits.size) return

        val array = getChars(digits[index])

        for (char in array) {
            path.add(char)
            combine(digits, index + 1)
            // careful
            // for test case: "999"
            // path.remove(char) wont pass
            // so for this case we need remove last
            path.removeAt(path.size - 1)
        }
    }

    private inline fun getChars(num: Char): CharArray {
        return when (num) {
            '2' -> charArrayOf('a', 'b', 'c')
            '3' -> charArrayOf('d', 'e', 'f')
            '4' -> charArrayOf('g', 'h', 'i')
            '5' -> charArrayOf('j', 'k', 'l')
            '6' -> charArrayOf('m', 'n', 'o')
            '7' -> charArrayOf('p', 'q', 'r', 's')
            '8' -> charArrayOf('t', 'u', 'v')
            '9' -> charArrayOf('w', 'x', 'y', 'z')
            else -> return charArrayOf()
        }
    }
}