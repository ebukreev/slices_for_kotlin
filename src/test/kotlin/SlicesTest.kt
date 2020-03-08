import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.sbpstu.jblab.get

internal class SlicesTest {
    @Test
    fun getFor1Dim() {
        val testList = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8)
        assertEquals(testList[0..6], listOf(0, 1, 2, 3, 4, 5, 6))
        assertEquals(testList[6 downTo 0], listOf(6, 5, 4, 3, 2, 1, 0))
        assertEquals(testList[1..8 step 2], listOf(1, 3, 5, 7))
        assertEquals(testList[7 downTo 0 step 2], listOf(7, 5, 3, 1))
    }

    @Test
    fun getFor2Dim() {
        val testTable = listOf(
            listOf(1, 2, 3, 4),
            listOf(5, 6, 7, 8),
            listOf(9, 10, 11, 12),
            listOf(13, 14, 15, 16)
        )

        assertEquals(testTable[0..2, 0..2],
            listOf(
                listOf(1, 2, 3),
                listOf(5, 6, 7),
                listOf(9, 10, 11)
            ))

        assertEquals(testTable[1..3, 0..2],
            listOf(
            listOf(5, 6, 7),
            listOf(9, 10, 11),
            listOf(13, 14, 15)
        ))

        assertEquals(testTable[0..3, 0..1],
            listOf(
            listOf(1, 2),
            listOf(5, 6),
            listOf(9, 10),
            listOf(13, 14)
        ))

        assertEquals(testTable[0..3 step 2, 0..2],
            listOf(
            listOf(1, 2, 3),
            listOf(9, 10, 11)
        ))

        assertEquals(testTable[3 downTo 0, 0..1],
            listOf(
            listOf(13, 14),
            listOf(9, 10),
            listOf(5, 6),
            listOf(1, 2)
        ))

        assertEquals(testTable[3 downTo 0 step 2, 1 downTo 0],
            listOf(
            listOf(14, 13),
            listOf(6, 5)
        ))
    }
}