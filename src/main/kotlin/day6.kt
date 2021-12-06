import java.io.File

fun main() {
    val input = File("src/main/resources/aoc6.txt")
        .readLines()
        .map { row -> row.split(",").map { it.toInt() } }
        .first()

    println(calculate(input, 80))
    println(calculate(input, 256))
}

fun calculate(input: List<Int>, days: Int): Long {
    var eachCount = input.groupingBy { it }.eachCount().mapValues { it.value.toLong() }

    for (i in 1..days) {
        eachCount = eachCount.keys.flatMap {
            if(it == 0) {
                listOf(6 to eachCount[it], 8 to eachCount[it])
            } else {
                listOf((it -1) to eachCount[it])
            }
        }.groupBy { it.first }
            .mapValues {
                it.value.mapNotNull { it.second  }.sum()
            }
    }
    return eachCount.values.sum()
}
