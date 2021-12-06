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
        val data = mutableListOf<Pair<Int, Long>>()
        for (k in eachCount.keys) {
            if (k == 0) {
                data.add(Pair(6, eachCount[k]!!))
                data.add(Pair(8, eachCount[k]!!))
            } else {
                data.add(Pair(k - 1, eachCount[k]!!))
            }
        }

        eachCount = data.groupBy { it.first }
            .mapValues { it.value.sumOf { it.second } }
    }
    
    return eachCount.values.sum()

}
