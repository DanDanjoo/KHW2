package helper

import kotlin.random.Random

class GameStarter {

    private val answer: String

    init {
        answer = RandomNumber()
    }

    fun startGame(guess: String): String {
        if (!isVaildInput(guess)) {
            return ""
        }
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
            strikes == 3 -> " 딩동댕~ 정답! "
            strikes > 0 || balls > 0 -> " 스트라이크: $strikes, 볼: $balls "
            else -> " 아웃! "

        }
    }

private fun RandomNumber(): String {
    val digits = (1..9).toList().shuffled().take(3)
    return digits.joinToString("")
}

private fun isVaildInput(guess: String): Boolean {
    return guess.length == 3 && guess.all {it in '1'..'9'} && guess.toSet().size == 3
}

    fun startGame() {
        TODO("Not yet implemented")
    }



}




