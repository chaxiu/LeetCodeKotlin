package com.boycoder.problems.tree

import com.boycoder.basis.datastructure.TreeNode

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Easy_257_BinaryTreePaths {
    fun binaryTreePaths(root: TreeNode?): List<String> {

        val list = mutableListOf<String>()
        if (root == null) return list

        val nodes = mutableListOf<TreeNode>()
        nodes.add(root)
        findPath(root, nodes, list)
        return list
    }

    private fun findPath(root: TreeNode, nodes: MutableList<TreeNode>, list: MutableList<String>) {
        if (root.left == null && root.right == null) {
            val s = nodes.joinToString(separator = "->", transform = {
                it.value.toString()
            })
            list.add(s)
            return
        }

        if (root.left != null) {
            nodes.add(root.left!!)
            findPath(root.left!!, nodes, list)
            nodes.remove(root.left) // back tracking
        }

        if (root.right != null) {
            nodes.add(root.right!!)
            findPath(root.right!!, nodes, list)
            nodes.remove(root.right) // back tracking
        }
    }
}