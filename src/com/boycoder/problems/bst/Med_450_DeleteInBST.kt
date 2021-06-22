package com.boycoder.problems.bst

import com.boycoder.basis.datastructure.TreeNode

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Med_450_DeleteInBST {
    fun deleteNode(root: TreeNode?, key: Int): TreeNode? {
        return delete(root, key)
    }

    private fun delete(root: TreeNode?, key: Int): TreeNode? {
        if (root == null) return null

        if (root.value == key) {
            if (root.left == null && root.right == null) {
                // return null means delete
                return null
            } else if (root.left == null && root.right != null) {
                return root.right
            } else if (root.left != null && root.right == null) {
                return root.left
            } else {
                // left != null right != null
                var node = root.right!!
                // find successor
                while (node.left != null) {
                    node = node.left!!
                }
                node.left = root.left
                root.left = null
                return root.right
            }
        } else if (root.value > key) {
            root.left = delete(root.left, key)
        } else if (root.value < key) {
            root.right = delete(root.right, key)
        }

        return root
    }
}