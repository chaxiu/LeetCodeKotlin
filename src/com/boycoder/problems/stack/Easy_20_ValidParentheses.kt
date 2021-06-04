package com.boycoder.problems.stack

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/4
 * @desc:
 */
object Easy_20_ValidParentheses {
    fun isValid(s: String): Boolean {
        val stk = ArrayDeque<Char>()
        val array = s.toCharArray()

        for (char in array) {
            if (isLeft(char)) {
                stk.addLast(char)
            } else {
                if (stk.isEmpty()) return false

                if (!isMatching(stk.removeLast(), char)) {
                    return false
                }
            }
        }

        return stk.isEmpty()
    }

    private fun isMatching(left: Char, right: Char): Boolean {
        return when (left) {
            '(' -> right == ')'
            '[' -> right == ']'
            '{' -> right == '}'
            else -> false
        }
    }

    private fun isLeft(char: Char): Boolean {
        return when(char) {
            '(' -> true
            '[' -> true
            '{' -> true
            else -> false
        }
    }
}

fun main() {
    val s = "()[]{}"
    val res = Easy_20_ValidParentheses.isValid(s)
    val res1 = Easy_20_ValidParentheses.isValid("([)]")
    val res2 = Easy_20_ValidParentheses.isValid("{[]}")
    asserts(res, true)
    asserts(res1, false)
    asserts(res2, true)
}