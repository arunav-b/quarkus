package me.arunav.mutiny

import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.Uni

/**
 *  If you don’t have a final subscriber, nothing is going to happen.
 *  Mutiny types are lazy, meaning that you need to express your interest.
 *  If you don’t the computation won’t even start.
 */

fun helloUniMutiny() {
    Uni.createFrom().item("Hello")
        .onItem().transform { "$it Mutiny" }
        .onItem().transform { it.uppercase() }
        .subscribe().with { println(">> $it") }
}

fun creatingMultis() {
    Multi.createFrom().items(1, 2, 3, 4, 5, 6)
        .onItem().transform { it * 2 }
        .select().first(3)
        .onFailure().recoverWithItem(0)
        .subscribe().with { println(it) }
}

fun main() {
    helloUniMutiny()
    creatingMultis()
}
