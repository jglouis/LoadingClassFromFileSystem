package xyz.hexode

class Messager1 : Messager {
    override fun getMessage() =
            "Hello, I am ${Messager1::class.java.name} loaded by " +
                    "${Messager1::class.java.classLoader::class.java.simpleName} " +
                    "(hashCode: ${Messager1::class.java.classLoader.hashCode()})."
}
