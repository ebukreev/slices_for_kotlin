package ru.sbpstu.kslices

/**
 * Класс, реализующий срезы.
 * Примеры синтаксиса, использующего срезы:
 *
 * a[2..7..1], a[1..9..2], a[9..0..-1], a[11..3..-2],
 * a[-10..-3..1], a[-3..-10..-1], a[3..-1..1],
 * a[` `..` `], a[` `..` `..-1], a[` `..` `..-2]. a[` `..` `..3],
 * a[` `..4], a[3..` `], a[8..` `..-2]
 *
 * Индексы с унарным минусом обозначают взятие элемента с конца листа (-1 - последний элемент
 * -2 - предпоследний элемент, и т.д).
 *
 * Символ ` ` обозначает все оставшиеся элементы до конца листа (или с конца листа в случае отрицательного щага)
 * или с начала листа (или до начала листа в случае отрицательного шага).
 */
class IndexProgression private constructor(val start: Int?, val end: Int?, val step: Int) {

    init {
        if (step == 0) throw kotlin.IllegalArgumentException("Step must be non-zero.")
        if (step == Int.MIN_VALUE) throw kotlin.IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.")
    }

    operator fun rangeTo(step: Int): IndexProgression = IndexProgression(start, end, step)

    operator fun rangeTo(step: ` `.Companion): IndexProgression = IndexProgression(start, end, 1)

    /**
     * Создает IntProgression для конкретного SlicebleList по переданному срезу.
     */
    fun toIntProgressionFor(slicebleList: SlicebleList<*>): IntProgression {
        val step = this.step
        var start = this.start ?: (if (step > 0) 0 else slicebleList.size - 1)
        var end = this.end ?: (if (step > 0) slicebleList.size - 1 else 0)
        val size = slicebleList.size

        start = if (start >= 0) start else size + start
        end = if (end >= 0) end else size + end

        return IntProgression.fromClosedRange(start, end, step)
    }

    companion object {
        fun fromClosedProgression(start: Int?, end: Int?, step: Int): IndexProgression =
            IndexProgression(start, end, step)
    }

    override fun toString(): String {
        return buildString {
            append(start ?: "` `")
            append("..")
            append(end ?: "` `")
            append("..")
            append(step)
        }
    }
}

operator fun Int.rangeTo(other: ` `.Companion): IndexProgression {
    return IndexProgression.fromClosedProgression(this, null, 1)
}

operator fun IntProgression.rangeTo(step: Int): IndexProgression {
    return IndexProgression.fromClosedProgression(first, last, step)
}

fun IntProgression.toIndexProgression(): IndexProgression {
    return IndexProgression.fromClosedProgression(this.first, this.last, this.step)
}

