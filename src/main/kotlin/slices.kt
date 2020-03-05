package ru.sbpstu.jblab

operator fun <E> List<E>.get(range: IntProgression): List<E> = this.slice(range)

operator fun <E> List<List<E>>.get(vararg ranges: IntProgression): List<List<E>> {
    val result = this.slice(ranges[0]).toMutableList()
    for (i in 1 until ranges.size) {
        for (j in result.indices) {
            result[j] = result[j].slice(ranges[i])
        }
    }
    return result.toList()
}

fun main() {
    val table: List<List<Int>> = listOf(
        listOf(1, 2, 3, 4),
        listOf(5, 6, 7, 8),
        listOf(9, 10, 11, 12),
        listOf(13, 14, 15, 16)
    )
    print(table[0..2 , 0..2])
}



