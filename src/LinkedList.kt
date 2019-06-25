const val STRING_LINE = "\n\t************************************************\n"

fun main() {
    val linkedList = LinkedList()

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

fun printList(node: Node?) {
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

class LinkedList(
    private var head: Node? = null,
    private var tail: Node? = null,
    private var current: Node? = null,
    private var count: Int = 0
) {

    init {
        head?.also(::addLast)
    }

    fun addFirst(newNode: Node) {
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

    fun addLast(newNode: Node) {
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

    private fun removeLastNode(node: Node) {
        if (node.next == tail) {
            node.next = null
            tail = node

        } else removeLastNode(node.next!!)
    }

    fun removeByValue(value: Int) {

        if (contains(value)) {

            when (value) {
                head?.value -> head?.remove()

                tail?.value -> tail?.remove()

                else -> searchAndRemove(value)
            }
            count--

        }
    }

    private fun searchAndRemove(value: Int) {
        current = head
        recursiveSearchByValue(value)?.remove()
        current = null
    }

    private fun clear() {
        head = null
        tail = null
        count = 0
    }

    fun contains(value: Int): Boolean = if (head?.value == value || tail?.value == value) true else search(value)

    private fun search(value: Int): Boolean {
        current = head?.next
        val isHere = recursiveSearchByValue(value) != null
        current = null
        return isHere
    }

    private fun recursiveSearchByValue(value: Int): Node? {
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

class Node(var value: Int?, var next: Node? = null, var previous: Node? = null)

fun Node.remove() {
    next?.previous = previous
    previous?.next = next
}


