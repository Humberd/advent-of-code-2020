import kotlin.math.sqrt

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


fun Iterable<Boolean>.sum(): Int {
    var sum = 0
    for (element in this) {
        sum += if (element) 1 else 0
    }
    return sum
}

fun Int.isSqrtAWholeNumber(): Boolean {
    val result = sqrt(this.toDouble())
    val intedResult = result.toInt()

    return (intedResult * intedResult).toDouble() == result
}

fun Int.isPowerOf2(): Boolean {
    val n = this
    return if (n == 0) false else Math.ceil(Math.log(n.toDouble()) / Math.log(2.0)).toInt() ==
            Math.floor(Math.log(n.toDouble()) / Math.log(2.0)).toInt()
}
