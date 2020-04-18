import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

data class InputData(val dNum: Int, val votesEarned: Int)
fun main()=BufferedReader(InputStreamReader(System.`in`)).run {
    val (P, V) = readLine()!!.split(' ').map { it.toInt() }
    val temp = mutableMapOf<String, InputData>()
    repeat(P) {
        val st = StringTokenizer(readLine()!!)
        temp[st.nextToken()!!] = InputData(st.nextToken()!!.toInt(), st.nextToken()!!.toInt())
    }
    val total = temp.map { it.value.votesEarned }.sum()
    val subjects = temp.filter { it.value.dNum >= 5 || 100 * it.value.votesEarned / total >= 3 }
    val subTotalVote = subjects.map { it.value.votesEarned }.sum()
    val subTotalDistrict = subjects.map{it.value.dNum}.sum() + 47
    val t = subjects.map{
        val value = (subTotalDistrict * it.value.votesEarned / subTotalVote.toDouble() - it.value.dNum)/2
        println(value)
        if(value<1.0) 0 else (value+0.5).toInt()
    }
}