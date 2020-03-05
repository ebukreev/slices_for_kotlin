package ru.sbpstu.jblab

operator fun <E> List<E>.get(range: IntProgression): List<E> {
    return this.slice(range)
}

fun main() {
    val test = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8)
    print(test[0..8 step 2])
}


