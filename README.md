# 내일배움캠프 2주차 과제
## 계산기 구현하기

### 실행 이미지(Main.kt)

<p align="center" width="100%">
    <image src = "https://github.com/winteryy/nbc_calculator/blob/develop/screenshots/exc1.png" width="65%" >
    <image src = "https://github.com/winteryy/nbc_calculator/blob/develop/screenshots/exc2.png" width="30%" >
    <image src = "https://github.com/winteryy/nbc_calculator/blob/develop/screenshots/exc3.png" width="45%" >
    <image src = "https://github.com/winteryy/nbc_calculator/blob/develop/screenshots/exc4.png" width="45%" >
</p>

### 요구사항
- [x] +, -, *, /, % 연산 구현
- [x] 반복문 통한 무한 계산 
- [x] 연산 클래스들은 따로 생성해서 단일 책임 원칙 적용 
- [x] `AbstractOperation` 추상 클래스를 상속 받아 연산 클래스들 구현
- [x] 3개 이상의 수 연산(우선순위 반영)
- [x] 괄호 연산  
  

### What to
- 콘솔 창에서 입출력을 통해 계산 기능을 이용 가능
- 숫자, 연산자, 커맨드들 중 하나를 선택해 입력
    - 입력 가능 숫자: `BigDecimal` 범위 내 실수
    - 입력 가능 연산자: +, -, *, /, %, (, ), =
    - 입력 가능 커맨드: clear (현재 식 초기화), exit (계산기 종료)
- 연산자 중 '='를 입력해 연산 결과를 확인할 수 있음

### How to
- 부동 소수점 오차 제거 및 더 넓은 범위의 수 연산을 안정적으로 하기 위해 `BigDecimal`을 사용
- `Calculator` 클래스는 싱글톤 패턴으로 구현 시도
- 괄호를 비롯한 연산자들의 우선순위를 적용하고, 3개 이상의 수에 대한 연산을 위해 후위표기식으로 변환 후 연산