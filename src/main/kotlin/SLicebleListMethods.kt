package ru.sbpstu.jblab

fun <T : Any?> slicebleListOf(vararg args: T): SlicebleList<T> =
    SlicebleList(args.toMutableList())

fun Iterable<*>.toSlicebleList() = SlicebleList(this.toMutableList())

private fun <T: Any?> sliceThisList(sList: SlicebleList<T>, vararg progressions: IntProgression): SlicebleList<Any?> {
    var result = sList.toMutableList()
    if (progressions.size == 1)
        return sList.toMutableList().slice(progressions[0]).toSlicebleList()
    if (result[0] is SlicebleList<*>) {
        result = result.slice(progressions[0]).toMutableList()
        @Suppress("UNCHECKED_CAST")
        for (i in result.indices) {
            result[i] =
                sliceThisList(result[i] as SlicebleList<*>,
                    *progressions.sliceArray(1 until progressions.size)) as T
        }
    }
    return result.toSlicebleList()
}

operator fun <T: Any?> SlicebleList<T>.get(vararg indices: IndexProgression): SlicebleList<Any?> {
    var arrayOfProgressions: Array<IntProgression> = emptyArray()
    for (element in indices) {
        val step = element.step
        var start = element.start ?: (if (step > 0) 0 else this.size - 1)
        var end = element.end ?: (if (step > 0) this.size - 1 else 0)
        val size = this.size

        start = if (start >= 0) start else size + start
        end = if (end >= 0) end else size + end

        arrayOfProgressions = arrayOfProgressions.plus(IntProgression.fromClosedRange(start, end, step))
    }
    return sliceThisList(this, *arrayOfProgressions)
}

operator fun <T: Any?> SlicebleList<T>.get(range: IntRange): SlicebleList<Any?> =
    this[range.toIndexProgression()]

operator fun <T: Any?> SlicebleList<T>.get(vararg ranges: IntRange): SlicebleList<Any?> {
    var needArray: Array<IndexProgression> = emptyArray()
    for (element in ranges) {
        needArray = needArray.plus(element.toIndexProgression())
    }
    return this.get(*needArray)
}

operator fun <T: Any?> SlicebleList<T>.get(listIndices: List<Int>): SlicebleList<Any?> =
    (listIndices.map { this[it] }).toSlicebleList()

operator fun <T: Any?> SlicebleList<T>.get(vararg indices: Int): SlicebleList<Any?> =
    this[indices.toList()]

operator fun <T: Any?> SlicebleList<T>. get(vararg lists: List<Int>): SlicebleList<Any?> {
    val result = emptyList<T>().toMutableList()
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
