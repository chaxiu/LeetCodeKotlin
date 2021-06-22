package com.boycoder.problems.bst

import com.boycoder.basis.datastructure.TreeNode

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Med_701_InsertIntoBST {
    fun insertIntoBST(root: TreeNode?, target: Int): TreeNode? {
        return insert(root, target)
    }

    private fun insert(root: TreeNode?, target: Int): TreeNode? {
        if (root == null) return TreeNode(target)

        var node: TreeNode? = root
        var pre: TreeNode? = null

        while (node != null) {
            pre = node
            if (node.value > target) {
                node = node.left
            } else if (node.value < target) {
                node = node.right
            } else {
            }
        }

        if (node == null && pre != null) {
            node = TreeNode(target)
            if (node!!.value > pre.value) {
                pre.right = node
            } else {
                pre.left = node
            }
        }

        return root
    }


    // recursion
    private fun insert1(root: TreeNode?, target: Int): TreeNode? {
        if (root == null) {
            val node = TreeNode(target)
            return node
        }

        if (root.value > target) {
            root.left = insert(root.left, target)
        }

        if (root.value < target) {
            root.right = insert(root.right, target)
        }

        return root
    }
}