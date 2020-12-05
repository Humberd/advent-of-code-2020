fun main() {
    val lines = getResourceAsLines("data/day02.txt")
    val rows = lines.map {
        val tokens = it.split(" ")
        val (rangeStart, rangeEnd, _) = tokens[0].split("-")

        Row(
            rangeStart = rangeStart.toInt(),
            rangeEnd = rangeEnd.toInt(),
            letter = tokens[1].get(0),
            password = tokens[2]
        )
    }

    val validPasswords = rows
        .map { isPasswordValidPart2(it) }
        .filter { it }

    printResult("${validPasswords.size} valid of ${rows.size} total")
}

private data class Row(
    val letter: Char,
    val password: String,
    val rangeStart: Int,
    val rangeEnd: Int
)

private fun isPasswordValidPart1(row: Row): Boolean {
    val matchingLetters = row.password.filter { it == row.letter }
    return matchingLetters.length >= row.rangeStart && matchingLetters.length <= row.rangeEnd
}

private fun isPasswordValidPart2(row: Row): Boolean {
    val valid1 = row.password.get(row.rangeStart - 1) == row.letter
    val valid2 = row.password.get(row.rangeEnd - 1) == row.letter

    return valid1 xor valid2
}
