package ru.sbpstu.jblab

class IndexProgression private constructor(val start: Int?, val end: Int?, val step: Int) {


    init {
        if (step == 0) throw kotlin.IllegalArgumentException("Step must be non-zero.")
        if (step == Int.MIN_VALUE) throw kotlin.IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.")
    }


    operator fun rangeTo(step: Int): IndexProgression = IndexProgression(start, end, step)

    operator fun rangeTo(step: ` `.Companion): IndexProgression = IndexProgression(start, end, 1)

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

operator fun IntProgression.rangeTo(other: ` `.Companion): IndexProgression {
    return IndexProgression.fromClosedProgression(first, last, step)
}

operator fun IntProgression.rangeTo(step: Int): IndexProgression {
    return IndexProgression.fromClosedProgression(first, last, step)
}

fun IntProgression.toIndexProgression(): IndexProgression {
    return IndexProgression.fromClosedProgression(this.first, this.last, this.step)
}