package ru.sbpstu.kslices

/**
 * Этот класс представляет собой коллекцию, рсширяющую функциональность MutableList.
 */
class SlicebleList<T: Any?>(private val list: MutableList<T>) : MutableList<T> {

    override val size: Int = list.size

    override fun contains(element: T): Boolean = list.contains(element)

    override fun containsAll(elements: Collection<T>): Boolean = list.containsAll(elements)

    /**
     * Позволяет получить элемент также и по отрицательному индексу,
     * в этом случае отсчет идет с конца листа
     * (-1 - последний элемент, -2 - предпоследний и т.д.)
     *
     * @param index принимает индекс, по которому надо вернуть элемент
     * @return элемент по заданному индексу
     */
    override fun get(index: Int): T = if (index >= 0) list[index] else list[size + index]

    override fun indexOf(element: T): Int = list.indexOf(element)

    override fun isEmpty(): Boolean = list.isEmpty()

    override fun iterator(): MutableIterator<T> = list.iterator()

    override fun lastIndexOf(element: T): Int = list.lastIndexOf(element)

    override fun listIterator(): MutableListIterator<T> = list.listIterator()

    override fun listIterator(index: Int): MutableListIterator<T> = list.listIterator(index)

    override fun subList(fromIndex: Int, toIndex: Int): SlicebleList<T> = SlicebleList(list.subList(fromIndex, toIndex))

    fun toList(): List<T> = this.list.toList()

    fun toMutableList(): MutableList<T> = this.list

    override fun add(element: T): Boolean = this.list.add(element)

    override fun add(index: Int, element: T) {
       this.list.add(index, element)
    }

    override fun addAll(index: Int, elements: Collection<T>): Boolean =
        this.list.addAll(index, elements)


    override fun addAll(elements: Collection<T>): Boolean =
        this.list.addAll(elements)

    override fun clear() {
       this.list.clear()
    }

    override fun remove(element: T): Boolean =
        this.list.remove(element)

    override fun removeAll(elements: Collection<T>): Boolean =
        this.list.removeAll(elements)

    override fun removeAt(index: Int): T =
        this.list.removeAt(index)

    override fun retainAll(elements: Collection<T>): Boolean =
        this.list.retainAll(elements)

    /**
     * Позволяет изменить элемент также и по отрицательному индексу,
     * в этом случае отсчет идет с конца листа
     * (-1 - последний элемент, -2 - предпоследний и т.д.)
     *
     * @param index принимает индекс, по которому надо изменить элемент
     * @param element принимает элемент, на который надо установить в лист по заданному индексу
     * @return элемент стоявший под заданным индексом до его замены
     */
    override fun set(index: Int, element: T): T =
        if (index >= 0)
            this.list.set(index, element)
        else
            this.list.set(size + index, element)

    override fun toString(): String = list.toString()

    override fun hashCode(): Int = list.hashCode()

    override fun equals(other: Any?): Boolean = this.list == (other as? SlicebleList<*>)?.list ?: false
}
