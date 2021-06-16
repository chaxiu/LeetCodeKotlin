package com.boycoder.problems.dp.knapsack

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/15
 * @desc: https://leetcode-cn.com/problems/perfect-squares/
 * This problem is similar to [Med_322_CoinChange]
 */
object Med_279_PerfectSquares {
    private var min = Int.MAX_VALUE
    private val memo = hashMapOf<Int, Int>()

    fun numSquares(n: Int): Int {
        memo.clear()
        min = find5(n)
        return if (min == Int.MAX_VALUE) -1 else min
    }

    /**
     * dp[1] means to get target i, min square num count is dp[i]
     */
    private fun find5(target: Int): Int {
        val dp = IntArray(target + 1) { Int.MAX_VALUE }
        // init
        dp[0] = 0

        for (i in 0..target) {
            // This loop will calculate dp[1] dp[2] dp[3]..dp[target]
            var j = 1
            while (j * j <= i) {
                dp[i] = Math.min(dp[i], dp[i - j*j] + 1)
                j++
            }
        }

        return dp[target]
    }

    /**
     * bfs + pruning
     */
    private fun find4(target: Int): Int {
        var sum = 0
        val queue = ArrayDeque<Int>()
        var level = 0
        queue.addLast(0)
        val visited = hashSetOf<Int>()
        visited.add(0)

        while (!queue.isEmpty()) {
            val size = queue.size
            println(queue.joinToString())
            level++
            for (i in 0 until size) {
                sum = queue.removeFirst()
                for (j in 1..target) {
                    val value = sum + j * j

                    if (value == target) {
                        return level
                    }

                    if (value > target) {
                        break
                    }

                    if (!visited.contains(value)) {
                        visited.add(value)
                        queue.addLast(value)
                    }
                }
            }
        }

        return Int.MAX_VALUE
    }

    /**
     * bfs
     * This problem become a problem that find the shortest path in graph
     * The square num is node of the graph / tree
     */
    private fun find3(target: Int): Int {
        var sum = 0
        val queue = ArrayDeque<Int>()
        var level = 0
        queue.addLast(0)

        while (!queue.isEmpty()) {
            val size = queue.size
            println(queue.joinToString())
            level++
            for (i in 0 until size) {
                sum = queue.removeFirst()
                for (j in 1..target) {
                    val value = sum + j * j

                    if (value == target) {
                        return level
                    }

                    if (value > target) {
                        break
                    }

                    queue.addLast(value)
                }
            }
        }

        return -1
    }

    /**
     * dfs + cache
     */
    private fun find2(target: Int): Int {
        if (target == 0) {
            return 0
        }

        if (target < 0) {
            return Int.MAX_VALUE
        }

        if (memo.containsKey(target)) {
            return memo.getOrDefault(target, Int.MAX_VALUE)
        }

        var res = Int.MAX_VALUE
        for (i in 1..Math.sqrt(target.toDouble()).toInt()) {
            var min = find2(target - i * i)
            if (min != Int.MAX_VALUE) {
                min++
            }
            res = Math.min(res, min)
        }

        memo[target] = res

        return res
    }

    /**
     * dfs to add cache
     * time out
     */
    private fun find1(target: Int): Int {
        if (target == 0) {
            return 0
        }

        if (target < 0) {
            return Int.MAX_VALUE
        }

        var res = Int.MAX_VALUE
        for (i in 1..Math.sqrt(target.toDouble()).toInt()) {
            var min = find1(target - i * i)
            if (min != Int.MAX_VALUE) {
                min++
            }
            res = Math.min(res, min)
        }

        return res
    }

    /**
     * dfs
     * time out
     */
    private fun find(size: Int, target: Int) {
        if (target == 0) {
            if (size < min) {
                min = size
            }
            return
        }

        if (size > min || target < 0) {
            return
        }

        for (i in 1..Math.sqrt(target.toDouble()).toInt()) {
            find(size + 1, target - i * i)
        }
    }
}

fun main() {
    val res = Med_279_PerfectSquares.numSquares(12)
    asserts(res, 3)
    val res1 = Med_279_PerfectSquares.numSquares(13)
    asserts(res1, 2)
}