import java.io.File
import kotlin.math.abs

fun main() {
    val input = File("src/main/resources/aoc7.txt")
        .readLines()
        .map { row -> row.split(",").map { it.toInt() } }
        .first()

    val max = input.maxOrNull()!!
    println(day7Star1(input, max))
    println(day7Star2(input, max))
}

fun day7Star1(input: List<Int>, max: Int): Int {
    return (1..max).map { x ->
        input.sumOf {
            abs(it - x)
        }
    }.minOrNull() ?: 0
}

fun day7Star2(input: List<Int>, max: Int): Int {
    return (1..max).map { x ->
        input.sumOf {
            (1..abs(it - x)).sumOf { it }
        }
    }.minOrNull() ?: 0
}
