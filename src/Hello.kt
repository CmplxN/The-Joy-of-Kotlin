fun main(){
    val mapA = mapOf("Emergency" to "112", "Fire department" to "101", "Police" to "102")
    val mapB = mapOf("Emergency" to "911", "Police" to "102")
    val unionList = (mapA.asSequence() + mapB.asSequence())
            .toList()
            .distinct()
            .groupBy({ it.key }){it.value}
            .mapValues { (_, values) -> values.joinToString(",") }
    println(unionList)
}