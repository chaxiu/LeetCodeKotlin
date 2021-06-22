package com.boycoder.problems.stack

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Easy_1047_RemoveDuplicate {
    fun removeDuplicates(s: String): String {
        val stk = ArrayDeque<Char>()
        val array = s.toCharArray()

        for (char in array) {
            if (stk.isEmpty()) {
                stk.addLast(char)
            } else if (stk.last() == char) {
                stk.removeLast()
            } else if (stk.last() != char) {
                stk.addLast(char)
            }
        }

        return stk.joinToString("")
    }
}