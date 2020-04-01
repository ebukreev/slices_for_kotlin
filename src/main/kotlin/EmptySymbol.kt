package ru.sbpstu.kslices

/**
 * Объект, предоставляющий пустой символ для использования в срезах.
 * Примеры:
 * a[` `..` `], a[` `..` `..-1], a[` `..` `..-2]. a[` `..` `..3],
 * a[` `..4], a[3..` `], a[8..` `..-2]
 *
 * Обозначает все оставшиеся элементы до конца листа (или с конца листа в случае отрицательного щага)
 * или с начала листа (или до начала листа в случае отрицательного шага).
 */
object ` ` {
    operator fun rangeTo(other: Int): IndexProgression =
        IndexProgression.fromClosedProgression(null, other, 1)

    operator fun rangeTo(other: ` `): IndexProgression =
        IndexProgression.fromClosedProgression(null, null, 1)

    override fun toString(): String = "` `"
}