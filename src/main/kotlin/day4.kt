import java.io.File

fun main() {
    val input = File("src/main/resources/aoc4.txt")
        .readLines()

    val numbers = getNumbers(input)
    val boards = getBoards(input.subList(2, input.size))
    println(getWinner(numbers, boards))
}

fun calculateStar1(board: MutableList<MutableList<String>>, n: Int): Int {
    val leftovers = board.flatten().map { it.toInt() }.distinct().toMutableList()
    leftovers.removeAll { it == n }
    val sum = leftovers.reduce { a, b -> a + b }
    return sum*n
}

fun getWinner(numbers: List<String>, boards: MutableMap<Int, MutableList<MutableList<String>>>): Int {
    val nu = mutableListOf<String>()
    for (n in numbers) {
        nu.add(n)
        for (b in boards) {
            b.value.forEach {
                if (it.contains(n)) {
                    it.removeAll { i -> i == n }
                }
                if (it.isEmpty()) {
                    return calculateStar1(b.value, n.toInt())
                }
            }
        }
    }

    return 0
}

fun getNumbers(input: List<String>): List<String> {
    return input.first().split(",")
}

fun getBoards(input: List<String>): MutableMap<Int, MutableList<MutableList<String>>> {
    // boardsno -> rows & columns
    val boards = mutableMapOf<Int, MutableList<MutableList<String>>>()
    // Rows and Column
    val temp: MutableList<MutableList<String>> = mutableListOf()
    var n = 0

    for (l in input) {
        if (l.isNotBlank()) {
            val c = l.removeWhiteSpaces()
            temp.add(c.split(" ").toMutableList())
        } else if (l.isBlank() && temp.isNotEmpty()) {
            val rows = getRows(temp)
            boards[n] = rows
            n += 1
            temp.clear()
        }
    }

    return boards
}

fun getRows(i: MutableList<MutableList<String>>): MutableList<MutableList<String>> {
    val t = mutableListOf<MutableList<String>>()
    t.addAll(i)
    for (d in 0 until i.first().size) {
        t.add(i.map { it[d] }.toMutableList())
    }
    return t
}

fun String.removeWhiteSpaces(): String {
    return this.removePrefix(" ").replace("\\s+".toRegex(), " ")
}
