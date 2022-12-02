package twentytwo

import java.io.File

fun main() {
    val rounds = File("src/main/resources/twentytwo/aoc2.txt")
        .readLines().map { it.split("\\s".toRegex()) }

    firstDay2(rounds)
    secondDay2(rounds)
}

val ROCK = mapOf("A" to 3, "C" to 6)
val PAPER = mapOf("B" to 3, "A" to 6)
val SCISSOR = mapOf("C" to 3, "B" to 6)
fun firstDay2(rounds: List<List<String>>) {
    val sum = rounds.sumOf { calculatePoints(it.first(), it.last()) }
    println(sum)
}

fun calculatePoints(op: String, me: String): Int {
    return when (me) {
        "X" -> ROCK.getOrDefault(op, 0) + 1
        "Y" -> PAPER.getOrDefault(op, 0) + 2
        "Z" -> SCISSOR.getOrDefault(op, 0) + 3
        else -> 0
    }
}

val LOSE = mapOf("A" to 3, "B" to 1, "C" to 2)
val WIN = mapOf("A" to 8, "B" to 9, "C" to 7)
val DRAW = mapOf("A" to 4, "B" to 5, "C" to 6)

fun secondDay2(rounds: List<List<String>>) {
    val sum = rounds.sumOf { calculatePoints2(it.first(), it.last()) }
    println(sum)
}

fun calculatePoints2(op: String, me: String): Int {
    return when (me) {
        "X" -> LOSE[op]!!
        "Y" -> DRAW[op]!!
        "Z" -> WIN[op]!!
        else -> 0
    }
}
