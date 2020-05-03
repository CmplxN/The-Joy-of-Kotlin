import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.max

fun main() = BufferedReader(InputStreamReader(System.`in`)).run{
    val N = readLine()!!.toInt()
    val map = mutableMapOf<Pair<Int,Int>,Int>()
    repeat(N){
        val st = StringTokenizer(readLine()!!)
        var (f,t) = Pair(st.nextToken()!!.toInt(),st.nextToken()!!.toInt())
        if(f>t) f=t.also{t=f}
        if(map.contains(Pair(f,t))) map[Pair(f,t)] = map[Pair(f,t)]!!.plus(1)
        else map[(Pair(f,t))] = 1
    }
    val D = readLine()!!.toInt()
    var ans = 0
    val pq = PriorityQueue<Int>()
    map.toSortedMap(Comparator { o1, o2 ->
        if(o1.second==o2.second) o1.first-o2.first
        else o1.second-o2.second})
        .forEach {
        repeat(it.value){_->pq.add(it.key.first)}
        while(!pq.isEmpty() && pq.peek().toLong() + D.toLong() < it.key.second.toLong()) pq.poll()
        ans = max(ans, pq.size)
    }
    print(ans)
}