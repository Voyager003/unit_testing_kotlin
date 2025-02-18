package ex.unit_testing_kotlin

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.string.shouldContain

class PasswordVerifierTest : DescribeSpec({
    // 팩토리 함수
    fun makeFailingRule(reason: String): (String) -> VerificationResult = { _ ->
        VerificationResult(passed = false, reason = reason)
    }

    fun makePassingRule(): (String) -> VerificationResult = { _ ->
        VerificationResult(passed = true, reason = "")
    }

    describe("PasswordVerifier") {
        lateinit var verifier: PasswordVerifier

        beforeEach {
            verifier = PasswordVerifier()
        }

        describe("with a failing rule") {
            lateinit var errors: List<String>

            beforeEach {
                verifier.addRule(makeFailingRule("fake reason"))
            }

            it("has an error message based on the rule.reason") {
                errors = verifier.verify("any value")
                errors[0] shouldContain "fake reason"
            }

            it("has exactly one error") {
                errors = verifier.verify("any value")
                errors shouldHaveSize 1
            }
        }

        describe("with a passing rule") {
            beforeEach {
                verifier.addRule(makePassingRule())
            }

            it("should have no errors") {
                val errors = verifier.verify("any value")
                errors shouldHaveSize 0
            }
        }

        describe("with multiple rules") {
            beforeEach {
                verifier.addRule(makePassingRule())
                verifier.addRule(makeFailingRule("error 1"))
                verifier.addRule(makeFailingRule("error 2"))
            }

            it("should collect all error messages") {
                val errors = verifier.verify("any value")
                errors shouldHaveSize 2
                errors[0] shouldContain "error 1"
                errors[1] shouldContain "error 2"
            }
        }
    }
})
