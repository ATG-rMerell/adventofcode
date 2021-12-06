import java.io.File

fun main() {
    val input = File("src/main/resources/aoc6.txt")
        .readLines()
        .map { row -> row.split(",").map { it.toInt() } }
        .first()

    println(star1(input, 0))
}

fun star1(input: List<Int>, day: Int): Int {
    if (day == 80) {
        return input.size
    }

    val temp = mutableListOf<Int>()
    temp.addAll(input)

    for ((i, v) in input.withIndex()) {
        if (v == 0) {
            temp.add(8)
            temp[i] = 6
        } else {
            temp[i] = temp[i].minus(1)
        }
    }

    return star1(temp, day + 1)
}

