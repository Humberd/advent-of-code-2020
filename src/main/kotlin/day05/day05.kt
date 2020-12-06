private const val F = 0
private const val B = 1

private const val L = 0
private const val R = 1


fun main() {
    val lines = getResourceAsLines("data/day05.txt")
    part2(lines)
}

// 590
private fun part2(lines: List<String>) {
    val grid = Array(128, { 0 })

    lines.forEach { line ->
        val rawRow = line.take(7).reversed()
        val rawColumn = line.takeLast(3).reversed()

        var rowValue = 0
        rawRow.forEachIndexed { index, it ->
            if (it == 'B') {
                rowValue = rowValue or (1 shl index)
            }
        }

        var columnValue = 0
        rawColumn.forEachIndexed { index, it ->
            if (it == 'R') {
                columnValue = columnValue or (1 shl index)
            }
        }

        grid[rowValue] = grid[rowValue] or (1 shl columnValue)
    }

    for (index in 0..grid.size - 1) {
        val element = grid[index]
        if (element != 0 && element.toDouble() != Math.pow(2.0, 8.0) - 1) {
            println("$index: ${Integer.toBinaryString(element)}")
        }
    }
}

private fun part1(lines: List<String>) {
    val maxId = lines.map { line ->
        val rawRow = line.take(7).reversed()
        val rawColumn = line.takeLast(3).reversed()

        var rowValue = 0
        rawRow.forEachIndexed { index, it ->
            if (it == 'B') {
                rowValue = rowValue or (1 shl index)
            }
        }

        var columnValue = 0
        rawColumn.forEachIndexed { index, it ->
            if (it == 'R') {
                columnValue = columnValue or (1 shl index)
            }
        }

        val seatId = (rowValue * 8) + columnValue
        println("$line: row $rowValue, column $columnValue, seat ID $seatId")
        seatId
    }.maxOrNull()

    printResult("Max Id is $maxId")
}
