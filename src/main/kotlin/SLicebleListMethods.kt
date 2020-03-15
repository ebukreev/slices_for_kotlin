package ru.sbpstu.jblab

fun<T : Any?> slicebleListOf(vararg args: T): SlicebleList<T> = SlicebleList(args.toList())

fun<T : Any?> SlicebleList<T>.toList() = this.list

fun List<*>.toSlicebleList() = SlicebleList(this)

operator fun <T: Any?> SlicebleList<T>.get(vararg indices: Int): T  {
    var result = this
    var k = 0
    while (result[0] is SlicebleList<*>) {
        @Suppress("UNCHECKED_CAST")
        result = result[indices[k]] as SlicebleList<T>
        k++
    }
    return result[indices[k]]
}

operator fun <T: Any?> SlicebleList<T>.get(vararg ranges: IntProgression): SlicebleList<Any?> {
    var result = this.list
    if (ranges.size == 1)
        return this.list.slice(ranges[0]).toSlicebleList()
    if (result[0] is SlicebleList<*>) {
        result = result.slice(ranges[0]).toMutableList()
        @Suppress("UNCHECKED_CAST")
        for (i in result.indices) {
            result[i] = (result[i] as SlicebleList<*>).get(*ranges.sliceArray(1 until ranges.size)) as T
        }
    }
    return result.toSlicebleList()
}

operator fun <T: Any?> SlicebleList<T>.get(listIndices: List<Int>): SlicebleList<Any?> =
    list.slice(listIndices).toSlicebleList()

operator fun <T: Any?> SlicebleList<T>. get(vararg lists: List<Int>): SlicebleList<Any?> {
    val result = emptyList<Any?>().toMutableList()
    for (i in lists[0].indices) {
        var currentList: SlicebleList<T> = this
        @Suppress("UNCHECKED_CAST")
        for (list in lists.slice(0 until lists.lastIndex)) {
            currentList = currentList[list[i]] as SlicebleList<T>
        }
        result += currentList[lists[lists.lastIndex][i]]
    }
    return result.toSlicebleList()
}

operator fun <T: Any?> SlicebleList<T>.get(indices: IndexProgression): SlicebleList<Any?> {
    val notNull: Boolean = indices.start != null && indices.end != null && indices.step != null
    if (notNull && indices.start!! < indices.end!! && indices.step!! > 0)
        return this[indices.start..indices.end step indices.step]
    if (notNull && indices.start!! > indices.end!! && indices.step!! > 0)
        return this[indices.start downTo indices.end step indices.step]
    return this[0..0]
}
