package arrays

@Suppress("UNCHECKED_CAST", "RedundantVisibilityModifier", "unused")
public class DynamicArray<T>(initialSize: Int = 10) {
    private val loadFactor = 2
    private var array: Array<T?> = arrayOfNulls<Any?>(initialSize) as Array<T?>
    private var size = 0

    public fun insert(item: T) {
        array[size++] = item
        if (size >= array.size) {
            inflate()
        }
    }

    public fun indexOf(item: T): Int {
        var index = 0
        while (index < array.size) {
            if (array[index] == item) {
                return index
            }
            index++
        }

        return -1
    }

    public fun removeAt(index: Int) {
        if (index !in 0..size) {
            throw ArrayIndexOutOfBoundsException()
        }

        var i = index
        while (i < size - 1) {
            array[i] = array[++i]
        }

        if (size - 1 < array.size / loadFactor) {
            deflate()
        }

        size--
    }

    override fun toString(): String {
        val sb = StringBuilder("[").apply {
            for (i in 0 until size - 1) {
                append(array[i]).append(", ")
            }

            if (size > 0) {
                append(array[size - 1])
            }

            append("]")
        }

        return sb.toString()
    }

    operator fun get(index: Int): T {
        if (index !in 0..size) {
            throw ArrayIndexOutOfBoundsException()
        }

        return array[index] ?: throw IllegalStateException()
    }

    private fun inflate() {
        val newSize = size * loadFactor
        val newArray = Array<Any?>(newSize) { null }
        for (i in 0 until size) {
            newArray[i] = array[i]
        }
        array = newArray as Array<T?>
    }

    private fun deflate() {
        val newSize = if (size % 2 == 0) size / loadFactor else size / loadFactor + 1
        val newArray = Array<Any?>(newSize) { null }
        for (i in 0 until newSize) {
            newArray[i] = array[i]
        }
        array = newArray as Array<T?>
    }
}
