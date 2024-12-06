import kotlin.math.abs

fun main() {
    fun allIncOrDec(): (List<Int>) -> Boolean = { levels ->
        val allIncreasing = levels.zipWithNext { a, b -> a.compareTo(b) }.all() { it == 1 }
        val allDecreasing = levels.zipWithNext { a, b -> a.compareTo(b) }.all() { it == -1 }
        allIncreasing || allDecreasing
    }

    fun within3(): (List<Int>) -> Boolean = { levels ->
        levels.zipWithNext { a, b -> abs(a - b) }.all() { it <= 3 }
    }

    fun part1(input: List<String>): Int {
        val reports = input.map { line ->
            line.split(" ").map { it.trim().toInt() }
        }
        val result = reports.filter(allIncOrDec()).filter(within3())
//        result.println()
        return result.size
    }

    fun part2(input: List<String>): Int {
        val reports = input.map { line ->
            line.split(" ").map { it.trim().toInt() }
        }
        val okReports = reports.mapIndexed { reportNum, levels -> reportNum to (allIncOrDec()(levels) && within3()(levels)) }
        val toCheckReportsIndexes = okReports.filterNot { it.second }.map { it.first }

        println("ok: ${okReports}")
        println("toCheck2: ${toCheckReportsIndexes}")

        val toCheckReports = reports.filterIndexed { index, levels -> toCheckReportsIndexes.contains(index) }
        val passed = toCheckReports.filter { levels ->
            var passed = false
            for (i in levels.indices) {
                val newLevel = levels.subList(0, i) + levels.subList(i + 1, levels.size)
                if (allIncOrDec()(newLevel) && within3()(newLevel)) {
                    passed = true
                    break
                }
            }
            passed
        }
        println("passed: ${passed.size}")

        return okReports.filter { it.second }.size + passed.size
    }

    check(part1(readInput("Day02_test")) == 2)
    check(part2(readInput("Day02_test")) == 4)

    val input = readInput("Day02")
//    part1(input).println()
    part2(input).println()
}
