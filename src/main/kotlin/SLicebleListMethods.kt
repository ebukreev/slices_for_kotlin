package ru.sbpstu.jblab

/**
 * Создает SlicebleList по переданным элементам.
 *
 * @param args множество элеметнов
 * @return созданный SlicebleList
 */
fun <T : Any?> slicebleListOf(vararg args: T): SlicebleList<T> =
    SlicebleList(args.toMutableList())

/**
 * Превращает экземпляр класса или наследника класса Iterable в SlicebleList.
 */
fun Iterable<*>.toSlicebleList() = SlicebleList(this.toMutableList())

/**
 * Возвращает N-мерный SlicebleList, полученный из переданного в соответствии с переданными IntProgressions.
 */
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

/**
 * Возвращает N-мерный SlicebleList, полученный из исходного в соответствии с переданными IndexProgressions(срезами).
 */
operator fun <T: Any?> SlicebleList<T>.get(vararg indices: IndexProgression): SlicebleList<Any?> {
    var arrayOfProgressions: Array<IntProgression> = emptyArray()
    for (element in indices) {
        arrayOfProgressions = arrayOfProgressions.plus(element.toIntProgressionFor(this))
    }
    return sliceThisList(this, *arrayOfProgressions)
}

/**
 * Возвращает N-мерный SlicebleList, полученный из исходного в соответствии с переданными IntRanges.
 */
operator fun <T: Any?> SlicebleList<T>.get(vararg ranges: IntRange): SlicebleList<Any?> {
    var needArray: Array<IndexProgression> = emptyArray()
    for (element in ranges) {
        needArray = needArray.plus(element.toIndexProgression())
    }
    return this.get(*needArray)
}

/**
 * Возвращает SlicebleList, полученный после взятия элементов из исходного под переданными в листе индексами.
 *
 * @param listIndices принимает лист индексов, элементы под которыми нужны
 * @return SlicebleList составленный из нужных элементов
 */
operator fun <T: Any?> SlicebleList<T>.get(listIndices: List<Int>): SlicebleList<Any?> =
    (listIndices.map { this[it] }).toSlicebleList()

/**
 * Возвращает SlicebleList, полученный после взятия элементов из исходного под переданными индексами.
 *
 * @param indices принимает индексы, элементы под которыми нужны
 * @return SlicebleList составленный из нужных элементов
 */
operator fun <T: Any?> SlicebleList<T>.get(vararg indices: Int): SlicebleList<Any?> =
    this[indices.toList()]

/**
 * Возвращает SlicebleList, полученный после взятия элементов из исходного N-мерного SlicebleList
 * под полученными из переданных списков наборов индексов.
 *
 * @param lists принимает списки индексов
 * @return SlicebleList нужных элементов
 */
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

/**
 * Устанавливает переданные элементы вместо элементов под переданными индексами исходного SlicebleList.
 * Колличество прежних элементов НЕ обязательно должно быть равно колличеству новых.
 *
 * @param indices указывает IntRange индексов элементов, вместо которых должны быть установлены новые
 * @param elements задает элементы для установки
 * @return успешность проведенной операции
 */
operator fun <T: Any?> SlicebleList<T>.set(indices: IntRange, elements: List<T>): Boolean {
    for(i in indices)
        this.removeAt(indices.first)
    return this.addAll(indices.first, elements)
}

/**
 * Устанавливает переданные элементы вместо элементов под переданными индексами исходного SlicebleList.
 * Колличество прежних элементов обязательно должно быть равно колличеству новых.
 *
 * @param indices указывает IndexProgression(срез) индексов элементов, вместо которых должны быть установлены новые
 * @param elements задает элементы для установки
 */
operator fun <T: Any?> SlicebleList<T>.set(indices: IndexProgression, elements: List<T>) {
    val intProgression = indices.toIntProgressionFor(this)
    if (intProgression.count() != elements.size)
        throw IllegalArgumentException("size of slice and size of list of elements for setting must be the same")
    for ((k, i) in intProgression.withIndex()) {
        this[i] = elements[k]
    }
}

/**
 * Устанавливает переданный элемент вместо элементов под переданными индексами исходного SlicebleList.
 *
 * @param indices указывает IndexProgression(срез) индексов элементов, вместо которых должен быть установлен новый
 * @param element задает элемент для установки
 */
operator fun <T: Any?> SlicebleList<T>.set(indices: IndexProgression, element: T) {
    for (i in indices.toIntProgressionFor(this))
        this[i] = element
}

/**
 * Устанавливает переданные элементы вместо элементов под переданными в листе индексами исходного SlicebleList.
 * Колличество прежних элементов обязательно должно быть равно колличеству новых.
 *
 * @param listIndices принимает лист индексов элементов, вместо которых должны быть установлены новые
 * @param elements задает элементы для установки
 */
operator fun <T: Any?> SlicebleList<T>.set(listIndices: List<Int>, elements: List<T>) {
    if (listIndices.size != elements.size)
        throw IllegalArgumentException("size of list of indices and size of list of elements for setting must be the same")
    for ((k, i) in listIndices.withIndex()) {
        this[i] = elements[k]
    }
}

/**
 * Устанавливает переданные элементы вместо элементов под переданными индексами исходного SlicebleList.
 * Колличество прежних элементов обязательно должно быть равно колличеству новых.
 *
 * @param indices принимает индексы элементов, вместо которых должны быть установлены новые
 * @param elements задает элементы для установки
 */
operator fun <T: Any?> SlicebleList<T>.set(vararg indices: Int, elements: List<T>) {
    this[indices.toList()] = elements
}

/**
 * Устанавливает переданный элемент вместо элементов под переданными в листе индексами исходного SlicebleList.
 *
 * @param indices принимает лист индексов элементов, вместо которых должен быть установлен новый
 * @param element задает элемент для установки
 */
operator fun <T: Any?> SlicebleList<T>.set(indices: List<Int>, element: T) {
    for (i in indices)
        this[i] = element
}

/**
 * Устанавливает переданный элемент вместо элементов под переданными индексами исходного SlicebleList.
 *
 * @param indices принимает индексы элементов, вместо которых должен быть установлен новый
 * @param element задает элемент для установки
 */
operator fun <T: Any?> SlicebleList<T>.set(vararg indices: Int, element: T) {
    this[indices.toList()] = element
}