package ru.sbpstu.jblab

operator fun <E> List<E>.get(vararg ranges: IntProgression): List<E> {
    var result = this.toMutableList()
    if (ranges.size == 1)
        return this.slice(ranges[0])
    if (result[0] is List<*>) {
        result = result.toList().slice(ranges[0]).toMutableList()
        for (i in result.indices) {
            result[i] = (result[i] as List<*>).slice(ranges[1]) as E
        }
        for (i in result.indices) {
            result[i] = (result[i] as List<*>).get(*ranges.sliceArray(1 until ranges.size)) as E
        }
    }
    return result
}


fun main() {
    val table: List<List<List<Int>>> = listOf(
        listOf(listOf(1, 2, 3, 4), listOf(5, 6, 7, 8), listOf(9, 10, 11, 12), listOf(13, 14, 15, 16)),
        listOf(listOf(17, 18, 19, 20), listOf(21, 22, 23, 24), listOf(25, 26, 27, 28), listOf(29, 30, 31, 32)),
        listOf(listOf(33, 34, 35, 36), listOf(37, 38, 39, 40), listOf(41, 42, 43, 44), listOf(45, 46, 47, 48)),
        listOf(listOf(49, 50, 51, 52), listOf(53, 54, 55, 56), listOf(57, 58, 59, 60), listOf(61, 62, 63, 64))
    )
    print(table[0..1, 0..1, 0..1])
}



