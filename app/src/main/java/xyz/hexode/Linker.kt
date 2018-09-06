package xyz.hexode

import dalvik.system.DexClassLoader
import java.io.File

@Throws(RuntimeException::class)
fun <T>link(className: String, dex: File, codeCacheDir: File, parent: ClassLoader) : T {
    try {
        val classLoader = DexClassLoader(dex.absolutePath,
                codeCacheDir.absolutePath, null, parent)
        val clazz = classLoader.loadClass(className)
        @Suppress("UNCHECKED_CAST")
        return clazz.newInstance() as T
    } catch (e: Exception) {
        throw RuntimeException(e)
    }
}
