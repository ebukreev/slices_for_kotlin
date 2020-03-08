package ru.sbpstu.jblab

operator fun <E> List<E>.get(vararg ranges: IntProgression): List<E> {
    var result = this
    if (ranges.size == 1)
        return this.slice(ranges[0])
    if (result[0] is List<*>) {
        result = result.slice(ranges[0]).toMutableList()
        for (i in result.indices) {
            result[i] = (result[i] as List<*>).slice(ranges[1]) as E
        }
        for (i in result.indices) {
            result[i] = (result[i] as List<*>).get(*ranges.sliceArray(2 until ranges.size)) as E
        }
    }
    return result
}

fun main() {
    val table: List<List<Int>> = listOf(
        listOf(1, 2, 3, 4),
        listOf(5, 6, 7, 8),
        listOf(9, 10, 11, 12),
        listOf(13, 14, 15, 16)
    )
    print(table[0..2, 0..2])
}



