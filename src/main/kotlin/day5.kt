import java.awt.Point
import java.io.File
import java.lang.Integer.max
import kotlin.math.abs

fun main() {
    val input = File("src/main/resources/aoc5.txt")
        .readLines()
        .map { row ->
            row.split(" -> ").map {
                it.split(",").let { (x, y) -> Point(x.toInt(), y.toInt()) }
            }
        }
    println(star1(input))
    println(star2(input))
}

fun star1(input: List<List<Point>>): Int {
    return input
        .filter { (x, y) -> x.x == y.x || x.y == y.y }
        .flatMap { (s, f) -> count(s, f) }
        .groupBy { it }
        .count { it.value.size > 1 }
}

fun star2(input: List<List<Point>>): Int {
    return input
        .flatMap { (s, f) -> count(s, f) }
        .groupBy { it }
        .count { it.value.size > 1 }
}

fun count(start: Point, finish: Point): List<Point> {
    val max = max(abs(start.x - finish.x), abs(start.y - finish.y))
    return (0..max).map { i ->
        val x = if (start.x > finish.x) start.x - i else if (start.x < finish.x) start.x + i else start.x
        val y = if (start.y > finish.y) start.y - i else if (start.y < finish.y) start.y + i else start.y
        Point(x, y)
    }
}
