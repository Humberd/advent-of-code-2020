import java.lang.Error

fun main() {
    val text = getResourceAsText("data/day01.txt")
    val orderedNumbers = text.split("\n")
        .filter { it.length > 0 }
        .map { it.toInt() }
        .sorted()

//    val pair = findPair(orderedNumbers)
    val triple = findTriple(orderedNumbers)

    printResult(triple.first * triple.second * triple.third)
}

private fun findPair(orderedNumbers: List<Int>): Pair<Int, Int> {
    for (i in 0..orderedNumbers.size - 1) {
        for (j in 1..orderedNumbers.size - 1) {
            val result = orderedNumbers[i] + orderedNumbers[j]
            if (result == 2020) {
                return Pair(orderedNumbers[i], orderedNumbers[j])
            }

            if (result > 2020) {
                break
            }
        }
    }

    throw Error("Not reached")
}

private fun findTriple(orderedNumbers: List<Int>): Triple<Int, Int, Int> {
    for (i in 0..orderedNumbers.size - 1) {
        for (j in 1..orderedNumbers.size - 1) {
            for (k in 2..orderedNumbers.size - 1) {
                val result = orderedNumbers[i] + orderedNumbers[j] + orderedNumbers[k]
                if (result == 2020) {
                    return Triple(orderedNumbers[i], orderedNumbers[j], orderedNumbers[k])
                }

                if (result > 2020) {
                    break
                }
            }
        }
    }

    throw Error("Not reached")
}
