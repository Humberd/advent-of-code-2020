fun main() {
    val text = getResourceAsText("data/day04.txt")
    val rawBatches = text.split("\n\n").filter { it.length != 0 }
    val batches = rawBatches.map { batch ->
        val entries = batch.split("\\s".toRegex()).filter { it.length > 0 }

        Batch(
            entries.map { entry -> BatchEntry.generateEntry(entry) }
        )
    }

    println(batches)
}

private data class Batch(
    val entries: List<BatchEntry>
)

private sealed class BatchEntry(
    val key: String,
    val isOptional: Boolean = false
) {
    data class BirthYear(val value: Int) : BatchEntry("byr")
    data class IssueYear(val value: Int) : BatchEntry("iyr")
    data class ExpirationYear(val value: Int) : BatchEntry("eyr")
    data class Height(val value: Int, val unit: String?) : BatchEntry("hgt")
    data class HairColor(val value: String) : BatchEntry("hcl")
    data class EyeColor(val value: String) : BatchEntry("ecl")
    data class PassportId(val value: String) : BatchEntry("pid")
    data class CountryId(val value: String) : BatchEntry("cid", true)

    companion object {
        fun generateEntry(raw: String): BatchEntry {
            val (key, value) = raw.split(":")

            return when (key) {
                "byr" -> BirthYear(value.toInt())
                "iyr" -> IssueYear(value.toInt())
                "eyr" -> ExpirationYear(value.toInt())
                "hgt" -> {
                    val isOnlyNumeric = value.takeLast(2).toIntOrNull() == null
                    Height(
                        value = if (isOnlyNumeric) value.toInt() else value.take(value.length - 2).toInt(),
                        unit = if (isOnlyNumeric) null else value.takeLast(2)
                    )
                }
                "hcl" -> HairColor(value)
                "ecl" -> EyeColor(value)
                "pid" -> PassportId(value)
                "cid" -> CountryId(value)
                else -> {
                    throw Error("Not found")
                }
            }
        }
    }
}
