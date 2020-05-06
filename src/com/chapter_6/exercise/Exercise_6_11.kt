package com.chapter_6.exercise

/*
    List<Option<A>>를 Option<List<A>>로 엮어주는 패키지 수준 함수 sequence를 작성하라.
    원래 리스트의 모든 원소가 Some 인스턴스면 결과가 Some<List<A>>이고,
    None이 하나라도 있으면 None<List<A>>가 결과다. 시그니쳐는 다음과 같다.
    fun <A> sequence(list: List<Option<A>>): Option<List<A>>
 */