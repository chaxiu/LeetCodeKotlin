package com.boycoder.problems.tree

import com.boycoder.basis.datastructure.TreeNode

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 *
 * 1. Recursion + post order
 * 2. Maintain every node's parent in map
 *       and find visit every p node parent put into the set.
 *       Then visit every q node parent check if into the set.
 */
object Med_236_LowestCommonAncestor {

    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if (root == null || p == null || q == null) return null
        return find(root, p, q)
    }

    // recursion + post order
    private fun find(root: TreeNode?, p: TreeNode, q: TreeNode): TreeNode? {
        if (root == null) return null

        val left = find(root.left, p, q)
        val right = find(root.right, p, q)

        if (root.value == p.value || root.value == q.value) return root

        if (left != null && right != null) {
            return root
        } else if (left == null && right != null) {
            return right
        } else if (left != null && right == null) {
            return left
        } else {
            return null
        }
    }
}