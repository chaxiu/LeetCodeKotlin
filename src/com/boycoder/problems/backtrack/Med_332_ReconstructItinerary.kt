package com.boycoder.problems.backtrack

import java.util.*
import kotlin.collections.HashMap

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 *
 * Ideal
 * We take airport as vertex and ticket as edge.
 * Map<start, <destination, count>>
 * it means for this airport we can go to destination count times.
 * We start from JFK,
 * and find a way that all the list.size = tickets.size + 1,
 * it means we used all the tickets.
 * This is a normal dfs
 */
object Med_332_ReconstructItinerary {
    // sorted by key
    private val graph: HashMap<String, SortedMap<String, Int>> = hashMapOf()
    private val path: MutableList<String> = mutableListOf()
    private var count = 0

    fun findItinerary(tickets: List<List<String>>): List<String> {
        // pre process
        count = tickets.size
        for (ticket in tickets) {
            val start = ticket[0]
            val destination = ticket[1]

            // careful: Kotlin sortedMapOf() means TreeMap
            val map = graph[start]?: sortedMapOf<String, Int>()
            map.put(destination, map.getOrDefault(destination, 0) + 1)

            graph[start] = map
        }

        // start from JFK
        path.add("JFK")
        backtrack("JFK")
        return path
    }

    private fun backtrack(start: String): Boolean {
        if (path.size == count + 1) {
            return true
        }

        // this is a tree map
        val destMap = graph[start]?:return false

        for (entry in destMap.entries) {
            if (entry.value <= 0) {
                continue
            }
            val value = entry.value
            val key = entry.key

            path.add(entry.key)
            destMap.put(key, value - 1)
            if (backtrack(entry.key)) {
                return true
            }
            path.removeAt(path.size - 1)
            destMap.put(key, value)
        }

        return false
    }
}