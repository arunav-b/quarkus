package me.arunav.mutiny

import io.smallrye.mutiny.Uni

/**
 *  If you don’t have a final subscriber, nothing is going to happen.
 *  Mutiny types are lazy, meaning that you need to express your interest.
 *  If you don’t the computation won’t even start.
 */

fun helloMutiny() {
    Uni.createFrom().item("Hello")
        .onItem().transform { "$it Mutiny" }
        .onItem().transform { it.uppercase() }
        .subscribe().with { println(">> $it") }
}

fun main() {
    helloMutiny()
}
