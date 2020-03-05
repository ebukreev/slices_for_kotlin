package ru.sbpstu.jblab

operator fun <E> List<List<E>>.get(range: IntProgression, range2: IntProgression): List<List<E>> {
    val result = this.slice(range).toMutableList()
    for (i in result.indices)
        result[i] = result[i].slice(range2)
    return result.toList()
}

operator fun <E> List<E>.get(range: IntProgression): List<E> = this.slice(range)


fun main() {
    val table = List(3) { List(3) {0} }.toMutableList()
    table[0] = listOf(1, 2, 3)
    table[1] = listOf(4, 5, 6)
    table[2] = listOf(7, 8, 9)
    print(table.toList()[0..1, 0..1])
}

