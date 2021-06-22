package com.boycoder.problems.backtrack

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Med_216_CombinationSumIII {
    private val list: MutableList<List<Int>> = mutableListOf()
    private val path: MutableList<Int> = mutableListOf()
    fun combinationSum3(k: Int, n: Int): List<List<Int>> {
        combine(k, n, n, 1)
        return list
    }

    // with pruming
    private fun combine(k: Int, n: Int, remain: Int, start: Int) {
        if (remain < 0) return

        if (path.size == k) {
            if (remain == 0) {
                list.add(path.toMutableList())
            }

            return
        }

        // conner case: start > remain
        if (start > remain) return

        // conner case: k = 3, start = 8, path.size = 1
        if ((k - path.size - 1) > 9 - start) return

        for (i in start..9) {
            path.add(i)
            combine(k, n, remain - i, i + 1)
            path.remove(i)
        }
    }

    private fun combineNoPruming(k: Int, n: Int, remain: Int, start: Int) {
        if (remain < 0) return

        if (path.size == k) {
            if (remain == 0) {
                list.add(path.toMutableList())
            }

            return
        }

        for (i in start..9) {
            path.add(i)
            combine(k, n, remain - i, i + 1)
            path.remove(i)
        }
    }
}