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
        .flatMap { (s, f) -> getOverlappingPoints(s, f) }
        .groupBy { it }
        .count { it.value.size > 1 }
}

fun star2(input: List<List<Point>>): Int {
    return input
        .flatMap { (s, f) -> getOverlappingPoints(s, f) }
        .groupBy { it }
        .count { it.value.size > 1 }
}

fun getOverlappingPoints(from: Point, to: Point): List<Point> {
    val max = max(abs(from.x - to.x), abs(from.y - to.y))
    return (0..max).map { i ->
        val x = if (from.x > to.x) from.x - i else if (from.x < to.x) from.x + i else from.x
        val y = if (from.y > to.y) from.y - i else if (from.y < to.y) from.y + i else from.y
        Point(x, y)
    }
}
