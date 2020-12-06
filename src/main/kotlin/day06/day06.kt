const val QUESTIONS_COUNT = 26

fun main() {
    val text = getResourceAsText("data/day06.txt")
    val groups = text.split("\n\n")

    val result = getResultPart2(groups)

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

private fun getResultPart2(groups: List<String>): Int {
    return groups.map { group ->
        val people = group.split("\n").filter { it != "" }

        /**
         * There is aa 32 bit int for every letter
         * Each bit in an int is a representation if a given person answered 'yes'
         * 100 - person 1 and person 2 answered 'no', but person 3 answered 'yes'
         * 1111 - all 4 people answered 'yes'
         */
        val letterBytes = Array(QUESTIONS_COUNT, { 0 })

        people.forEachIndexed { personIndex, person ->
            person.forEach { letter ->
                val bitPosition = (letter - 97).toInt()
                letterBytes[bitPosition] = letterBytes[bitPosition] or (1 shl personIndex)
            }
        }

        print("Group: ")
        letterBytes.forEach {
            print("${Integer.toBinaryString(it)}, ")
        }
        println("")

        val peopleCount = people.size
        val requiredNumber = (Math.pow(2.0, peopleCount.toDouble()) - 1).toInt()
        val foo = letterBytes.map {
            if (it == requiredNumber) {
                1
            } else
                0
        }
        foo.sum()
    }.sum()
}
