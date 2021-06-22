package com.boycoder.problems.bst

import com.boycoder.basis.datastructure.TreeNode

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Easy_235_LowestCommonAncestorBST {
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if (root == null || p == null || q == null) return null
        return find(root, p, q)
    }

    private fun find(root: TreeNode?, p: TreeNode, q: TreeNode): TreeNode? {
        if (root == null) return null

        var node: TreeNode? = root
        while (node != null) {
            if (node.value > p.value && node.value > q.value) {
                node = node.left
            } else if (node.value < p.value && node.value < q.value) {
                node = node.right
            } else {
                return node
            }
        }

        return null
    }

    // recursion
    private fun find1(root: TreeNode?, p: TreeNode, q: TreeNode): TreeNode? {
        if (root == null) return null

        if (root.value > p.value && root.value > q.value) {
            return find(root.left, p, q)
        } else if (root.value < p.value && root.value < q.value) {
            return find(root.right, p, q)
        } else {
            return root
        }
    }
}