package ex.unit_testing_kotlin


class PasswordVerifier {
    private val rules = mutableListOf<(String) -> VerificationResult>()

    fun addRule(rule: (String) -> VerificationResult) {
        rules.add(rule)
    }

    fun verify(input: String): List<String> {
        val errors = mutableListOf<String>()
        for (rule in rules) {
            val result = rule(input)
            if (!result.passed) {
                errors.add(result.reason)
            }
        }
        return errors
    }
}

data class VerificationResult(val passed: Boolean, val reason: String)