package com.chapter_2.example

import java.io.File

/*
    # 나중에 배우면 다시 이해해야할듯
    closable을 구현한 객체에 대해 자동으로 close() 호출을 보장 (Java의 try ... with ... resource)
 */

fun main(){
    val str = "C:\\Users\\zayne\\Desktop\\CN\\The-Joy-of-Kotlin\\src\\com\\chapter_2\\example\\myFile.txt"
    File(str).forEachLine{
        println(it)
    }
    File(str).useLines{it.forEach(::println)}
    val lines = File(str)
            .inputStream()
            .use {
                it.bufferedReader()
                        .lineSequence()
            }
    //lines.forEach(::println) use 블록을 벗어나서 스트림이 자동으로 닫혀서 IOException
}