package xyz.hexode

import dalvik.system.DexClassLoader
import java.io.File

val classMap = mutableMapOf<String, Any>()

@Throws(RuntimeException::class)
fun <T> link(className: String, dex: File, codeCacheDir: File, parent: ClassLoader): T {
    @Suppress("UNCHECKED_CAST")
    return classMap.getOrPut(className) {
        try {
            val classLoader = DexClassLoader(dex.absolutePath,
                    codeCacheDir.absolutePath, null, parent)
            val clazz = classLoader.loadClass(className)
            @Suppress("UNCHECKED_CAST")
            clazz.newInstance()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    } as T
}
