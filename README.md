# 간단한 숫자퍼즐
무작위로 정렬된 1~8까지의 수를 오름차순으로 정렬하는 게임입니다.
***

### 1. 풀이과정 및 코드 설명
 다음과 같은 순서로 문제 풀이를 진행했습니다.

- createRandomNumbers 메서드
  - 1~8까지의 중복되지 않는 숫자를 무작위로 8번 생성합니다.
    - 중복된 수인지 확인하는 방법은 List의 contains 메서드를 활용했습니다.
  - 1~8까지의 작은 리스트를 만드는 경우에는 문제가 없지만, 큰 범위의 리스트를 만들 경우 while문이 과도하게 돌 수 있으므로 리팩토링이 필요할 것으로 보입니다.
  - 중복되지 않은 수라면, 리스트에 추가합니다.
- playTurns 메서드
  - 정답을 맞출 때까지 턴을 1씩 증가시키며 반복합니다.
  - 정답을 확인하기 위해 정렬된 숫자배열을 정답으로 가지고 있어야 합니다.
  - 처음 게임을 시작할 때 필요한 문구와 게임이 끝날 때 필요한 문구를 처리합니다.
- playTurn 메서드
  - 현재 턴의 수를 알려주고 한 턴을 수행합니다.
  - 턴을 수행하기 위해 입력받은 값이 오류가 있다면 턴이 증가하지 않고 새로 입력을 받습니다.
    - 입력을 처리하는 메서드에서 예외를 발생시키면 이를 catch하고 다시 입력을 받습니다.
  - 올바른 입력이 들어오면 입력 받은 두 수의 위치를 변경시킵니다.
    - List의 indexOf 메서드를 활용해 해당 턴의 리스트에서 입력받은 수의 index를 각각 찾습니다.
    - List의 set 메서드를 활용해 입력 받은 수의 위치를 서로 교환합니다.
- getChangeTargets 메서드
  - 턴을 수행하기 위해 필요한 사용자의 콘솔 입력을 받습니다.
- parseInputToChangeTagets 메서드
  - 입력받은 값은 String이므로 이를 두 개의 숫자로 이루어진 List로 파싱합니다.
    - 쉼표를 기준으로 입력을 나눴을 때, 길이가 2가 아니면 예외를 발생시킵니다.
    - 쉼표를 기준으로 나눈 입력들을 각각 Integer로 파싱하고 validation을 수행하도록 돕습니다.
- parseSplitInputToInteger 메서드
  - 쉼표를 기준으로 나눈 입력을 하나씩 전달받아 Integer로 파싱합니다.
    - 숫자가 아닌 입력의 경우 예외를 발생시킵니다. 
- validateTargetRange 메서드
  - 입력 받은 숫자가 1~8사이의 숫자가 아나리면 예외를 발생시킵니다.
***
### 3. 실행 결과
```
간단 숫자 퍼즐
Trun 1
[4, 8, 2, 6, 7, 1, 5, 3]

교환할 두 숫자를 입력>
10

잘못 입력하셨습니다. 다시 입력해 주세요.

교환할 두 숫자를 입력>
a,1

잘못 입력하셨습니다. 다시 입력해 주세요.

교환할 두 숫자를 입력>
 1,2

잘못 입력하셨습니다. 다시 입력해 주세요.

교환할 두 숫자를 입력>
4,,1

잘못 입력하셨습니다. 다시 입력해 주세요.

교환할 두 숫자를 입력>
4,1

Trun 2
[1, 8, 2, 6, 7, 4, 5, 3]

교환할 두 숫자를 입력>
2,8

Trun 3
[1, 2, 8, 6, 7, 4, 5, 3]

교환할 두 숫자를 입력>
100,-1

잘못 입력하셨습니다. 다시 입력해 주세요.

교환할 두 숫자를 입력>
3,8

Trun 4
[1, 2, 3, 6, 7, 4, 5, 8]

교환할 두 숫자를 입력>
4,6

Trun 5
[1, 2, 3, 4, 7, 6, 5, 8]

교환할 두 숫자를 입력>
7,5

Turn 5
[1, 2, 3, 4, 5, 6, 7, 8]

축하합니다! 5턴만에 퍼즐을 완성하셨습니다!
```