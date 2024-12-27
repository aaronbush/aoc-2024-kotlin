import java.math.BigInteger

fun main() {
    val mulRegex = "mul\\((\\d{1,3}),(\\d{1,3})\\)".toRegex()

    fun part1(input: List<String>): Int {
        return input.flatMap { memory ->
            mulRegex.findAll(memory).map { resultGroup ->
                val (a, b) = resultGroup.destructured
                a.toInt() * b.toInt()
            }
        }.sum()
    }

    fun part2(input: List<String>): Long {
        data class Accum(var sum: Long = 0, var inDo: Boolean = true)
        val memory = input.joinToString(separator = "")
        val result = "don't\\(\\)|do\\(\\)|$mulRegex".toRegex().findAll(memory)
            .fold(Accum()) { acc, match ->
                when (match.value) {
                    "don't()" -> acc.inDo = false
                    "do()" -> acc.inDo = true
                    else -> {
//                        println("$acc : ${match.value}")
                        if (acc.inDo) {
                            val (a, b) = match.destructured
                            acc.sum += a.toInt() * b.toInt()
                        }
                    }
                }
                acc
            }

        return result.sum
    }

    val testInput = readInput("Day03_test")
//    check(part1(testInput) == 161)

    // Read the input from the `src/Day01.txt` file.
//    val input = readInput("Day03")
//    part1(readInput("Day03")).println()
    part2(readInput("Day03")).println()
//    part2(listOf("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))",
//        "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"
//    )).println()
}
