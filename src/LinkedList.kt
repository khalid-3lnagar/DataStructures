class Node(var value: Int?, var next: Node? = null)
class LinkedList(
    private var head: Node? = null,
    private var tail: Node? = null,
    private var count: Int = 0
) {

    init {
        head?.also(::addLast)
    }

    fun addFirst(newNode: Node) {
        if (count == 0)
            tail = newNode
        else
            newNode.next = head

        head = newNode
        count++

    }

    fun getFirst() = head

    fun addLast(newNode: Node) {
        if (count > 0)
            tail?.next = newNode
        else
            head = newNode

        tail = newNode
        count++
    }

    fun getLast() = tail

    fun removeFirst() {
        if (count < 2)
            initialState()
        else
            head = head!!.next

        count--
    }

    fun removeLast() {
        if (count < 2)
            initialState()
        else
            removeLastNode(head!!)

        count--
    }

    private fun removeLastNode(node: Node) {

        if (node.next == tail) {
            node.next = null
            tail = node

            return
        }
        removeLastNode(node.next!!)

    }

    private fun initialState() {
        head = null
        tail = null
    }

}

fun main() {
    val linkedList = LinkedList(Node(5))


    linkedList.addFirst(Node(3))


    linkedList.addLast(Node(7))

    for (i: Int in 0..4) {
        printList(linkedList.getFirst())
        linkedList.removeLast()

    }
}

fun printList(node: Node?) {
    if (node == null) {
        println()
        return
    }
    print(node.value)
    printList(node.next)
}