package ru.sbpstu.kslices

/**
 * Этот класс представляет собой коллекцию, рсширяющую функциональность MutableList.
 */
class SlicebleList<T> (private val list: MutableList<T>) : MutableList<T> by list {

    /**
     * Позволяет получить элемент также и по отрицательному индексу,
     * в этом случае отсчет идет с конца листа
     * (-1 - последний элемент, -2 - предпоследний и т.д.)
     *
     * @param index принимает индекс, по которому надо вернуть элемент
     * @return элемент по заданному индексу
     */
    override fun get(index: Int): T = if (index >= 0) list[index] else list[size + index]

    override fun subList(fromIndex: Int, toIndex: Int): SlicebleList<T> = SlicebleList(list.subList(fromIndex, toIndex))

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

    override fun equals(other: Any?): Boolean = this.list == (other as SlicebleList<*>).list
}
