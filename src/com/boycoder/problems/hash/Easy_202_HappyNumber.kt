package com.boycoder.problems.hash

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc: https://leetcode-cn.com/problems/happy-number/
 */
object Easy_202_HappyNumber {
    fun isHappy(n: Int): Boolean {
        if (n < 0) return false

        val set = mutableSetOf<Int>()
        return check(n, set)
    }

    // tail recursion
    private fun check(n: Int, set: MutableSet<Int>): Boolean {
        if (n == 1) return true
        if (set.contains(n)) return false
        set.add(n)

        var sum = 0
        var num = n
        val digits = ArrayList<Int>()

        while (num > 0) {
            digits.add(num % 10)
            num = num / 10
        }

        var newNum = 0
        digits.forEach {
            newNum = it * it + newNum
        }

        return check(newNum, set)
    }
}