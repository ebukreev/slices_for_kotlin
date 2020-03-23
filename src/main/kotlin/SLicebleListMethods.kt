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
        arrayOfProgressions = arrayOfProgressions.plus(element.toIntProgressionFor(this))
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

private fun countSize(intProgression: IntProgression): Int {
    var k = 0
    for (i in intProgression)
        k++
    return k
}

operator fun <T: Any?> SlicebleList<T>.set(indices: IntRange, elements: List<T>): Boolean {
    for(i in indices)
        this.removeAt(indices.first)
    return this.addAll(indices.first, elements)
}

operator fun <T: Any?> SlicebleList<T>.set(indices: IndexProgression, elements: List<T>) {
    val intProgression = indices.toIntProgressionFor(this)
    if (countSize(intProgression) != elements.size)
        throw IllegalArgumentException("size of slice and size of list of elements for setting must be the same")
    for ((k, i) in intProgression.withIndex()) {
        this[i] = elements[k]
    }
}

operator fun <T: Any?> SlicebleList<T>.set(listIndices: List<Int>, elements: List<T>) {
    if (listIndices.size != elements.size)
        throw IllegalArgumentException("size of list of indices and size of list of elements for setting must be the same")
    for ((k, i) in listIndices.withIndex()) {
        this[i] = elements[k]
    }
}

operator fun <T: Any?> SlicebleList<T>.set(vararg indices: Int, elements: List<T>) {
    this[indices.toList()] = elements
}

operator fun <T: Any?> SlicebleList<T>.set(indices: List<Int>, element: T) {
    for (i in indices)
        this[i] = element
}

operator fun <T: Any?> SlicebleList<T>.set(vararg indices: Int, element: T) {
    this[indices.toList()] = element
}