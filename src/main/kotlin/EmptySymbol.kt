package ru.sbpstu.jblab

class ` ` {
    companion object {
        operator fun rangeTo(other: Int): IndexProgression = IndexProgression.fromClosedProgression(null, other, 1)

        operator fun rangeTo(other: Companion): IndexProgression =
            IndexProgression.fromClosedProgression(null, null, 1)

    }
    override fun toString(): String = "` `"
}