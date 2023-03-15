package github.masterj3y.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val myApplicationDispatchers: MyApplicationDispatchers)

enum class MyApplicationDispatchers {
  IO
}
