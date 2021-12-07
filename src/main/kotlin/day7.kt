import java.io.File
import kotlin.math.abs

fun main() {
    val input = File("src/main/resources/aoc7.txt")
        .readLines()
        .map { row -> row.split(",").map { it.toInt() } }
        .first()

    println(day7Star1(input))
}

fun day7Star1(input: List<Int>): Int {
    return input.map { x ->
        input.sumOf {
            abs(it - x)
        }
    }.minOrNull() ?: 0
}
