fun getResourceAsText(path: String): String = object {}.javaClass.getResource(path).readText()

fun printResult(result: Any) {
    println("==================")
    println("==================")
    println("Result is: $result")
    println("==================")
    println("==================")
}
