package ru.orlovvv

/* ПОИСК В ШИРИНУ

    Помогает ответить на вопросы двух типов:
    1) Существует ли путь от узла A к узлу B
    2) Как выглядит кратчайший путь от узла A к узлу B

    Для примера представьте, что вы ищите конкретного человека в списке
    друзей в социальной сети. Сначала нужно построить список друзей для поиска.
    Затем обратиться к каждому и проверить, тот ли это человек. Затем поиск продолжается
    среди друзей ваших друзей. Каждый раз, когда вы проверяете кого-то из списка, вы добавляете
    в список всех его друзей.

    Связи первого уровня предпочтительнее связей второго уровня -> поиск по контактам второго уровня
    не должен производиться, пока вы не будете уверены в том, что среди связей первого уровня нет подходящего
    контакта. Для проверки в порядке добавления необходимо использовать очередь.

 */
fun Graph.bfs(start: String, finish: String): Int {
    val searchQueue = ArrayDeque<String>()      // создание новой очереди
    searchQueue.add(start)                      // в очередь добавляется стартовый элемент
    val searched = mutableMapOf(start to 0)     // список уже проверенных

    while (searchQueue.isNotEmpty()) {          // пока очередь не пуста
        val next = searchQueue.removeAt(0)// из очереди извлекается первый человек
        val distance = searched[next]!!         // расстояние до человека
        if (next == finish) return distance     // если это совпадене, то результат

        for (neighbor in neighbors(next)) {     // идем по всем друзьям человека
            if (neighbor in searched) continue  // если он уже был проверен, то пропускаем
            searched[neighbor] = distance + 1   // присваиваем ему значение в виде расстояния
            searchQueue.add(neighbor)           // добавляем в очередь соседа
        }
    }

    return -1
}

fun useGraph() {
    val g = Graph()
    g.addNode("you")
    g.addNode("alice")
    g.addNode("bob")
    g.addNode("claire")
    g.addNode("anuj")
    g.addNode("jonny")
    g.addNode("thom")
    g.addNode("peggy")
    g.connect("you", "alice")
    g.connect("you", "bob")
    g.connect("you", "claire")
    g.connect("bob", "anuj")
    g.connect("bob", "peggy")
    g.connect("claire", "thom")
    g.connect("claire", "jonny")
    println(g.bfs("you", "anuj"))
}

fun main() {
    useGraph()
}

class Graph() {
    private data class Node(val name: String) {
        val neighbors = mutableSetOf<Node>()
    }

    private val nodes = mutableMapOf<String, Node>()

    private fun connect(first: Node?, second: Node?) {
        if (second != null) first?.neighbors?.add(second)
        if (first != null) second?.neighbors?.add(first)
    }

    fun addNode(name: String) {
        nodes[name] = Node(name)
    }

    fun connect(first: String, second: String) {
        connect(nodes[first], nodes[second])
    }

    fun neighbors(name: String): List<String> =
        nodes[name]?.neighbors?.map { it.name } ?: listOf()

}


