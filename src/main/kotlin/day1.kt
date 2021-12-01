import java.io.File

fun main() {
    val numbers = File("src/main/resources/aoc1.txt")
        .readLines()
        .map(String::toInt)

    first(numbers)
    second(numbers)
}

fun first(input: List<Int>) {
    val result = input.windowed(2, 1).count { it[1] > it[0] }
    println(result)
}

fun second(input: List<Int>){
    var previousN = Int.MAX_VALUE
    var count = 0

    val list = input.windowed(3, 1).map { it.reduce { i, x -> i + x } }

    for (i in list) {
        if (i > previousN) {
            count+=1
        }
        previousN = i
    }
    println(count)
}
