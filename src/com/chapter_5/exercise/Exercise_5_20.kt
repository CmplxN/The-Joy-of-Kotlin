package com.chapter_5.exercise

/*
    List<A> 타입인 리스트의 각 원소에 대해 A를 List<B>로 변환하는 함수를 적용한 다음,
    결과 리스트들을 모두 연결해 평평하게 만든 List<B>를 반환하는 flatMap 함수를 작성하라.
    flatMap의 시그니처는 다음과 같다.
    fun<B> flatMap(f: (A) -> List<B>): List<B>
 */