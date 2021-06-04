package com.boycoder.problems.greedy

/**
 * @Author: zhutao
 * @datetime: 2021/6/4
 * @desc:
 */
object Easy_445_AssignCookie {

    fun findContentChildren(g: IntArray, s: IntArray): Int {
        g.sort()
        s.sort()
        var i = g.size - 1
        var j = s.size - 1

        while (i >= 0 && j >= 0) {
            if (g[i] <= s[j]) {
                i--
                j--
            } else {
                i--
            }
        }
        return s.size - 1 - j
    }
}