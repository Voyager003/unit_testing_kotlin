package ex.unit_testing_kotlin

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContain

class PasswordVerifierTest : DescribeSpec({
    describe("PasswordVerifier") {
        describe("with a failing rule") {
            it("has an error message based on the rule.reason") {
                val verifier = PasswordVerifier()

                val fakeRule: (String) -> VerificationResult = {
                    VerificationResult(false, "fake reason")
                }

                verifier.addRule(fakeRule)

                val errors = verifier.verify("any value")

                errors shouldContain "fake reason"
            }
        }
    }
})
