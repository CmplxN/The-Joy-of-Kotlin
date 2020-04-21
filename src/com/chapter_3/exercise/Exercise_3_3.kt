package com.chapter_3.exercise

/*
    두 Int 값을 더하는 함수를 작성하라.
    (참조) typealias
 */

typealias intBinOp = (Int) -> (Int) -> Int
val add: intBinOp = {x: Int ->
    {y:Int ->
        x + y
    }
}
