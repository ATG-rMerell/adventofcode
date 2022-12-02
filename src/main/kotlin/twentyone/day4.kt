import java.io.File

fun main() {
    val input = File("src/main/resources/aoc4.txt")
        .readLines()

    val numbers = getNumbers(input)
    val boards = getBoards(input.subList(2, input.size))
    println(getWinner(numbers, boards))
    println(getLoser(numbers, boards))
}

fun calculateResult(board: MutableList<MutableList<String>>, n: Int): Int {
    val leftovers = board.flatten().map { it.toInt() }.distinct().toMutableList()
    leftovers.removeAll { it == n }
    val sum = leftovers.reduce { a, b -> a + b }
    return sum*n
}

fun getLoser(numbers: List<String>, boards: MutableMap<Int, MutableList<MutableList<String>>>): Int {
    val resultBoard = mutableMapOf<Int, MutableList<MutableList<String>>>()
    resultBoard.putAll(boards)

    for (n in numbers) {
        for (b in boards) {
            for (r in b.value) {
                if (r.contains(n)) {
                    r.removeAll { i -> i == n }
                }
                if (r.isEmpty()) {
                    resultBoard.remove(b.key)
                    if (resultBoard.isEmpty()) {
                        return calculateResult(b.value, n.toInt())
                    }
                }
            }
        }
    }

    return 0
}

fun getWinner(numbers: List<String>, boards: MutableMap<Int, MutableList<MutableList<String>>>): Int {
    for (n in numbers) {
        for (b in boards) {
            b.value.forEach {
                if (it.contains(n)) {
                    it.removeAll { i -> i == n }
                }
                if (it.isEmpty()) {
                    return calculateResult(b.value, n.toInt())
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
