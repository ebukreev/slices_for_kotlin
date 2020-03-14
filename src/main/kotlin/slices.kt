package ru.sbpstu.jblab

operator fun <E> List<E>.get(vararg ranges: IntProgression): List<E> {
    var result = this
    if (ranges.size == 1)
        return this.slice(ranges[0])
    if (result[0] is List<*>) {
        result = result.slice(ranges[0]).toMutableList()
        @Suppress("UNCHECKED_CAST")
        for (i in result.indices) {
            result[i] = (result[i] as List<*>).slice(ranges[1]) as E
        }
        @Suppress("UNCHECKED_CAST")
        for (i in result.indices) {
            result[i] = (result[i] as List<*>).get(*ranges.sliceArray(2 until ranges.size)) as E
        }
    }
    return result
}

fun main() {
    val list: SlicebleList<List<Int>> = SlicebleList(listOf(SlicebleList(listOf(1, 2, 3, 4)),
        SlicebleList(listOf(5, 6, 7, 8)),
        SlicebleList(listOf(9, 10, 11, 12)),
        SlicebleList(listOf(13, 14, 15, 16))))
    println(list[-3, -1])
}

/*
private operator fun <E> List<E>.get(range: CharRange): List<E> {
    if (range == (' '..' '))
        return this
    return emptyList()
}
*/