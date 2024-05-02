# 개인 프로젝트 - 숫자 야구 게임



## 🔍 클래스 다이어그램
![K-089](https://github.com/DanDanjoo/KHW/assets/162088392/bac80faa-09d8-4ded-840f-084a55b1f1fb)


### 😊 설명
게임 실행, 출력 담당 'main'과 게임 기록을 담당하는 'GameRecorder'를 class로 나눴습니다.  
설명에 앞서, 함수와 클래스부터 언급하는것이 보는 사람에게 있어서 이해가 더 쉬울거같아, 언급 후 main 로직을 설명 드리겠습니다. 


### ❕ randomRandom함수
```
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
```
무작위로 생성된 세 자리 숫자를 문자열로 반환하는 역할을 합니다.
#### ❔ 상세 설명
val digits = mutableListOf<Int>() - 빈 MutableList를 생성하여 숫자 저장 : 이 리스트는 중복되지 않는 세 자리 숫자를 만들 때 사용됩니다.   
while (digits.size < 3) {} - 리스트에 세 개의 숫자가 들어갈 때까지 루프를 반복   
val digit = Random.nextInt(1, 10) - 1부터 9 사이의 랜덤한 숫자를 생성함 : 앞에 숫자 1은 from, 뒷 숫자 10은 until으로 1이상 10미만이 됩니다.  
if (!digits.contains(digit)) {} - 생성된 숫자가 리스트에 이미 존재하지 않는지 확인 : 중복된 숫자를 허용하지 않기 위함입니다.   
digits.add(digit) - 중복된 숫자가 아니라면 리스트에 추가   
return digits.joinToString("") - 리스트에 저장된 세 자리의 숫자를 문자열로 변환하여 반환함   
joinToString("") 함수는 리스트의 각 요소를 합쳐 하나의 문자열로 만들어 줍니다.   
만약에 digits의 리스트가 [1, 2, 3]이라면 이 함수는 "123" 문자열을 반환합니다.
### ❕ inputInput 함수
```
fun inputInput(guess: String): Boolean {
    return guess.length == 3 && guess.all { it in '1'..'9' } && guess.toSet().size == 3
}
```
입력한 값이 유효한지를 확인하는 역할을 합니다.
#### ❔ 상세 설명
fun inputInput(guess: String): Boolean - 문자열을 매개변수로 받고, 입력이 유효한지 여부를 판단하여 참 또는 거짓 값을 반환합니다.  
guess.length == 3 - 추측이 세 자리인지 확인  
guess.all { it in '1'..'9' } - 추측의 모든 문자가 '1'에서 '9' 사이에 있는지 확인 : it은 여기서 각 문자를 의미합니다.  
즉, it은 guess 문자열의 각 문자를 순회하면서 해당하는 문자가 '1'부터 '9'까지의 범위에 속하는지를 확인하는데 사용된다고 보시면 됩니다.
guess.toSet().size == 3 - 추측에 중복된 숫자가 없는지 확인함

#### ❕ checkGuess 함수
```
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
```
추측(guess)과 정답(answer)을 비교하여 게임 결과를 평가하는 역할을 합니다.
#### ❔ 상세 설명
```
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
```
strikes와 balls 초기화 - 이 값은 처음에 모두 0으로 초기화된다. : 스트라이크와 볼 변수는 각각의 개수를 추적하기 위함입니다.   
for 루프 - 추측 문자열 guess의 각 문자에 대해 루프를 실행한다. : indices는 문자열의 인덱스 범위를 나타냅니다.  
조건문 - 각 문자에 대해, guess[i]와 answer[i]를 비교하여 해당 위치에 있는 숫자가 같은지를 확인한다.  
만약 두 숫자가 같다면, 스트라이크 변수를 증가시키고, 두 숫자가 다르고, guess[i]가 answer에 포함되어 있다면, 볼 변수를 증가시킵니다.  

```
    return when {
        strikes == 3 -> "딩동댕~ 정답!"
        strikes > 0 && balls > 0 -> "$strikes 스트라이크, $balls 볼"
        strikes > 0 -> "$strikes 스트라이크"
        balls > 0 -> "$balls 볼"
        else -> "꽝인디유"
    }
}
```
결과 리턴 - when 조건문을 운용하여 게임 결과를 반환한다.  
우선 세 자리 모두가 일치하면 "딩동댕~ 정답!", 스트라이크와 볼의 개수에 따라 다른 결과를 반환하고  
스트라이크와 볼이 모두 없으면 "꽝인디유"를 반환합니다.

#### GameRecorder 클래스



#### 💻 실행 예시










## 📈 과제 요구사항
### 필수 구현 기능

- 요구사항별로 상세 기능을 생각해요 / 사용하면서 발생할 수 있는 예외사항들을 고려해봅니다.
- [ ]  입력과 출력
    - [ ]  입력
        - [ ]  서로 다른 3자리 수
        - [ ]  게임 시작, 기록 보기, 종료를 구분하는 수 입력
            - [ ]  필수 구현에서는 실행 시, 바로 게임 시작
            - [ ]  선택 구현에서 시작, 기록, 종료 구분
    - [ ]  출력
        - [ ]  입력한 수에 대한 결과값을 “볼, 스트라이크, Nothing”으로 표시
- [ ]  요구사항
    - [ ]  1에서 9까지의 서로 다른 임의의 수 3개를 정하고 맞추는 게임입니다
    - [ ]  정답은 랜덤으로 만듭니다.(1에서 9까지의 서로 다른 임의의 수 3자리)
    - [ ]  상세
        - [ ]  정답을 맞추기 위해 3자리수를 입력하고 힌트를 받습니다
            - [ ]  힌트는 야구용어인 **볼**과 **스트라이크**입니다.
            - [ ]  같은 자리에 같은 숫자가 있는 경우 **스트라이크**, 다른 자리에 숫자가 있는 경우 **볼**입니다.
                - ex) 정답 : 456 인 경우
                    - 435를 입력한 경우 → 1스트라이크 1볼
                    - 357를 입력한 경우 → 1스트라이크
                    - 678를 입력한 경우 → 1볼
                    - 123를 입력한 경우 → Nothing
            - [ ]  만약 올바르지 않은 입력값에 대해서는 오류 문구를 보여주세요.
            - [ ]  3자리 숫자가 정답과 같은 경우 게임이 종료됩니다.

### 선택 구현 기능

- [ ]  1번
    - [ ]  프로그램을 시작할 때 안내문구를 보여주세요.
    - [ ]  1번 게임 시작하기의 경우 **“필수 구현 기능”** 의 예시처럼 게임이 진행됩니다
    - [ ]  정답을 맞혀 게임이 종료된 경우 위 안내문구를 다시 보여주세요
        
        ```kotlin
        // 예시
        환영합니다! 원하시는 번호를 입력해주세요
        1. 게임 시작하기  2. 게임 기록 보기  3. 종료하기
        1 // 1번 게임 시작하기 입력
        
        < 게임을 시작합니다 >
        숫자를 입력하세요
        .
        .
        .
        ```
        
    - 코드 뼈대로 보기
        
        ```swift
        func start() {
            while XX { **// while 뒤의 조건(XX) 작성해보기
              // 1. 안내문구 출력**
              
        	    **// 2. 유저의 입력값을 받음
        	       
        	    // 3. 입력값에 따라 처리(switch - case 활용해보기) When을 쓸 수도 있겠죠!**
        	    switch 입력값 {
        		    case 
        			    **// 1을 입력한 케이스 작성**
        		    case 
        			    **// 2을 입력한 케이스 작성**
        		    case 
        			    **// 3을 입력한 케이스 작성**
        		    default
        	    }
            }
        	}
        }
        ```
        
- [ ]  2번
    - [ ]  정답이 되는 숫자를 0에서 9까지의 서로 다른 3자리의 숫자로 바꿔주세요
        - 맨 앞자리에 0이 오는 것은 불가능합니다
            - 092 → 불가능
            - 870 → 가능
            - 300 → 불가능
- [ ]  3번
    - [ ]  실행 시, 2번 게임 기록 보기의 경우 완료한 게임들에 대해 시도 횟수를 보여줍니다.
        
        ```kotlin
        // 예시
        환영합니다! 원하시는 번호를 입력해주세요
        1. 게임 시작하기  2. 게임 기록 보기  3. 종료하기
        2 // 2번 게임 기록 보기 입력
        
        < 게임 기록 보기 >
        1번째 게임 : 시도 횟수 - 14
        2번째 게임 : 시도 횟수 - 9
        3번째 게임 : 시도 횟수 - 12
        .
        .
        .
        ```
        
- [ ]  4번
    - [ ]  실행 시, 3번 종료하기의 경우 프로그램이 종료됩니다.
        - 이전의 게임 기록들도 초기화됩니다.
        
        ```kotlin
        // 예시
        환영합니다! 원하시는 번호를 입력해주세요
        1. 게임 시작하기  2. 게임 기록 보기  3. 종료하기
        3 // 3번 종료하기 입력
        
        < 숫자 야구 게임을 종료합니다 >
        ```
        
    - [ ]  1, 2, 3 이외의 입력값에 대해서는 오류 메시지를 보여주세요
        
        ```kotlin
        // 예시
        환영합니다! 원하시는 번호를 입력해주세요
        1. 게임 시작하기  2. 게임 기록 보기  3. 종료하기
        4
        
        올바른 숫자를 입력해주세요!
        ```

## ✉️ 과제 제출 방법
- 제출 링크 : https://nbcamp.spartacodingclub.kr/mypage/assignments
- 제출 기한 : 05/03(금) 14:00까지
    - 제출 이후, 해설 세션이 진행됩니다.
    - 해설 세션을 참고하고 보완해 다시 제출하고, 튜터님께 피드백을 받으러 찾아갑니다.
      
      
## 환경설정
Language : Kotlin  
IDEA : IntelliJ  
JDK : 17.0.10
