package com.boycoder.problems.backtrack

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 *
 * Ideal
 * This problem is a little bit like palindrome-partitioning
 * Back tracking can be use in partitioning
 *
 * For the loop, we decide how many char to split,
 * from 1 to 3, check valide, then we save the path,
 * and pass the rest of string into the recursion i + 1.
 *
 * For the recursion, we check path.size == 4 && start == string.size
 * "25525511135"
 *
 */
object Med_93_RestoreIPAddress {
    private val list: MutableList<String> = mutableListOf()
    private val path: MutableList<String> = mutableListOf()

    fun restoreIpAddresses(s: String): List<String> {
        split(s, 0)
        return list
    }

    private fun split(s: String, start: Int) {
        if (path.size == 4) {
            if (start == s.length) {
                // all string are used
                list.add(path.joinToString("."))
            }
            return
        }

        // pruming
        // if there is too many num left
        // such as:
        //      s.length == 13
        if ((4 - path.size) * 3 < (s.length - start)) {
            return
        }

        // s = "25525511135" s.length = 11
        // start = 9
        val end = Math.min(start + 3, s.length)
        for (i in start until end) {
            val temp = s.slice(start..i)
            if (!isValid(temp)) continue

            path.add(temp)
            split(s, i + 1)
            path.removeAt(path.size - 1)
        }
    }

    private fun isValid(s: String): Boolean {
        if (s.startsWith("0")) {
            // only zero can start with 0
            return s.length == 1
        } else {
            val num = s.toInt()
            return num in 0..255
        }
    }
}