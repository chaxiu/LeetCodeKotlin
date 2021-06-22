package com.boycoder.problems.tree

import com.boycoder.basis.datastructure.TreeNode

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Med_513_BottomLeftTreeValue {
    var maxLevel = 0
    var value = 0
    fun findBottomLeftValue(root: TreeNode?): Int {
        findLeft(root)
        return value
    }

    private fun findLeft(root: TreeNode?) {
        if (root == null) return

        val queue = ArrayDeque<TreeNode>()
        queue.addLast(root)
        var node = root

        while (!queue.isEmpty()) {
            val size = queue.size

            for (i in 0 until size) {
                node = queue.removeFirst()
                if (i == 0) {
                    maxLevel++
                    value = node.value
                }

                node.left?.let {
                    queue.addLast(it)
                }

                node.right?.let {
                    queue.addLast(it)
                }
            }
        }
    }

    // recursion with back tracking
    private fun findLeft1(root: TreeNode?, level: Int) {
        if (root == null) return

        var mLevel = level
        if (mLevel > maxLevel) {
            maxLevel = mLevel
            value = root.value
        }

        // we check left node first, so we can find bottom left
        // if change the order, we can find bottom right
        if (root.left != null) {
            mLevel++
            findLeft1(root.left, mLevel)
            mLevel--
        }

        if (root.right != null) {
            mLevel++
            findLeft1(root.right, mLevel)
            mLevel--
        }
    }
}