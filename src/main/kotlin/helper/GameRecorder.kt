package helper

class GameRecorder {
    private val gameRecords = mutableListOf<String>()

    fun addRecord(guessCount: Int) {
        gameRecords.add("정답 횟수:$guessCount")
    }

    fun displayGameRecords() {
        if (gameRecords.isEmpty()) {
            println("기록이 없는디유.")
        } else {
            gameRecords.forEachIndexed { index, record ->
                println("${index + 1}. $record")
            }
        }
    }

}