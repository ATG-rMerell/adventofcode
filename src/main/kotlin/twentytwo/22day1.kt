package twentytwo

import java.io.File

fun main() {
    val numbers = File("src/main/resources/twentytwo/aoc1.txt")
        .readLines()

    //first(numbers)
    second(numbers)
}

fun first(input: List<String>) {
    var max = 0
    var sum = 0
    for (i in input) {
        if (i.isNotEmpty()) {
            sum += i.toInt()
        } else {
            if (max < sum) {
                max = sum
            }
            sum = 0
        }
    }
    println(max)
}

fun second(input: List<String>) {
    val max = mutableListOf(0, 0, 0)
    var sum = 0
    for (i in input) {

        if (i.isNotEmpty()) {
            sum += i.toInt()
        } else {
            if (max[2] < sum) {
                max[2] = sum
                max.sortDescending()
            }
            sum = 0
        }
    }
    println(max.reduce {i, x -> i+x})
}
