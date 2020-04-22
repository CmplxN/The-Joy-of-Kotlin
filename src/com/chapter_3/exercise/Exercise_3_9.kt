package com.chapter_3.exercise

/*
	다음 함수를 커리한 함수로 변환하라.
	fun <A,B,C,D> func(a:A,b:B,c:C,d:D):String = "$a $b $c $d"
 */

fun <A,B,C,D> func() = {a:A->
	{b:B->
		{c:C->
			{d:D->
				"$a $b $c $d"
			}
		}
	}
}