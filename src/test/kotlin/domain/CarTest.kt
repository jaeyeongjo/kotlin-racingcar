package domain

import common.ERROR_MESSAGE_FORMAT
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatCode
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class CarTest {

    @ParameterizedTest
    @ValueSource(strings = ["", "po bi", "pobi1", "pobi!", "thomas"])
    fun `자동차의 이름이 1에서 5자의 영어 또는 한글로 이루어져 있지 않다면 예외 메세지를 포함한 IllegalArgumentException을 던진다`(name: String) {
        Assertions.assertThatIllegalArgumentException().isThrownBy { Car(name, RandomMovingStrategy()) }
            .withMessageContaining(ERROR_MESSAGE_FORMAT.format("자동차의 이름은 한글 혹은 영어로 공백이 없어야 하며 최소 1글자에서 최대 5글자까지 가능합니다."))
    }

    @Test
    fun `자동차의 이름이 1에서 5자의 영어 또는 한글로 이루어져 있다면 예외를 던지지 않는다`() {
        val name = "pobi"

        assertThatCode { Car(name, RandomMovingStrategy()) }.doesNotThrowAnyException()
    }

    @Test
    fun `moveAccordingToStrategy 메소드를 실행하면 전략대로 움직인다`() {
        val car = Car("pobi", object : MovingStrategy {
            override fun isMovable(): Boolean = true
        })

        car.moveAccordingToStrategy()

        assertThat(car.advancedCount).isEqualTo(1)
    }

    @Test
    fun `equals 메소드를 실행하면 비교 대상이 Car 클래스이고 같은 이름을 가지고 있다면 참을 반환한다`() {
        val car1 = Car("pobi", RandomMovingStrategy())
        val car2 = Car("pobi", object : MovingStrategy {
            override fun isMovable(): Boolean = true
        })

        val result = car1.equals(car2)

        assertThat(result).isTrue
    }
}