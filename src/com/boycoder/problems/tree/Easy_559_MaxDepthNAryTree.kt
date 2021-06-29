package com.boycoder.problems.tree

/**
 * @Author: zhutao
 * @datetime: 2021/6/23
 * @desc:
 */
object Easy_559_MaxDepthNAryTree {
    fun maxDepth(root: Node?): Int {
        return getDepth(root)
    }

    // level traversal
    private fun getDepth(root: Node?): Int {
        if (root == null) return 0

        val queue = ArrayDeque<Node>()
        queue.addLast(root)
        var depth = 0

        while (!queue.isEmpty()) {
            val size = queue.size
            depth++

            for (i in 0 until size) {
                queue.removeFirst().children.forEach { child ->
                    if (child != null) {
                        queue.addLast(child)
                    }
                }
            }
        }

        return depth
    }


    // recursion
    private fun getDepth1(root: Node?): Int {
        if (root == null) return 0

        var max = 0
        root.children.forEach {
            max = Math.max(getDepth(it), max)
        }

        return max + 1
    }
}

data class Node(var value: Int, var children: List<Node?>)