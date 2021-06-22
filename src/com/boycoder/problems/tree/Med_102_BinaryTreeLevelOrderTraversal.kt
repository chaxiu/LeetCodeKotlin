package com.boycoder.problems.tree

import com.boycoder.basis.datastructure.TreeNode

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Med_102_BinaryTreeLevelOrderTraversal {
    fun levelOrder(root: TreeNode?): List<List<Int>> {
        val list: MutableList<List<Int>> = mutableListOf()
        if (root == null) return list

        val queue = ArrayDeque<TreeNode>()

        var node = root
        queue.addLast(root)

        while (!queue.isEmpty()) {
            val size = queue.size
            val temps = mutableListOf<Int>()


            for (i in 0 until size) {
                node = queue.removeFirst()
                temps.add(node.value)

                if (node.left != null) queue.addLast(node.left!!)
                if (node.right != null) queue.addLast(node.right!!)
            }

            list.add(temps)
        }
        return list
    }
}