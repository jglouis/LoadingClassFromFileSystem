package xyz.hexode

class MessagerOriginal : Messager {
    override fun getMessage() =
            "Hello, I am ${MessagerOriginal::class.java.name} loaded by " +
                    "${MessagerOriginal::class.java.classLoader::class.java.simpleName} " +
                    "(hashCode: ${MessagerOriginal::class.java.classLoader.hashCode()})."
}
