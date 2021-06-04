package com.boycoder.problems.backtrack

/**
 * @Author: zhutao
 * @datetime: 2021/6/4
 * @desc:
 *
Ideal

For 1...n K combination, we pick one for loop, one for recursion
If we use n to loop, then we recur until we collet k elements
1..n is not very good for recursion

eg: n = 3, k = 2

                         [1,2,3]
                    1/     2|      \3
                  [2,3]    [3]      []
                 2/   \3    |3
               (1,2) (1,3)  (2,3)


 */
object Med_077_Combinations {
    val list: MutableList<List<Int>> = mutableListOf()
    val path: MutableList<Int> = mutableListOf()
    var n = 0

    fun combine(n: Int, k: Int): List<List<Int>> {
        this.n = n
        dfs(path, 1, k)
        return list
    }

    // Another way for combination
    private fun dfs(path: MutableList<Int>, start: Int, k: Int) {
        if (path.size == k) {
            list.add(path)
            return
        }

        if (path.size > k) {
            return
        }

        if (start > n) {
            return
        }

        dfs(path.toMutableList().also{ it.add(start) }, start + 1, k)
        dfs(path.toMutableList(), start + 1, k)
    }

    // pruming got better performance
    private fun doCombinePruming(n: Int, start: Int, k: Int) {

        if (path.size == k) {
            // careful toMutableList()
            list.add(path.toMutableList())
            return
        }

        // conner case: k = 2, start = 2, n = 3, path.size = 0
        if ((k - path.size - 1) > (n - start)) return

        // careful start..n
        // every time start, we can avoid duplication
        for (i in start..n) {
            // conner case: k = 2, i = 2, n = 3, path.size = 0
            if ((k - path.size - 1) > (n - i)) continue

            path.add(i)
            doCombinePruming(n, i + 1, k)
            path.remove(i)
        }
    }

    private fun doCombine(n: Int, start: Int, k: Int) {

        if (path.size == k) {
            // careful toMutableList()
            list.add(path.toMutableList())
            return
        }

        // careful start..n
        // every time start, we can avoid duplication
        for (i in start..n) {
            path.add(i)
            doCombine(n, i + 1, k)
            path.remove(i)
        }
    }
}