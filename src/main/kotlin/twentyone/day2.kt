import java.io.File

fun main() {
    val numbers = File("src/main/resources/aoc2.txt")
        .readLines()

    first(numbers)
    second(numbers)
}

fun first(input: List<String>) {
    var horizontal = 0
    var depth = 0

    for (i in input) {
        val (left, right) = i.split(" ")

        when (left) {
            "forward" -> horizontal+=right.toInt()
            "up" -> depth-=right.toInt()
            "down" -> depth+=right.toInt()
        }
    }

    println(horizontal * depth)
}

fun second(input: List<String>) {
    var horizontal = 0
    var depth = 0
    var aim = 0

    for (i in input) {
        val (left, right) = i.split(" ")

        when (left) {
            "forward" -> {
                horizontal+=right.toInt()
                depth+=(right.toInt()*aim)
            }
            "up" -> {
                aim-=right.toInt()
            }
            "down" -> {
                aim+=right.toInt()
            }
        }
    }

    println(horizontal * depth)
}
