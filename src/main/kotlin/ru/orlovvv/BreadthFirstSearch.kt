package ru.orlovvv



fun Graph.bfs(start: String, finish: String): Int {
    val searchQueue = ArrayDeque<String>()
    searchQueue.add(start)
    val searched = mutableMapOf(start to 0)
    while (searchQueue.isNotEmpty()) {
        val next = searchQueue.removeAt(0)
        val distance = searched[next]!!
        if (next == finish) return distance
        for (neighbor in neighbors(next)) {
            if (neighbor in searched) continue
            searched[neighbor] = distance + 1
            searchQueue.add(neighbor)
        }
    }

    return -1
}

fun main() {
    useGraph()
}

fun useGraph() {
    val g = Graph()
    g.addVertex("A")
    g.addVertex("B")
    g.addVertex("C")
    g.addVertex("D")
    g.connect("A", "C")
    g.connect("B", "D")
    g.connect("B", "C")
    println(g.bfs("A", "B"))
}

class Graph {
    private data class Vertex(val name: String) {
        val neighbors = mutableSetOf<Vertex>()
    }

    private val vertices = mutableMapOf<String, Vertex>()

    private fun connect(first: Vertex?, second: Vertex?) {
        if (second != null) first?.neighbors?.add(second)
        if (first != null) second?.neighbors?.add(first)
    }

    fun addVertex(name: String) {
        vertices[name] = Vertex(name)
    }

    fun connect(first: String, second: String) =
        connect(vertices[first], vertices[second])

    fun neighbors(name: String): List<String> =
        vertices[name]?.neighbors?.map { it.name } ?: listOf()
}
