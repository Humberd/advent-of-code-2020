const val QUESTIONS_COUNT = 26

fun main() {
    val text = getResourceAsText("data/day06.txt")
    val groups = text.split("\n\n")

    val result = getResultPart1(groups)

    printResult(result)
}

private fun getResultPart1(groups: List<String>): Int {
    val groupSet = HashSet<Char>(QUESTIONS_COUNT)

    return groups.map { group ->
        groupSet.clear()

        group.filter { it != '\n' }
            .forEach { groupSet.add(it) }

        groupSet.size
    }.sum()
}
