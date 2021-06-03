package com.boycoder.problems.dp

import com.boycoder.utils.asserts

object Med_063_UniquePathII {
    private var obstacleGrid: Array<IntArray> = arrayOf()
    private var row = 0
    private var col = 0

    fun uniquePathsWithObstacles(obstacleGrid: Array<IntArray>): Int {
        if (obstacleGrid.size == 0) return 0

        this.obstacleGrid = obstacleGrid
        row = obstacleGrid.size
        col = obstacleGrid[0].size
        return path(row - 1, col - 1)
    }

    private fun path(m: Int, n: Int): Int {
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

        return path(m, n - 1) + path(m - 1, n)
    }
}

fun main() {
    val res = Med_063_UniquePathII.uniquePathsWithObstacles(arrayOf(intArrayOf(0,0),
            intArrayOf(1,1),
            intArrayOf(0,0)
    ))

    asserts(res, 0)
}