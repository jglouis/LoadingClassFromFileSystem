package xyz.hexode

import dalvik.system.DexClassLoader
import java.io.File

@Throws(RuntimeException::class)
fun linkMessager(className: String, dex: File, codeCacheDir: File, parent: ClassLoader) : Messager {
    try {
        val classLoader = DexClassLoader(dex.absolutePath,
                codeCacheDir.absolutePath, null, parent)
        val clazz = classLoader.loadClass(className)
        return clazz.newInstance() as Messager
    } catch (e: Exception) {
        throw RuntimeException(e)
    }
}
