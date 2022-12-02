import java.io.File

fun main() {
    val input = File("src/main/resources/aoc9.txt")
        .readLines()
        .map { it.map { it.digitToInt() }.toList() }

    println(day9Star1(input))
}

fun day9Star1(list: List<List<Int>>): Int {
    return list.mapIndexed { i, l ->
        l.mapIndexed { ii, value ->
            val above = if (i == 0) Int.MAX_VALUE else list[i - 1][ii]
            val below = if (i == list.lastIndex) Int.MAX_VALUE else list[i + 1][ii]
            val left = if (ii == 0) Int.MAX_VALUE else l[ii - 1]
            val right = if (ii == l.lastIndex) Int.MAX_VALUE else l[ii + 1]

            if (listOf(above, below, left, right).all { it > value }) value + 1 else 0
        }
    }.flatten().sum()
}
