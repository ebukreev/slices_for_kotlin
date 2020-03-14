package ru.sbpstu.jblab



fun main() {
    val list: SlicebleList<SlicebleList<SlicebleList<Int>>> = slicebleListOf(slicebleListOf(slicebleListOf(1,2,3), slicebleListOf(4,5,6), slicebleListOf(7,8,9)),
        slicebleListOf(slicebleListOf(10,11,12), slicebleListOf(13,14,15), slicebleListOf(16,17,18)),
        slicebleListOf(slicebleListOf(19,20,21), slicebleListOf(22,23,24), slicebleListOf(25,26,27)))
    println(list[0..1, 0..1, 0..1])
}

/*
private operator fun <E> List<E>.get(range: CharRange): List<E> {
    if (range == (' '..' '))
        return this
    return emptyList()
}
*/