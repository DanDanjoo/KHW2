import kotlin.random.Random

fun main() {
    println("환영합니다! 아무개 단주의 야구게임입니다.")

    while (true) {
        val answer = randomRandom()
        var gameIsOver = false

        println( "1. 게임 시작! 2. 게임 기록 3. 게임 종료 ")
        when (readLine()) {
            "1" -> {
                println("게임을 시작합니다. 세 자리 숫자를 입력하세요: ")
                while (!gameIsOver) {
                    val guess = readLine() ?: ""
                    if (isValidInput(guess)) {
                        val result = checkGuess(guess, answer)
                        println(result)
                        if (result == "딩동댕~ 정답!") {
                            gameIsOver = true
                        }
                    } else {
                        println("잘못된 입력입니다. 1부터 9까지의 서로 다른 세자리 숫자를 입력하세요.")
                    }
                }
            }
        }

        println("게임이 종료되었습니다. 게임을 다시 시작하려면 아무 키나 누르세요.")
        readLine() // 사용자가 아무 키나 누를 때까지 대기
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

 fun isValidInput(guess: String): Boolean {
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
        else -> "Nothing"
    }
}
