# NPE 또는 IOBE 피하기
- 참조가 null인지 아닌지를 확인하지 않고는 절대 참조하지 않는다.
- 맵 안에 키가 존재하는지 아닌지 확안하지 않고는 절대 값을 가져오지 않는다.
- 리스트가 비어 있지 않고, 리스트 길이가 원하는 인덱스 값보다 크다는 사실을 확인하지 않고는 리스트에서 원소를 가져오지 않는다.
- 선택적 데이터(Optional Data)??

# Kotlin의 null 처리 방법
- nullable 타입을 선택시, 호출한 쪽에서 null 경우를 처리하도록 책임을 넘긴다.
- 프로그래머 수준에서는 원시 타입이 없으므로 박싱 및 언박싱시의 문제가 없다.
- null에 대한 연산자 ?. ?: !! 제공
- null의 전파를 막지는 않지만, 발생한 null의 처리 책임도 전파된다.

# Option 타입
- 

# ETC
- Kotlin은 널 참조를 사용하나, 참조가 널이 될 수 있다는 사실을 컴파일러에게 알려줘야한다.
즉 null에 대한 처리를 강제한다.
- Business null(실제로 없다는 것은 아닌데 로직상 널 이용)
- sentinel value(널임을 알려주는 널 아닌 값, Double.NaN)
- Kotlin은 즉시 계산 언어이기에 함수 파라미터를 쓰는지 여부와 상관없이 함수 실행 전, 함수 파라미터를 평가한다.