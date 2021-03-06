package com.tompietri.aoc2020.day4

fun day4FirstSolution(input: List<String>): Int {
    return input.toPassports()
        .filter { passport ->
            PassportField.requiredValues.all { passport[it] != null }
        }.count()
}

fun day4SecondSolution(input: List<String>): Int  {
    return input.toPassports()
        .filter { passport -> PassportField.requiredValues.all { passport[it] != null } }
        .filter { passport -> PassportField.requiredValues.all { it.isValidForPassport(passport) } }
        .count()
}

fun List<String>.toPassports(): List<Passport> {
    val passports = mutableListOf<Passport>()
    var currentFields = mutableMapOf<PassportField, String>()
    this.forEach { line ->
        if (line.isBlank()) {
            passports.add(Passport(currentFields))
            currentFields = mutableMapOf()
        } else {
            val newFileds = line.split(" ")
                    .map { it.split(":") }
                    .map { PassportField.acronymToPassportField[it[0]]!! to it[1] }
                    .toMap()
            currentFields.putAll(newFileds)
        }
    }

    passports.add(Passport(currentFields))

    return passports
}

data class Passport(private val fields: Map<PassportField, String>) {
    operator fun get(field: PassportField): String? = fields[field]
}

enum class PassportField(val acronym: String, val required: Boolean) {
    BIRTH_YEAR("byr", true) {
        override fun isValidForPassport(passport: Passport) = passport[BIRTH_YEAR]?.toInt()?.let { it in 1920..2002 } ?: !required
    },
    ISSUE_YEAR("iyr", true) {
        override fun isValidForPassport(passport: Passport) = passport[ISSUE_YEAR]?.toInt()?.let { it in 2010..2020 } ?: !required
    },
    EXPIRATION_YEAR("eyr", true) {
        override fun isValidForPassport(passport: Passport) = passport[EXPIRATION_YEAR]?.toInt()?.let { it in 2020..2030 } ?: !required
    },
    HEIGHT("hgt", true) {
        override fun isValidForPassport(passport: Passport): Boolean {
            return passport[HEIGHT]!!.let { field ->
                heightFormat.find(field)?.groupValues?.let { (_, height, unit) ->
                    when (unit) {
                        "cm" -> height.toInt() in 150..193
                        "in" -> height.toInt() in 59..76
                        else -> false
                    }
                } ?: false
            }
        }
    },
    HAIR_COLOR("hcl", true) {
        override fun isValidForPassport(passport: Passport) = passport[HAIR_COLOR]?.matches(hairColorFormat) ?: !required
    },
    EYE_COLOR("ecl", true) {
        override fun isValidForPassport(passport: Passport) = passport[EYE_COLOR]?.matches(eyeColorFormat) ?: !required
    },
    PASSPORT_ID("pid", true) {
        override fun isValidForPassport(passport: Passport) = passport[PASSPORT_ID]?.matches(passportIdFormat) ?: !required
    },
    COUNTRY_ID("cid", false) {
        override fun isValidForPassport(passport: Passport) = passport[COUNTRY_ID] != null || !required
    };


    abstract fun isValidForPassport(passport: Passport): Boolean

    companion object {
        val requiredValues = values().filter { it.required }
        val acronymToPassportField = values().map { it.acronym to it }.toMap()

        private val heightFormat = Regex("(\\d+)(cm|in)")
        private val hairColorFormat = Regex("^#(\\d|[a-f]){6}$")
        private val eyeColorFormat = Regex("^(amb|blu|brn|gry|grn|hzl|oth)$")
        private val passportIdFormat = Regex("^\\d{9}$")
    }

}
