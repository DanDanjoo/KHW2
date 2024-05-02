import kotlin.random.Random
import helper.GameRecorder




fun main() {
    println("환영합니다! 아무개 단주의 야구게임입니다.")
    var recorder = GameRecorder()

    while (true) {
        val amuRandom = randomRandom()
        var gameIsOver = false
        var guessCount = 0
        println("$amuRandom")
        println( "1. 게임 시작 2. 게임 기록 3. 게임 종료 ")
        when (readLine()) {
            "1" -> {
                println("세 자리 숫자를 입력해주세요.")
                while (!gameIsOver) {
                    val guess = readLine() ?: ""
                    if (inputInput(guess)) {
                        val result = checkGuess(guess, amuRandom)
                        println(result)
                        guessCount++
                        if (result == "딩동댕~ 정답!") {
                            gameIsOver = true
                            recorder.addRecord(guessCount)
                        }
                    } else {
                        println("1부터 9까지의 서로 다른 세 자리 숫자를 입력하세요.")
                    }
                }
            }
            "2" -> {
                println("게임 기록 메시지 활성화!")
                recorder.displayGameRecords()

            }
            "3" -> {
                println("게임 종료!")
                return
            }
            else -> println("1, 2, 3 세 가지만 입력 가능합니다.")

        }

        println("아무 키나 누르세요.")
        readLine()

    }
}




fun randomRandom(): String {
    val digits = mutableListOf<Int>()
    while (digits.size < 3) {
        val digit = Random.nextInt(1, 10)
        if (!digits.contains(digit)) {
            digits.add(digit)
        }
    }
    return digits.joinToString("")
}


fun inputInput(guess: String): Boolean {
    return guess.length == 3 && guess.all { it in '1'..'9' } && guess.toSet().size == 3
}


fun checkGuess(guess: String, answer: String): String {
    var strikes = 0
    var balls = 0

    for (i in guess.indices) {
        if (guess[i] == answer[i]) {
            strikes++
        } else if (guess[i] in answer) {
            balls++
        }
    }

    return when {
        strikes == 3 -> "딩동댕~ 정답!"
        strikes > 0 && balls > 0 -> "$strikes 스트라이크, $balls 볼"
        strikes > 0 -> "$strikes 스트라이크"
        balls > 0 -> "$balls 볼"
        else -> "꽝인디유"
    }
}






