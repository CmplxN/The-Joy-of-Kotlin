package com.chapter_4.example

/*
    공재귀도 스택공간을 사용하긴 한다. 루프로 바꾸자.
    함수가 제일 마지막에 자기 자신을 호출한다면 tailrec 키워드를 사용하면 된다. (tail call elimination)
 */

fun toStringCorec2(list:List<Char>): String{ // 앞의 toStrinG을 루프로 변경
    var s = ""
    for (c in list) s = append(s, c)
    return s
}

// 전통적인 제어구조를 사용함. (< vs <= 이라던가 인덱스 증가 위치라던가 어려움)

fun sum(n: Int): Int{
    var s = 0
    var i = 0
    while(i<=n){
        s+=i
        i+=1
    }
    return s
}

/*
    전통적인 방식에서 썼던 변수들을 함수 파라미터로 쓰는 블록 내부의 함수(helper)를 추가한다.
    helper를 이용해서 tailrec helper함수를 완성한다.
 */

fun suM(n: Int): Int{
    tailrec fun sum(s:Int, i:Int): Int =
            if(i>n) s else sum(s+i,i+1)
    return sum(0,n)
}