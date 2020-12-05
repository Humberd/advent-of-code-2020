fun getResourceAsText(path: String): String = object {}.javaClass.getResource(path).readText()
fun getResourceAsLines(path: String): List<String> = getResourceAsText(path).split("\n").filter { it.length > 0 }

fun printResult(result: Any) {
    println("==================")
    println("==================")
    println("Result is: $result")
    println("==================")
    println("==================")
}


fun Iterable<Int>.multiply(): Long {
    var sum = 1L
    for (element in this) {
        sum *= element
    }
    return sum
}
