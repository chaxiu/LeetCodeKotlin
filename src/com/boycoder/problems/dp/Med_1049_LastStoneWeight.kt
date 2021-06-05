package com.boycoder.problems.dp

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/5
 * @desc: https://leetcode-cn.com/problems/last-stone-weight-ii/
 */
object Med_1049_LastStoneWeight {
    private var capacity = 0
    private var stones = intArrayOf()
    private var max = Int.MIN_VALUE
    private var memo = hashMapOf<String, Int>()
    fun lastStoneWeightII(stones: IntArray): Int {
        this.stones = stones
        val sum = stones.sumBy { it }
        capacity = sum / 2
        val max = lastWeight(stones.size - 1, capacity)
        return (sum - max) - max
    }

    /**
     * After optimize the space complexity
     * We can still optimize the time complexity:
     * for (j in capacity downTo 0) {
     *      if (j >= stones[i]) {
     *          dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i])
     *      }
     * }
     *              | Like this
     * for (j in capacity downTo stones[i]) {
     *      dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i])
     * }
     *
     * Why? We just move the unnecessary if check into the for loop.
     */
    private fun lastWeight(num: Int, capacity: Int): Int {
        val dp = IntArray(capacity + 1)

        // init when i == 0
        for (j in 0..capacity) {
            if (j >= stones[0]) {
                dp[j] = stones[0]
            }
        }

        // dp
        for (i in 1..num) {
            // only down to stones[i]
            for (j in capacity downTo stones[i]) {
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i])
            }
        }

        return dp[capacity]
    }

    /**
     * dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weights[i]] + values[i])
     *
     * In this case, the result of row i only depends on the row i - 1.
     * So, we can try to reuse the result, then space complexity become O(Capacity)
     * More details is here: [Med_416_SubSetSum.canPart]
     *
     * Although the code we write is one dimension array,
     * we should keep two demension array in our mind.
     */
    private fun lastWeight4(num: Int, capacity: Int): Int {
        val dp = IntArray(capacity + 1)

        // init when i == 0
        for (j in 0..capacity) {
            if (j >= stones[0]) {
                dp[j] = stones[0]
            }
        }

        // dp
        for (i in 1..num) {
            for (j in capacity downTo 0) {
                if (j >= stones[i]) {
                    dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i])
                }
                // This code is unnecessary,
                // but we should know it reuse the result in row i - 1
//                else {
//                    dp[j] = dp[j]
//                }
            }
        }

        return dp[capacity]
    }

    /**
     * Use 0-1 Knapsack problems Solutions
     * dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weights[i]] + values[i])
     * In this case, weights == values == stones
     */
    private fun lastWeight3(num: Int, capacity: Int): Int {
        val dp = Array(num + 1) { IntArray(capacity + 1) }

        // init when capacity == 0
        for (i in 0..num) {
            dp[i][0] = 0
        }

        // init when i == 0
        for (j in 0..capacity) {
            if (j >= stones[0]) {
                dp[0][j] = stones[0]
            }
        }

        // dp
        for (i in 1..num) {
            for (j in 1..capacity) {
                if (j >= stones[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - stones[i]] + stones[i])
                } else {
                    dp[i][j] = dp[i - 1][j]
                }
            }
        }

        return dp[num][capacity]
    }

    /**
     * To cache the result of back track.
     * We need to return the max capacity.
     */
    private fun lastWeight2(cur: Int, start: Int): Int {
        if (cur > capacity) {
            return Int.MIN_VALUE
        }

        if (start == stones.size) {
            return cur
        }

        if (start > stones.size) {
            return Int.MIN_VALUE
        }

        val key = "${cur}.$start"
        if (memo.containsKey(key)) {
            return memo.getOrDefault(key, Int.MIN_VALUE)
        }

        // choose
        val choose = lastWeight2(cur + stones[start], start + 1)
        // not choose
        val notChoose = lastWeight2(cur, start + 1)
        val res = Math.max(choose, notChoose)
        memo[key] = res
        return res
    }

    /**
     * back track: out of time
     *
     * In the capacity, how can we get the combination sum get close to the capacity.
     * When we get the max weight of capacity.
     * return (sum - max) - max
     */
    private fun lastWeight1(cur: Int, start: Int) {
        if (cur > capacity) {
            return
        }
        max = Math.max(cur, max)

        if (start >= stones.size) {
            return
        }

        // choose
        lastWeight1(cur + stones[start], start + 1)
        // not choose
        lastWeight1(cur, start + 1)
    }
}

fun main() {
    val res = Med_1049_LastStoneWeight.lastStoneWeightII(intArrayOf(31, 26, 33, 21, 40))
    asserts(res, 5)
}