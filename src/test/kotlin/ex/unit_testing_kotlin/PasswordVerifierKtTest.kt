package ex.unit_testing_kotlin

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.string.shouldContain

class PasswordVerifierDescribeTest : DescribeSpec({
    describe("verifyPassword") {
        describe("with a failing rule") {
            // fakeRule 함수를 한 단계 위로 올림
            val fakeRule = { _: String ->
                RuleResult(
                    passed = false,
                    reason = "fake reason"
                )
            }

            it("returns errors") {
                val errors = verifyPassword("any value", listOf(fakeRule))

                errors[0] shouldContain "fake reason"
            }
        }
    }
})
