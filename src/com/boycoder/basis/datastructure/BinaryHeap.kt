package com.boycoder.basis.datastructure

import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/4
 * @desc: Binary Heap use array under the hood.
 */
class BinaryHeap(val comparator: Comparator<Int>): IBinaryHeap {

    private val elements = mutableListOf<Int>()

    override fun size(): Int {
        return elements.size
    }

    override fun isEmpty(): Boolean {
        return elements.isEmpty()
    }

    override fun clear() {
        return elements.clear()
    }

    override fun add(element: Int) {
        elements.add(element)
        shiftUp(size() - 1)
    }

    /**
     * Shift up the element until the parent node is bigger than element.
     * During the shifting, we need to keep smaller parent shift down.
     */
    private fun shiftUp(index: Int) {
        checkIndex(index)

        val element = elements[index]
        var cur = index

        while (cur > 0) {
            val parentIndex = (cur - 1) / 2
            val parent = elements[parentIndex]
            if (comparator.compare(parent, element) > 0) {
                break
            }
            elements[cur] = parent
            cur = parentIndex
        }

        elements[cur] = element
    }

    override fun get(): Int {
        checkIndex(0)
        return elements[0]
    }

    override fun remove(): Int {
        checkIndex(0)
        val first = elements[0]
        val endIndex = size() - 1
        val end = elements[endIndex]

        elements[0] = end
        elements.removeAt(endIndex)

        if (size() > 0) {
            shiftDown(0)
        }

        return first
    }

    /**
     * When we replace the top element, we need to shift it down,
     * if the node is smaller than it's child node, we need to swap the bigger child
     * until we find the node >= max(leftChild, rightChild)
     */
    private fun shiftDown(index: Int) {
        var cur = index
        val element = elements[index]

        // [0, half] means they are not leaf node
        val half = size() / 2

        while (cur < half) {
            val leftIndex = cur*2 + 1

            var childIndex = leftIndex
            var child = elements[leftIndex]

            val rightIndex = leftIndex + 1
            if (rightIndex < size() &&
                    comparator.compare(elements[rightIndex], elements[leftIndex]) > 0) {
                childIndex = rightIndex
                child = elements[rightIndex]
            }

            // child = max(leftChild, rightChild)
            if (comparator.compare(element, child) >= 0) {
                // shift down is finished
                break
            }

            // shift up the child
            elements[cur] = child
            cur = childIndex
        }

        // shift down the element
        elements[cur] = element
    }

    override fun replace(element: Int): Int? {
        if (size() == 0) {
            elements.add(element)
            return null
        } else {
            val top = get()
            elements[0] = element
            shiftDown(0)
            return top
        }
    }

    private fun checkIndex(index: Int) {
        if (index <0 || index >= size()) {
            throw IndexOutOfBoundsException()
        }
    }
}

fun main() {
    val heap = BinaryHeap { o1, o2 -> o1 - o2 }
    for (i in 1..50) {
        heap.add(i)
    }
    val top = heap.get()
    asserts(top, 50)

    heap.remove()
    heap.remove()
    val top1 = heap.get()
    asserts(top1, 48)

    heap.replace(5)
    val top2 = heap.get()
    asserts(top2, 47)
}


interface IBinaryHeap {
    fun size(): Int
    fun isEmpty(): Boolean
    fun clear()
    fun add(element: Int)
    fun get(): Int
    fun remove(): Int
    fun replace(element: Int): Int?
}