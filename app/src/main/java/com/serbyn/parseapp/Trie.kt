package com.serbyn.parseapp

class Trie {

    private val root = Node(
        value = '.',
        children = mutableMapOf(),
    )

    class Node(
        val value: Char,
        val children: MutableMap<Char, Node>,
        var isWord: Boolean = false
    )

    fun insert(word: String) {
        var current: Node = root

        for (l in word.toCharArray()) {
            current = current.children.computeIfAbsent(l) { c ->
                Node(value = c, children = mutableMapOf())
            }
        }

        current.isWord = true
    }

    fun find(word: String): Node? {
        var current: Node = root
        for (element in word) {
            val node: Node = current.children[element] ?: return null
            current = node
        }
        return if (current.isWord) {
            current
        } else {
            null
        }
    }


    fun splitString(fullString: String): String {
        val result = StringBuilder()
        val length = fullString.length

        var firstIndex = 0
        var lastIndex = 1
        while (firstIndex < length && lastIndex <= length) {
            val substring = fullString.substring(firstIndex, lastIndex)
            val node = find(substring)
            if (node != null) {
                result.append(substring)
                result.append(" ")
            }

            if (lastIndex == length) {
                firstIndex++
                lastIndex = firstIndex + 1
            } else {
                lastIndex++
            }
        }
        return result.toString()
    }
}