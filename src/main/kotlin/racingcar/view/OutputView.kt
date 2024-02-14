package racingcar.view

import racingcar.domain.Car

class OutputView {
    fun printResultMessage() {
        println("실행 결과")
    }

    fun printRoundResult(cars : List<Car>){
        cars.forEach{
            println("${it.name} : ${"-".repeat(it.position)}")
        }
        println()
    }

}