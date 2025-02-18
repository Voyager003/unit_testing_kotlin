package ex.unit_testing_kotlin

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.string.shouldContain

class PasswordVerifierDescribeTest : DescribeSpec({
    describe("verifyPassword") {
        describe("with a failing rule") {
            it("returns errors") {
                val fakeRule = { _: String ->
                    RuleResult(
                        passed = false,
                        reason = "fake reason"
                    )
                }

                val errors = verifyPassword("any value", listOf(fakeRule))

                errors[0] shouldContain "fake reason"
            }
        }
    }
})
