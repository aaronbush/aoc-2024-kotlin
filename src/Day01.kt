import kotlin.collections.sorted
import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val cols = input.map() { line ->
            val parts = line.split(" ", limit = 2).map { it.trim().toInt() }
            Pair(parts[0], parts[1])
        }
        val col1 = cols.map { it.first }.sorted()
        val col2 = cols.map { it.second }.sorted()

        var result = col1.zip(col2).fold(0) { acc, pair -> acc + abs(pair.first - pair.second) }
        return result
    }

    fun part2(input: List<String>): Int {
        val cols = input.map() { line ->
            val parts = line.split(" ", limit = 2).map { it.trim().toInt() }
            Pair(parts[0], parts[1])
        }
        val col1 = cols.map { it.first }
        val col2 = cols.map { it.second }

        val result = col1.fold(0) { acc, i -> acc + i * col2.count { it == i } }
        result.println()
        return result
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
