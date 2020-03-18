import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.sbpstu.jblab.slicebleListOf
import ru.sbpstu.jblab.*
import ru.sbpstu.jblab.toSlicebleList

internal class SlicesTest {

    @Test
    fun getFor1Dim() {
        val testList = slicebleListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        assertEquals(testList[0..6], slicebleListOf(0, 1, 2, 3, 4, 5, 6))
        assertEquals(testList[6..0..-1], slicebleListOf(6, 5, 4, 3, 2, 1, 0))
        assertEquals(testList[1..8..2], slicebleListOf(1, 3, 5, 7))
        assertEquals(testList[7..0..-2], slicebleListOf(7, 5, 3, 1))
        assertEquals(testList[` `..6], slicebleListOf(0, 1, 2, 3, 4, 5, 6))
        assertEquals(testList[3..` `], slicebleListOf(3, 4, 5, 6, 7, 8, 9))
        assertEquals(testList[` `..` `], testList)
        assertEquals(testList[` `..` `..2], slicebleListOf(0, 2, 4, 6, 8))
        assertEquals(testList[-3..` `], slicebleListOf(7, 8, 9))
        assertEquals(testList[-5..` `..-1], slicebleListOf(5, 4, 3, 2, 1, 0))
        assertEquals(testList[-1..3..-2], slicebleListOf(9, 7, 5, 3))
        assertEquals(testList[4..-1..2], slicebleListOf(4, 6, 8))
        assertEquals(testList[-6..-2..1], slicebleListOf(4, 5, 6, 7, 8))
        assertEquals(testList[` `..` `..-1], slicebleListOf(9, 8, 7, 6, 5, 4, 3, 2, 1, 0))
        assertEquals(testList[6..-5], emptyList<Int>().toSlicebleList())
        assertEquals(testList[-6..-1..-1], emptyList<Int>().toSlicebleList())
        assertEquals(testList[3..8..-2], emptyList<Int>().toSlicebleList())
        assertEquals(testList[-2], 8)
        assertEquals(testList[listOf(1, 5, 8)], slicebleListOf(1, 5, 8))
    }

    @Test
    fun getFor2Dim() {
        val testTable = slicebleListOf(
            slicebleListOf(1, 2, 3, 4),
            slicebleListOf(5, 6, 7, 8),
            slicebleListOf(9, 10, 11, 12),
            slicebleListOf(13, 14, 15, 16)
        )

        assertEquals(testTable[0..2, 0..2],
            slicebleListOf(
                slicebleListOf(1, 2, 3),
                slicebleListOf(5, 6, 7),
                slicebleListOf(9, 10, 11)
            ))

        assertEquals(testTable[1..3, 0..2],
            slicebleListOf(
            slicebleListOf(5, 6, 7),
            slicebleListOf(9, 10, 11),
            slicebleListOf(13, 14, 15)
        ))

        assertEquals(testTable[0..3, 0..1],
            slicebleListOf(
            slicebleListOf(1, 2),
            slicebleListOf(5, 6),
            slicebleListOf(9, 10),
            slicebleListOf(13, 14)
        ))

        assertEquals(testTable[0..3..2, 0..2..1],
            slicebleListOf(
            slicebleListOf(1, 2, 3),
            slicebleListOf(9, 10, 11)
        ))

        assertEquals(testTable[3..0..-1, 0..1..1],
            slicebleListOf(
            slicebleListOf(13, 14),
            slicebleListOf(9, 10),
            slicebleListOf(5, 6),
            slicebleListOf(1, 2)
        ))

        assertEquals(testTable[3..0..-2, 1..0..-1],
            slicebleListOf(
            slicebleListOf(14, 13),
            slicebleListOf(6, 5)
        ))
    }
}

