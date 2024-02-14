package racingcar.domain

class Car(val name: String) {
    var position: Int = 0
        private set

    fun move() {
        position++
    }
}