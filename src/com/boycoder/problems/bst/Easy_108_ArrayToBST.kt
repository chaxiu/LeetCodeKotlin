package com.boycoder.problems.bst

import com.boycoder.basis.datastructure.TreeNode

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Easy_108_ArrayToBST {
    fun sortedArrayToBST(nums: IntArray): TreeNode? {
        return build(nums, 0, nums.size - 1)
    }

    private fun build(nums: IntArray, left: Int, right: Int): TreeNode? {
        if (left > right) return null

        val mid = left + ((right - left) / 2)

        val root = TreeNode(nums[mid])
        root.left = build(nums, left, mid - 1)
        root.right = build(nums, mid + 1, right)

        return root
    }
}