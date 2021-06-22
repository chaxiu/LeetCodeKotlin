package com.boycoder.basis.datastructure

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:  Quick Find
 *
 * 1. find      O(1)
 * 2. union     O(n)
 */
class UnionFindQuickFind(capacity: Int): IUnionFind {

    private var parents: IntArray

    init {
        if (capacity <= 0) {
            throw IllegalArgumentException("Capacity must > 0")
        }
        parents = IntArray(capacity)

        // init, let every element point to itself
        for (i in 0 until capacity) {
            parents[i] = i
        }
    }

    override fun find(element: Int): Int {
        rangeCheck(element)

        return parents[element]
    }

    /**
     * Quick find.
     *
     *          2          4
     *        0   1        3
     *                 |
     *                 |
     *                     4
     *                 0  1  2  3
     *
     * Merge element1 and it's children into element2 parents
     * This way, find will be really quick, but union is slow.
     */
    override fun union(element1: Int, element2: Int) {
        val p1 = find(element1)
        val p2 = find(element2)

        // already union
        if (p1 == p2) return

        for (i in 0 until parents.size) {
            if (parents[i] == p1) {
                parents[i] = p2
            }
        }
    }

    override fun isSame(element1: Int, element2: Int): Boolean {
        return find(element1) == find(element2)
    }

    private fun rangeCheck(element: Int) {
        if (element < 0 || element >= parents.size) {
            throw IllegalArgumentException("Out of range")
        }
    }
}

interface IUnionFind {
    fun find(element: Int): Int
    fun union(element1: Int, element2: Int)
    fun isSame(element1: Int, element2: Int): Boolean
}