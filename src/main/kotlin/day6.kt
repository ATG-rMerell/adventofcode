import java.io.File

fun main() {
    val input = File("src/main/resources/aoc6.txt")
        .readLines()
        .map { row -> row.split(",").map { it.toInt() } }
        .first()

    val data = mapData(input)
    println(star1(data.toMutableMap(), 0))
}

fun mapData(input: List<Int>): Map<Int, Int> {
    return input.mapIndexed { i, v -> i to v }.toMap()
}

fun star1(input: MutableMap<Int, Int>, day: Int): Int {
    if (day == 80) {
        return input.size
    }

    var max = input.maxByOrNull { it.key }!!.key
    val temp = mutableMapOf<Int, Int>()

    for (i in input) {
        if (i.value == 0) {
            max = max.plus(1)
            temp[max] = 8
            input[i.key] = 6
        } else {
            val value = input.getValue(i.key)
            input[i.key] = value - 1
        }
    }

    input.putAll(temp)
    return star1(input, day + 1)
}

