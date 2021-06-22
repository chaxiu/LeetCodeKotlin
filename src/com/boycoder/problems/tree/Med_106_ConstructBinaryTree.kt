package com.boycoder.problems.tree

import com.boycoder.basis.datastructure.TreeNode

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Med_106_ConstructBinaryTree {
    fun buildTree(inorder: IntArray, postorder: IntArray): TreeNode? {
        if (inorder.size == 0 || postorder.size == 0) return null

        val pRoot = postorder.size - 1
        val iRoot = inorder.indexOf(postorder[pRoot])

        var node = TreeNode(postorder[pRoot])

        if (postorder.size == 1) {
            return node
        }

        if (iRoot == 0) {
            node.right = buildTree(inorder.slice(1..(inorder.size - 1)).toIntArray(), postorder.slice(0..(postorder.size - 2)).toIntArray())
        } else if (iRoot == inorder.size - 1) {
            node.left = buildTree(inorder.slice(0..(inorder.size - 2)).toIntArray(), postorder.slice(0..(postorder.size - 2)).toIntArray())
        } else {
            // in the middle
            val leftSize = iRoot
            val rightSize = inorder.size - 1 - iRoot
            node.left = buildTree(inorder.slice(0..(leftSize - 1)).toIntArray(), postorder.slice(0..(leftSize - 1)).toIntArray())
            node.right = buildTree(inorder.slice((iRoot + 1)..(inorder.size - 1)).toIntArray(), postorder.slice(leftSize..(postorder.size - 2)).toIntArray())
        }

        return node
    }
}