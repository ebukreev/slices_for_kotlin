# Slices for Kotlin

Эта библиотека привносит в язык Kotlin синтаксис для работы с коллекциями, 
схожий с тем, что есть в таких языках, как: Python, Matlab, Julia, R...

### На данный момент реализовано:

- Получение элементов списка, соответствующих переданному списку индексов:
  ```Kotlin
  val a = slicebleListOf(1, 2, 3, 4, 5, 6, 7, 8)
  val b = slicebleListOf(slicebleListOf(1, 2, 3), slicebleListOf(4, 5, 6), slicebleListOf(7, 8, 9))
  
  println(a[listOf(1, 4, 5)]) 
  println(b[listOf(0, 1, 0), listOf(2, 1, 0)])
  ```
  В консоли мы получим:
  ```Kotlin
  [2, 5, 6]
  [3, 5, 1]
  ```
- Получение элементов списка с конца по отрицательному индексу:
  ```Kotlin
  val a = slicebleListOf(1, 2, 3, 4)
  println(a[-2])
  ```
  В консоли мы получим число 3
  
- Получение элементов списка по переданному срезу (работает для любого уровня вложенности):
  ```Kotlin
  val a = slicebleListOf(0, 1, 2, 3, 4, 5, 6, 7, 8)
  println(a[1..4]) //[1, 2, 3, 4]
  println(a[2..6..2]) //[2, 4, 6]
  //синтаксис слайсов: [start..end..step], может быть передан т.н. пустой элемент (` `)
  println(a[` `..4..1]) //[0, 1, 2, 3, 4]
  println(a[6..` `]) //[6, 7, 8]
  println(a[6..3..-1]) //[6, 5, 4, 3]
  println(a[` `..` `..2]) //[0, 2, 4, 6, 8]
  println(a[-3..` `]) //[6, 7, 8]
  println(a[` `..` `..-1]) // [8, 7, 6, 5, 4, 3, 2, 1, 0]
  ```