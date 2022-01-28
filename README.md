# Quarkus

## Mutiny

### `Uni`

- A `Uni` represents a stream that can only emit either an item or a failure event. You rarely create instances of `Uni` yourself, but, instead, use a reactive client exposing a Mutiny API that provides Unis. That being said, it can be handy at times.
- A `Uni<T>` is a specialized stream that emits only an item or a failure. Typically, `Uni<T>` are great to represent asynchronous actions such as a remote procedure call, an HTTP request, or an operation producing a single result.
- `Uni<T>` provides many operators that create, transform, and orchestrate `Uni` sequences.
- As said, `Uni<T>` emits either an **item** or a **failure**. Note that the item can be `null`, and the `Uni` API has specific methods for this case.
- Unis are lazy by nature. To trigger the computation, you must have a final subscriber indicating your interest.

    ```kotlin
    fun creatingUnis() {
        val num = 10
        val item: Uni<Int> = Uni.createFrom().item(num)
        val supplier = Uni.createFrom().item { num * 5 }
    }
    
    fun creatingFailingUnis() {
        val exception = Uni.createFrom().failure<Int>(Exception("Invalid Int"))
        val exceptionWithASupplier = Uni.createFrom().failure<Int> { Exception("Exception using Supplier") }
    }
    
    fun creatingNullUni() {
        val nullItem = Uni.createFrom().nullItem<Int>()
    }
    
    fun creatingUniFromEmitter() {
        Uni.createFrom().emitter<String> {
            it.complete("result")
        }
    }
    ```