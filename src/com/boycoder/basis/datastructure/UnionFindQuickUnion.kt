package com.boycoder.basis.datastructure

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc: Quick Union
 *
 * 1. find    O(logn)
 * 2. union   O(logn)
 */
class UnionFindQuickUnion(capacity: Int): IUnionFind {

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

    /**
     * For Quick Union
     * Find is slower.
     */
    override fun find(element: Int): Int {
        rangeCheck(element)

        var temp = element
        while (temp != parents[temp]) {
            temp = parents[temp]
        }

        return temp
    }

    /**
     * Quick Union
     *
     * This case, we just let element1's parent point to element2's parent
     */
    override fun union(element1: Int, element2: Int) {
        val p1 = find(element1)
        val p2 = find(element2)

        // already union
        if (p1 == p2) return
        parents[p1] = p2
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