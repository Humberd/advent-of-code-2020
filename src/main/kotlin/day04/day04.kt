fun main() {
    val text = getResourceAsText("data/day04.txt")
    val rawBatches = text.split("\n\n").filter { it.length != 0 }
    val batches = rawBatches.map { batch ->
        val entries = batch.split("\\s".toRegex()).filter { it.length > 0 }

        val pairs = entries.map {
            val entry = BatchEntry.generateEntry(it)
            entry.key to entry
        }

        Batch(mapOf(*pairs.toTypedArray()))
    }

    val result = batches.map { part1IsValid(it) }
        .sum()

    println("$result valid out of ${batches.size} batches")
}

private fun part1IsValid(batch: Batch): Boolean {
    return if (batch.entries.size == 8) {
        true
    } else if (batch.entries.size < 7) {
        false
    } else {
        val hasCid = batch.entries.containsKey("cid")
        !hasCid
    }
}

private data class Batch(
    val entries: Map<String, BatchEntry>
)

private sealed class BatchEntry(
    val key: String
) {
    data class BirthYear(val value: Int) : BatchEntry("byr")
    data class IssueYear(val value: Int) : BatchEntry("iyr")
    data class ExpirationYear(val value: Int) : BatchEntry("eyr")
    data class Height(val value: Int, val unit: String?) : BatchEntry("hgt")
    data class HairColor(val value: String) : BatchEntry("hcl")
    data class EyeColor(val value: String) : BatchEntry("ecl")
    data class PassportId(val value: String) : BatchEntry("pid")
    data class CountryId(val value: String) : BatchEntry("cid")

    companion object {
        fun generateEntry(raw: String): BatchEntry {
            val (key, value) = raw.split(":")

            return when (key) {
                "byr" -> BirthYear(value.toInt())
                "iyr" -> IssueYear(value.toInt())
                "eyr" -> ExpirationYear(value.toInt())
                "hgt" -> {
                    val isOnlyNumeric = value.takeLast(2).toIntOrNull() != null
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

