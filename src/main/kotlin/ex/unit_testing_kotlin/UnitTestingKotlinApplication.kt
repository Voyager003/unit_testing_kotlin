package ex.unit_testing_kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UnitTestingKotlinApplication

fun main(args: Array<String>) {
	runApplication<UnitTestingKotlinApplication>(*args)
}
