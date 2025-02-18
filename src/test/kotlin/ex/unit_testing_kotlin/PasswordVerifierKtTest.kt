package ex.unit_testing_kotlin

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.string.shouldContain

class PasswordVerifierDescribeTest : DescribeSpec({
    describe("Password Verification") {
        context("with a rule that always fails") {
            val fakeRule = { input: String ->
                RuleResult(passed = false, reason = "fake reason")
            }

            it("should include the rule's reason in the error messages") {
                val errors = verifyPassword("any value", listOf(fakeRule))
                errors[0] shouldContain "fake reason"
            }
        }
    }
})
