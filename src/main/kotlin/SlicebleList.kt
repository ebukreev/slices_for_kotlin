package ru.sbpstu.jblab

class SlicebleList<T: Any?>(private val list: List<T>) : List<T> {

    override val size: Int = list.size

    override fun contains(element: T): Boolean = list.contains(element)

    override fun containsAll(elements: Collection<T>): Boolean = list.containsAll(elements)

    override fun get(index: Int): T = if (index >= 0) list[index] else list[size + index]

    override fun indexOf(element: T): Int = list.indexOf(element)

    override fun isEmpty(): Boolean = list.isEmpty()

    override fun iterator(): Iterator<T> = list.iterator()

    override fun lastIndexOf(element: T): Int = list.lastIndexOf(element)

    override fun listIterator(): ListIterator<T> = list.listIterator()

    override fun listIterator(index: Int): ListIterator<T> = list.listIterator(index)

    override fun subList(fromIndex: Int, toIndex: Int): SlicebleList<T> = SlicebleList(list.subList(fromIndex, toIndex))

    private fun toList() = this.list

    private fun List<*>.toSlicebleList() = SlicebleList(this)

    operator fun get(vararg indices: Int): T  {
        var result = this
        var k = 0
        while (result[0] is SlicebleList<*>) {
            @Suppress("UNCHECKED_CAST")
            result = result[indices[k]] as SlicebleList<T>
            k++
        }
        return result[indices[k]]
    }

    operator fun get(vararg ranges: IntProgression): SlicebleList<Any?> {
        var result = this.list
        if (ranges.size == 1)
            return this.list.slice(ranges[0]).toSlicebleList()
        if (result[0] is SlicebleList<*>) {
            result = result.slice(ranges[0]).toMutableList()
            @Suppress("UNCHECKED_CAST")
            for (i in result.indices) {
                result[i] = (result[i] as List<*>).slice(ranges[1]).toSlicebleList() as T
            }
            @Suppress("UNCHECKED_CAST")
            for (i in result.indices) {
                result[i] = (result[i] as SlicebleList<*>).get(*ranges.sliceArray(1 until ranges.size)) as T
            }
        }
        return result.toSlicebleList()
    }

    override fun toString(): String {
        return list.toString()
    }
}

fun<T : Any> slicebleListOf(vararg args: T): SlicebleList<T> = SlicebleList(args.toList())