package com.boycoder.problems.backtrack

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Med_40_CombinationSumII {
    private val list: MutableList<List<Int>> = mutableListOf()
    private val path: MutableList<Int> = mutableListOf()
    private var sum = 0

    fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>> {
        /**
        Though we can use a set in a level to avoid the same element been pick more than one time.
        But we still need to sort the array to avoid duplication
        EG:             1,2,7,1
                        /     \
                     2,7,1     1
                        \      (7, 1)
                        (1, 7)

        EG:             1,1,2,7
                        /      \
                     2,7,1     (NO)
                        \
                        (1, 7)
         */
        candidates.sort()
        combine(candidates, target, 0)
        return list
    }

    /**
    So, to avoid duplication, we can use sort and nums[i] == nums[i - 1] to check
     */
    private fun combine(nums: IntArray, target: Int, start: Int) {
        if (sum > target) return

        if (sum == target) {
            list.add(path.toMutableList())
            return
        }

        for (i in start until nums.size) {
            if (i > start && nums[i] == nums[i - 1]) continue

            path.add(nums[i])
            sum = sum + nums[i]
            // We dont want to pick the same element more than once
            // So in the next level of recursion, the start index is i + 1
            // then it never repeat the same index for the single combination
            combine(nums, target, i + 1)
            sum = sum - nums[i]
            path.removeAt(path.size - 1)
        }
    }

    private fun combine1(nums: IntArray, target: Int, start: Int) {
        if (sum > target) return

        if (sum == target) {
            list.add(path.toMutableList())
            return
        }

        val set: HashSet<Int> = hashSetOf()
        for (i in start until nums.size) {
            if (set.contains(nums[i])) continue
            set.add(nums[i])

            path.add(nums[i])
            sum = sum + nums[i]
            // We dont want to pick the same element more than once
            // So in the next level of recursion, the start index is i + 1
            // then it never repeat the same index for the single combination
            combine(nums, target, i + 1)
            sum = sum - nums[i]
            path.removeAt(path.size - 1)
        }
    }
}