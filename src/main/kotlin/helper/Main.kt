package helper

import helper.GameStarter
import helper.GameRecorder

fun main() {
    println("환영합니다! 아무개 단주의 야구게임입니다.")

    val baseball = GameStarter()
    val guess = ""
    val baseball2 = GameRecorder()

    while(true) {
        println("1. 게임 시작하기  2. 게임 기록 보기  3. 종료하기")
        val input = readLine()

        when(input) {
            "1" -> baseball.startGame()
            "2" -> baseball2.printRecords()
            "3" -> {
                println("종료 수고링!")
                break
            }
            else -> println("잘못된 입력입니다. 다시 시도하세요.")
        }
    }
}

