package com.boycoder.problems.backtrack

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 *
 * Ideal
 * For a n*n chessboard, we need to take n step.
 * And for every step, we can choose i = [0, n); j = [0, n)
 *
 * We use loop go through the col, and recursion go through the row,
 * and for every step, we check validation.
 */
object Hard_51_NQueens {
    private val list: MutableList<MutableList<String>> = mutableListOf()
    private val path: MutableList<Pair<Int, Int>> = mutableListOf()
    private var n = 0

    fun solveNQueens(n: Int): List<List<String>> {
        this.n = n
        backtrack(0)
        return list
    }

    private fun backtrack(row: Int) {
        if (path.size == n) {
            saveResult()
            return
        }

        for (col in 0 until n) {
            if (!isValid(row, col)) {
                continue
            }

            path.add(Pair(row, col))
            backtrack(row + 1)
            path.removeAt(path.size - 1)
        }

    }

    private fun saveResult() {
        path.sortBy{ it.first }
        val tempList = mutableListOf<String>()
        for (i in 0 until n) {
            val pair = path[i]
            var temp = StringBuilder()
            for (j in 0 until n) {
                if (j == pair.second) {
                    temp.append("Q")
                } else {
                    temp.append(".")
                }
            }
            tempList.add(temp.toString())
        }

        list.add(tempList)
    }

    private fun isValid(row: Int, col: Int): Boolean {
        for (pair in path) {
            if (!isPairValid(pair, row, col)) {
                return false
            }
        }

        return true
    }

    private fun isPairValid(pair: Pair<Int, Int>, row: Int, col: Int): Boolean {
        if (pair.first == row || pair.second == col) {
            return false
        }

        val x = Math.abs(pair.second - col)
        val y = Math.abs(pair.first - row)
        if (x == y) {
            return false
        }

        return true
    }
}