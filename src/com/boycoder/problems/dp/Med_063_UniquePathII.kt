package com.boycoder.problems.dp

import com.boycoder.utils.asserts

object Med_063_UniquePathII {
    private val BASE = 101
    private var cache = intArrayOf()

    private var obstacleGrid: Array<IntArray> = arrayOf()
    private var row = 0
    private var col = 0

    fun uniquePathsWithObstacles(obstacleGrid: Array<IntArray>): Int {
        if (obstacleGrid.size == 0) return 0

        this.obstacleGrid = obstacleGrid

        row = obstacleGrid.size
        col = obstacleGrid[0].size
        // cache = IntArray((row - 1) * BASE + (col - 1) + 1)

        // val state = BASE * (row - 1) + col - 1
        return path(row - 1, col - 1)
    }

    // dp
    private fun path(m: Int, n: Int): Int {
        if (obstacleGrid[0][0] == 1) {
            return 0
        }
        if (obstacleGrid[m][n] == 1) {
            return 0
        }

        // two dimension array
        val dp = Array<IntArray>(m + 1){ IntArray(n + 1) }

        // init first row
        for (i in 0..n) {
            if (obstacleGrid[0][i] == 1) {
                break
            }
            dp[0][i] = 1
        }

        for (j in 0..m) {
            if (obstacleGrid[j][0] == 1) {
                break
            }
            dp[j][0] = 1
        }

        // dp
        for (i in 1..m) {
            for (j in 1..n) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
                }
            }
        }

        return dp[m][n]
    }

    // recursion + state compress + cache
    private fun path2(state: Int): Int {

        val m = state / BASE
        val n = state % BASE

        if (obstacleGrid[0][0] == 1) {
            return 0
        }
        if (obstacleGrid[m][n] == 1) {
            return 0
        }

        if (m == 0) {
            val index = obstacleGrid[0].indexOf(1)
            if (index != -1 && n >= index) {
                return 0
            }

            return 1
        }

        if (n == 0) {
            var index = -1
            for (i in 0 until row) {
                if (obstacleGrid[i][0] == 1) {
                    index = i
                    break
                }
            }
            if (index != -1 && m >= index) {
                return 0
            }

            return 1
        }

        if (cache[state] != 0) {
            return cache[state]
        }

        val res = path2(m * BASE + n - 1) + path2((m - 1) * BASE + n)
        cache[state] = res

        return res
    }

    // out of time
    private fun path1(m: Int, n: Int): Int {
        if (obstacleGrid[0][0] == 1) {
            return 0
        }
        if (obstacleGrid[m][n] == 1) {
            return 0
        }

        if (m == 0) {
            val index = obstacleGrid[0].indexOf(1)
            if (index != -1 && n >= index) {
                return 0
            }

            return 1
        }

        if (n == 0) {
            var index = -1
            for (i in 0 until row) {
                if (obstacleGrid[i][0] == 1) {
                    index = i
                    break
                }
            }
            if (index != -1 && m >= index) {
                return 0
            }

            return 1
        }

        return path1(m, n - 1) + path1(m - 1, n)
    }
}

fun main() {
    val res = Med_063_UniquePathII.uniquePathsWithObstacles(arrayOf(intArrayOf(0,0),
            intArrayOf(1,1),
            intArrayOf(0,0)
    ))

    asserts(res, 0)
}