package com.boycoder.problems.tree

import com.boycoder.basis.datastructure.TreeNode

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Easy_111_MinDepthBinaryTree {
    fun minDepth(root: TreeNode?): Int {
        if (root == null) return 0
        return getDepth(root)
    }

    // level traversal
    // if we meet a leaf node, then return right now
    private fun getDepth(root: TreeNode): Int {
        val queue = ArrayDeque<TreeNode>()
        queue.add(root)
        var depth = 0

        while (!queue.isEmpty()) {
            val size = queue.size
            depth++
            for (i in 0 until size) {
                val node = queue.removeFirst()
                if (node.left == null && node.right == null) {
                    return depth
                }

                if (node.left != null) queue.addLast(node.left!!)
                if (node.right != null) queue.addLast(node.right!!)
            }
        }

        return depth
    }

    // dfs
    private fun getDepth1(root: TreeNode): Int {
        if (root.left == null && root.right == null){
            return 1
        } else if (root.left == null && root.right != null) {
            return getDepth(root.right!!) + 1
        } else if (root.left != null && root.right == null) {
            return getDepth(root.left!!) + 1
        } else {
            return Math.min(getDepth(root.left!!), getDepth(root.right!!)) + 1
        }
    }
}