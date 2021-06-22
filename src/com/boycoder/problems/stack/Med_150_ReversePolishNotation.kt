package com.boycoder.problems.stack

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Med_150_ReversePolishNotation {
    fun evalRPN(tokens: Array<String>): Int {
        val stk = ArrayDeque<String>()

        for (s in tokens) {
            if (!isOprator(s)) {
                stk.addLast(s)
            } else {
                val res = calculate(stk.removeLast().toInt(), stk.removeLast().toInt(), s)
                stk.addLast(res.toString())
            }
        }

        return stk.removeLast().toInt()
    }

    private fun calculate(b: Int, a: Int, oprator: String): Int {
        return when (oprator) {
            "+" -> a + b
            "-" -> a - b
            "*" -> a * b
            "/" -> a / b
            else -> { 0 } // throw exception
        }
    }

    private fun isOprator(s: String): Boolean {
        return s == "+" || s == "-" || s == "*" || s == "/"
    }
}