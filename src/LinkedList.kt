class Node(val value: Int?, var next: Node? = null)

fun main() {

    val first = Node(3)

    val middle = Node(5)
    first.next = middle

    val last = Node(7)
    middle.next = last

    printList(first)

}

fun printList(node: Node?) {
    node
        ?.also { println(it.value) }
        ?.also { printList(it.next) }
        ?: return
}