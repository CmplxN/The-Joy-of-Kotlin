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
- 데이터가 존재할 수도 있고, 존재하지 않을 수도 있다.
- None과 Some으로 이루어진 sealed class
- None: Option<Nothing> - 데이터가 null인 경우
- Some: Option<T> - 데이터가 null이 아닌 경우
- getOrElse - None인 경우 default 아니면 값을 반환
- 이후 map과 flatMap을 이용해 함수를 옵션에 적용하고 Option타입의 값을 합성한다.
- sequence를 이용해 List<Option>을 Option<List>로 변환한다.

# ETC
- Kotlin은 널 참조를 사용하나, 참조가 널이 될 수 있다는 사실을 컴파일러에게 알려줘야한다.
즉 null에 대한 처리를 강제한다.
- Business null(실제로 없다는 것은 아닌데 로직상 널 이용)
- sentinel value(널임을 알려주는 널 아닌 값, Double.NaN)
- Kotlin은 즉시 계산 언어이기에 함수 파라미터를 쓰는지 여부와 상관없이 함수 실행 전, 함수 파라미터를 평가한다.
- 예외를 던지는 대신 아무일 없듯이 None을 반환하는 것은 여전히 큰 문제다. 7단원에서 향상