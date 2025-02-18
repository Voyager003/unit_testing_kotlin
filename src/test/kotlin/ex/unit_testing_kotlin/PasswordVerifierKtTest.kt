package ex.unit_testing_kotlin

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.string.shouldContain

class PasswordVerifierTest : DescribeSpec({
    describe("PasswordVerifier") {

        lateinit var verifier: PasswordVerifier

        beforeEach {
            verifier = PasswordVerifier()
        }

        describe("with a failing rule") {
            var fakeRule: (String) -> VerificationResult
            lateinit var errors: List<String>

            beforeEach {
                fakeRule = { _ -> VerificationResult(passed = false, reason = "fake reason") }
                verifier.addRule(fakeRule)
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
                val passingRule = { _: String -> VerificationResult(passed = true, reason = "") }
                verifier.addRule(passingRule)
            }

            it("should have no errors") {
                val errors = verifier.verify("any value")
                errors shouldHaveSize 0
            }
        }

        describe("with multiple rules") {
            beforeEach {
                val passingRule = { _: String -> VerificationResult(passed = true, reason = "") }
                val failingRule1 = { _: String -> VerificationResult(passed = false, reason = "error 1") }
                val failingRule2 = { _: String -> VerificationResult(passed = false, reason = "error 2") }

                verifier.addRule(passingRule)
                verifier.addRule(failingRule1)
                verifier.addRule(failingRule2)
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
