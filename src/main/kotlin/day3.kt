import java.io.File

fun main() {
    val input = File("src/main/resources/aoc3.txt")
        .readLines()

    firstStar(input)
    secondStar(input)
}

fun firstStar(input: List<String>) {
    val binary = getData(input)
        .mapValues { if (it.value > 0) 1 else 0 }
        .values.joinToString("")

    println(binary.toInt(2) * binary.invert().toInt(2))
}

fun String.invert(): String {
    return this.map { if (it.toString() == "0") "1" else "0" }.joinToString("")
}

fun getValue(i: MutableMap<Int, Int>, pair: Pair<Int, String>): Int {
    val value = if (pair.second == "0") -1 else 1
    return if (i.containsKey(pair.first)) {
        i[pair.first]!!.plus(value)
    } else {
        value
    }
}

fun secondStar(input: List<String>) {
    val data = getData(input)

    val oxygen = calculate(input.toMutableList(), data, "0", "1")
    val co2 = calculate(input.toMutableList(), data, "1", "0")

    println(oxygen.times(co2))
}

fun calculate(input: MutableList<String>, data: MutableMap<Int, Int>, bit1: String, bit2:String, position: Int = 0): Int {
    if (input.size == 1) {
        return input.first().toInt(2)
    }

    if (data[position]!! >= 0) {
        input.removeIf { it[position].toString() == bit1 }
    } else {
        input.removeIf { it[position].toString() == bit2 }
    }

    return calculate(input, getData(input), bit1, bit2, position + 1)
}

fun getData(input: List<String>): MutableMap<Int, Int> {
    val data = mutableMapOf<Int, Int>()
    return input.map { it.mapIndexed { i, c -> i to c.toString() } }
        .flatten()
        .associateByTo(data, keySelector = { it.first }, valueTransform = { getValue(data, it) })
}
