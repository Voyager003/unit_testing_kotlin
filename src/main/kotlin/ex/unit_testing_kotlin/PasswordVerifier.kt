package ex.unit_testing_kotlin

import java.util.ArrayList

fun verifyPassword(input: String, rules: List<(String) -> RuleResult>): List<String> {
    val errors = ArrayList<String>()

    rules.forEach { rule ->
        val result = rule(input)
        if (!result.passed) {
            errors.add("error ${result.reason}")
        }
    }
    return errors
}

data class RuleResult(val passed: Boolean, val reason: String)