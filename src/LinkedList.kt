const val STRING_LINE = "\n\t************************************************\n"

fun main() {
    val linkedList = LinkedList<Int>()

    with(linkedList) {
        //Add some data
        addFirst(Node(5))
        addFirst(Node(0))
        addFirst(Node(7))

        //print that data
        printList(getFirst())
        println(size())

        //try contains method
        contains(0).also(::println)
        println(linkedList.size())

        println(STRING_LINE)
        removeByValue(0)
        println(STRING_LINE)

        contains(0).also(::println)

        printList(getFirst())
        println(linkedList.size())
    }


}

fun <T> printList(node: Node<T>?) {
    if (node == null) {
        println()
        return
    }
    with(node) {
        println("value $value\nnext value: ${next?.value}\nprevious value: ${previous?.value}")
    }
    println(STRING_LINE)
    printList(node.next)
}

class LinkedList<T>(
    private var head: Node<T>? = null,
    private var tail: Node<T>? = null,
    private var current: Node<T>? = null,
    private var count: Int = 0
) {

    init {
        head?.also(::addLast)
    }

    fun addFirst(newNode: Node<T>) {
        if (count == 0)
            tail = newNode
        else {
            head?.previous = newNode
            newNode.next = head
        }


        head = newNode
        count++

    }

    fun getFirst() = head

    fun addLast(newNode: Node<T>) {
        if (count == 0)
            head = newNode
        else {
            tail?.next = newNode
            newNode.previous = tail
        }

        tail = newNode
        count++
    }

    fun getLast() = tail

    fun removeFirst() {
        when (count) {
            0 -> return
            1 -> clear()
            else -> {
                head?.next?.previous = null
                head = head?.next
                count--
            }
        }
    }

    fun removeLast() {
        when (count) {
            0 -> return
            1 -> clear()
            else -> {
                removeLastNode(head!!)
                count--
            }
        }

    }

    private fun removeLastNode(node: Node<T>) {
        if (node.next == tail) {
            node.next = null
            tail = node

        } else removeLastNode(node.next!!)
    }

    fun removeByValue(value: T) {

        if (contains(value)) {

            when (value) {
                head?.value -> head?.remove()

                tail?.value -> tail?.remove()

                else -> searchAndRemove(value)
            }
            count--

        }
    }

    private fun searchAndRemove(value: T) {
        current = head
        recursiveSearchByValue(value)?.remove()
        current = null
    }

    private fun clear() {
        head = null
        tail = null
        count = 0
    }

    fun contains(value: T): Boolean = if (head?.value == value || tail?.value == value) true else search(value)

    private fun search(value: T): Boolean {
        current = head?.next
        val isHere = recursiveSearchByValue(value) != null
        current = null
        return isHere
    }

    private fun recursiveSearchByValue(value: T): Node<T>? {
        if (current == tail) return null

        return if (current?.value == value)
            current
        else {
            current = current?.next
            recursiveSearchByValue(value)
        }
    }

    fun size() = count

}

class Node<T>(var value: T?, var next: Node<T>? = null, var previous: Node<T>? = null) {
    fun remove() {
        next?.previous = previous
        previous?.next = next
    }
}




