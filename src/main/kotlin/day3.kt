import java.io.File

fun main() {
    val input = File("src/main/resources/aoc3.txt")
        .readLines()

    firstStar(input)
    secondStar(input)
}

fun secondStar(input: List<String>) {
    val data = getData(input)

    val oxygen = calculateOxygen(input.toMutableList(), data)
    val co2 = calculateCo2(input.toMutableList(), data )

    println(oxygen)
    println(co2)
    println(oxygen.times(co2))
}

fun calculateOxygen(input: MutableList<String>, data: MutableMap<Int, Int>, position: Int = 0): Int {
    if (input.size == 1) {
        return input.first().toInt(2)
    }

    if (data[position]!! >= 0) {
        removeValues(input, position, "0")
    } else {
        removeValues(input, position, "1")
    }

    return calculateOxygen(input, getData(input), position + 1)
}

fun calculateCo2(input: MutableList<String>, data: MutableMap<Int, Int>, position: Int = 0): Int {
    if (input.size == 1) {
        return input.first().toInt(2)
    }

    if (data[position]!! >= 0) {
        removeValues(input, position, "1")
    } else {
        removeValues(input, position, "0")
    }

    return calculateCo2(input, getData(input), position + 1)
}

fun getData(input: List<String>): MutableMap<Int, Int> {
    val data = mutableMapOf<Int, Int>()
    return input.map { it.mapIndexed { i, c -> i to c.toString() } }
        .flatten()
        .associateByTo(data, keySelector = { it.first }, valueTransform = { getValue(data, it) })
}

fun removeValues(input: MutableList<String>, position: Int, bit: String) {
    input.removeIf { it[position].toString() == bit }
}

fun firstStar(input: List<String>) {
    val data = mutableMapOf<Int, Int>()
    val binary = input.map { it.mapIndexed { i, c -> i to c.toString() } }
        .flatten()
        .associateByTo(data, keySelector = { it.first }, valueTransform = { getValue(data, it) })
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
