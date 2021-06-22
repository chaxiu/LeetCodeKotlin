package com.boycoder.problems.bst

import com.boycoder.basis.datastructure.TreeNode

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Med_669_TrimBST {
    fun trimBST(root: TreeNode?, low: Int, high: Int): TreeNode? {
        return trim(root, low, high)
    }

    private fun trim(root: TreeNode?, low: Int, high: Int): TreeNode? {
        var node = root
        if (node == null) return null

        if (node.value < low && node.value < high) {
            // drop the left side
            node = node.right
            node = trim(node, low, high)
        } else if (node.value > high && node.value > low) {
            // drop the right side
            node = node.left
            node = trim(node, low, high)
        } else {
            // node is fine
            // but we need to check node.left and node right
            node.left = trim(node.left, low, high)
            node.right = trim(node.right, low, high)
        }

        return node
    }
}