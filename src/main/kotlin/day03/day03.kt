const val TREE = '#'
const val NO_TREE = '.'

fun main() {
    val lines = getResourceAsLines("data/day03.txt")
    val columnSize = lines[0].length

    /**
     * true -> has tree
     * false -> no tree
     */
    val grid: Array<Array<Boolean>> = Array(lines.size, { rowIndex ->
        Array(columnSize, { columnIndex ->
            lines[rowIndex][columnIndex] == TREE
        })
    })

    val treesCount = getTreesCountPart2(grid)

    printResult("$treesCount trees in ${grid.size} rows")
}


private fun getTreesCountPart1(grid: Array<Array<Boolean>>): Int {
    val VERTICAL_JUMP = 1
    val HORIZONTAL_JUMP = 3

    return countTreesFor(grid, VERTICAL_JUMP, HORIZONTAL_JUMP)
}

private fun getTreesCountPart2(grid: Array<Array<Boolean>>): Long {
    val paths = listOf(
        Pair(1, 1),
        Pair(3, 1),
        Pair(5, 1),
        Pair(7, 1),
        Pair(1, 2),
    )

    return paths.map { countTreesFor(grid, it.second, it.first) }
        .onEach { println(it) }
        .multiply()
}

private fun countTreesFor(grid: Array<Array<Boolean>>, verticalJump: Int, horizontalJump: Int): Int {
    var resultCount = 0

    var maxRowIndex= 0;

    for (rowIndex in 0..grid.size - 1 step verticalJump) {
        val row = grid[rowIndex]
        resultCount += if (rowHasTree(row, rowIndex, horizontalJump)) 1 else 0

        maxRowIndex = rowIndex
    }
    if (maxRowIndex != grid.size-1) {
        throw Error("Not reached last index")
    }

    return resultCount
}

private fun rowHasTree(row: Array<Boolean>, rowIndex: Int, horizontalJump: Int): Boolean {
    val index = (rowIndex * horizontalJump) % row.size

    return row[index]
}
