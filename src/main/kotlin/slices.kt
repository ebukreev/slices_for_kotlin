package ru.sbpstu.jblab

class SlicebleList<E>(override val size: Int, entryList: List<E>) : AbstractList<E>() {
   private val list = entryList

   override fun get(index: Int): E {
      return list[index]
   }

   operator fun get(range: IntProgression): Any? {
      val workedList = mutableListOf<E>()
      for (i in range) {
         workedList.add(this.list[i])
      }
      return workedList
   }
}

fun main() {
   val test = SlicebleList(8, listOf(1,2,3,4,5,6,7,8))
   print(test[1..4])
}