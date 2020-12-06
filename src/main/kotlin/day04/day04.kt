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

    val result = batches.map { part2IsValid(it) }
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

private fun part2IsValid(batch: Batch): Boolean {
    return if (batch.entries.size == 8) {
        batch.entries.values.all { it.isValid() }
    } else if (batch.entries.size < 7) {
        false
    } else {
        val hasCid = batch.entries.containsKey("cid")
        if (hasCid) {
            return false
        }

        batch.entries.values.all { it.isValid() }
    }
}

private data class Batch(
    val entries: Map<String, BatchEntry>
)

private sealed class BatchEntry(
    val key: String
) {
    data class BirthYear(val value: Int) : BatchEntry("byr") {
        override fun isValid(): Boolean {
            return value >= 1920 && value <= 2002
        }
    }

    data class IssueYear(val value: Int) : BatchEntry("iyr") {
        override fun isValid(): Boolean {
            return value >= 2010 && value <= 2020
        }
    }

    data class ExpirationYear(val value: Int) : BatchEntry("eyr") {
        override fun isValid(): Boolean {
            return value >= 2020 && value <= 2030
        }
    }

    data class Height(val value: Int, val unit: String?) : BatchEntry("hgt") {
        override fun isValid(): Boolean {
            if (unit == null) {
                return false
            }
            if (unit == "cm" && value >= 150 && value <= 193) {
                return true
            }
            if (unit == "in" && value >= 59 && value <= 76) {
                return true
            }

            return false
        }
    }

    data class HairColor(val value: String) : BatchEntry("hcl") {
        override fun isValid(): Boolean {
            return value.matches("#[0-9a-f]{6}".toRegex())
        }
    }

    data class EyeColor(val value: String) : BatchEntry("ecl") {
        override fun isValid(): Boolean {
            return value.matches("amb|blu|brn|gry|grn|hzl|oth".toRegex())
        }
    }

    data class PassportId(val value: String) : BatchEntry("pid") {
        override fun isValid(): Boolean {
            return value.matches("[0-9]{9}".toRegex())
        }
    }

    data class CountryId(val value: String) : BatchEntry("cid") {
        override fun isValid(): Boolean {
            return true
        }
    }

    abstract fun isValid(): Boolean;

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

