package arrays

fun main() {
    val array = DynamicArray<String>(3)
    array.insert("a")
    array.insert("b")
    array.insert("c")
    array.insert("d")
    array.insert("eee")

    println(array.indexOf("d"))
    println(array.indexOf("f"))
    println(array)
}
