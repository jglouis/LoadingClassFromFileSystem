package xyz.hexode

class Messager2 : Messager {
    override fun getMessage() =
            "Hello, I am ${Messager2::class.java.name} loaded by " +
                    "${Messager2::class.java.classLoader::class.java.simpleName} " +
                    "(hashCode: ${Messager2::class.java.classLoader.hashCode()})."
}
