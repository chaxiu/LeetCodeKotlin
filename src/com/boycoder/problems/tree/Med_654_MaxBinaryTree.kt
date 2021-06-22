package com.boycoder.problems.tree

import com.boycoder.basis.datastructure.TreeNode

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Med_654_MaxBinaryTree {
    fun constructMaximumBinaryTree(nums: IntArray): TreeNode? {
        val size = nums.size
        if (size == 0) return null
        var max = -1
        var index = 0
        nums.forEachIndexed { indx, i ->
            if (i > max) {
                max = i
                index = indx
            }
        }

        val node = TreeNode(max)
        if (size == 1) return node

        if (index == 0) {
            node.right = constructMaximumBinaryTree(nums.slice(1..(size - 1)).toIntArray())
        } else if (index == size - 1) {
            node.left = constructMaximumBinaryTree(nums.slice(0..(size - 2)).toIntArray())
        } else {
            node.left = constructMaximumBinaryTree(nums.slice(0..(index - 1)).toIntArray())
            node.right = constructMaximumBinaryTree(nums.slice((index + 1)..(size - 1)).toIntArray())
        }

        return node
    }
}