package ru.sbpstu.jblab

class SlicebleList<T: Any?>(val list: List<T>) : List<T> {

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

    override fun toString(): String = list.toString()

    override fun hashCode(): Int = list.hashCode()

    override fun equals(other: Any?): Boolean = this.list == (other as? SlicebleList<*>)?.list ?: false
}
