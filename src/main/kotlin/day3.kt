import java.io.File

fun main() {
    val input = File("src/main/resources/aoc3.txt")
        .readLines()

    firstStar(input)
}

fun firstStar(input: List<String>) {
    val data = mutableMapOf<Int, Int>()
    val binary = input.map { it.mapIndexed { i, c -> i to c.toString() } }
        .flatten()
        .associateByTo(data, keySelector = { it.first }, valueTransform = { getValue(data, it) })
        .mapValues { if (it.value > 0) 1 else 0 }
        .values.joinToString("")

    println(binary.toInt(2)*binary.invert().toInt(2))
}

fun String.invert(): String {
    return this.map {if (it.toString() == "0") "1" else "0"}.joinToString("")
}
fun getValue(i: MutableMap<Int, Int>, pair: Pair<Int, String>): Int {
    val value = if (pair.second == "0") -1 else 1
    return if (i.containsKey(pair.first)) {
        i[pair.first]!!.plus(value)
    } else {
        value
    }
}
