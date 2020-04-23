package com.chapter_4.exercise

/*
    재귀적 계승 함수 값을 작성하라.
    val factorial: (Int) -> Int
    힌트 : 지연초기화를 사용하라.
 */

/*
    val factorial: (Int) -> Int ={ // 정의와 초기화가 동시에 이루어지는 구조
        if(it<=1) it
        else it * factorial(it-1) // factorial이 아직 정의되어있지 않다. (fun이 아님)
    }
    ==> 정의를 먼저하고 값은 나중에 변경하는 방식 필요
 */

object Factorial{
    private lateinit var factorialTemp: (Int) -> Int // 이걸 var말고 val 할 수는 없는거야?
    init{
        factorialTemp = {
            if(it<=1) it else factorialTemp(it-1) * it
        }
    }
    val factorial = factorialTemp // lateinit var를 private하고 val에 복사
}

// 함수 값의 경우 tailrec을 지원해주지 않는다. 필요하면 함수 참조를 사용해라. (fun 으로 만들고 참조)

val factorial: (Int) -> Int by lazy { {it: Int -> // ide는 불필요라는데 명시 안하면 컴파일 에러.
        if(it<=1) it else factorial(it-1) * it
    } }


fun main(){
    println(factorial(6))
}