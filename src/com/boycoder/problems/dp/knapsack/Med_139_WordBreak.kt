package com.boycoder.problems.dp.knapsack

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/16
 * @desc: https://leetcode-cn.com/problems/word-break/
 * 1. dfs
 * 2. bfs
 * 3. dp
 */
object Med_139_WordBreak {
    private var set = hashSetOf<String>()
    private var s = ""

    private val memo = hashMapOf<Int, Boolean>()
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        set = wordDict.toHashSet()
        this.s = s
        this.memo.clear()
        return canBreak4()
    }

    /**
     * Bottom to top.
     *
     * dp[i] means: for range [0, i -1], the substring can be break in set.
     *
     */
    private fun canBreak4(): Boolean {
        val dp = BooleanArray(s.length + 1)
        // init
        dp[0] = true

        for (i in 1..s.length) {
            // This loop will calculate dp[1], dp[2], dp[3].. dp[s.length]
            for (j in 0 until i) {
                // This loop break [0, i - 1] into [0, j] and [j, i -1]
                val prefix = s.slice(j until i)
                if (set.contains(prefix) && dp[j]) {
                    dp[i] = true
                }
            }
        }

        return dp[s.length]
    }

    /**
     * bfs + pruning
     */
    private fun canBreak3(): Boolean {
        val queue = ArrayDeque<Int>()
        // start from index 0
        queue.addLast(0)
        val visited = BooleanArray(s.length + 1)


        while (!queue.isEmpty()) {
            val start = queue.removeFirst()
            // It means we already to the end
            if (start == s.length) {
                return true
            }

            // pruning
            if (visited[start]) continue
            visited[start] = true

            for (i in start until s.length) {
                val prefix = s.slice(start..i)
                if (set.contains(prefix)) {
                    queue.addLast(i + 1)
                }
            }
        }

        // Reach here means don't match
        return false
    }

    /**
     * bfs
     * The ideal of bfs is:
     * Go through the string, [0, cur], until it is in the set.
     * Then put the (cur + 1) into the queue, and find [start, cur],
     * check [start, cur] into the set until the end of String.
     */
    private fun canBreak2(): Boolean {
        val queue = ArrayDeque<Int>()
        // start from index 0
        queue.addLast(0)

        while (!queue.isEmpty()) {
            val start = queue.removeFirst()
            // It means we already to the end
            if (start == s.length) {
                return true
            }

            for (i in start until s.length) {
                val prefix = s.slice(start..i)
                if (set.contains(prefix)) {
                    queue.addLast(i + 1)
                }
            }
        }

        // Reach here means dont match
        return false
    }

    /**
     * dfs + cache
     */
    private fun canBreak1(start: Int): Boolean {
        if (start == s.length) {
            return true
        }

        if (memo.containsKey(start)) {
            return memo.getOrDefault(start, false)
        }

        for(i in start until s.length) {
            val prefix = s.slice(start..i)
            if (set.contains(prefix) && canBreak(i + 1)) {
                memo[start] = true
                return true
            }
        }
        memo[start] = false
        return false
    }

    /**
     * dfs
     */
    private fun canBreak(start: Int): Boolean {
        if (start == s.length) {
            return true
        }

        for(i in start until s.length) {
            val prefix = s.slice(start..i)
            if (set.contains(prefix) && canBreak(i + 1)) {
                return true
            }
        }
        return false
    }
}

fun main() {
    val res = Med_139_WordBreak.wordBreak("leetcode", listOf("leet", "code"))
    asserts(res, true)
    val res1 = Med_139_WordBreak.wordBreak("applepenapple", listOf("apple", "pen"))
    asserts(res1, true)
}