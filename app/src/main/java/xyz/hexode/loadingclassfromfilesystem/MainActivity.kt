package xyz.hexode.loadingclassfromfilesystem

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import xyz.hexode.Messager
import xyz.hexode.MessagerOriginal
import xyz.hexode.link
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    private val compatCodeCacheDir: File by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            codeCacheDir
        } else {
            getDir("odex", Context.MODE_PRIVATE)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun getMessageOriginal(@Suppress("UNUSED_PARAMETER") view: View) {
        textViewMessage.text = MessagerOriginal().getMessage()
    }

    fun getMessage1(@Suppress("UNUSED_PARAMETER") view: View) =
            getMessage(R.raw.dex1, "xyz.hexode.Messager1")

    fun getMessage2(@Suppress("UNUSED_PARAMETER") view: View) =
            getMessage(R.raw.dex2, "xyz.hexode.Messager2")

    private fun getMessage(dexResourceId: Int, className: String) {
        val dexFile = File(getDir("dex", Context.MODE_PRIVATE), "$className.dex")
        resources.openRawResource(dexResourceId).use { input ->
            FileOutputStream(dexFile).use {
                val bytesCopied = input.copyTo(it)
                Log.d(TAG, "Copied $bytesCopied bytes from resource dex file")
            }
        }
        try {
            val messager = link<Messager>(className,
                    dexFile,
                    compatCodeCacheDir,
                    classLoader)
            val message = messager.getMessage()
            Log.d(TAG, message)
            textViewMessage.text = message
        } catch (e: Exception) {
            Log.e(TAG, "", e)
            textViewMessage.text = getString(R.string.exception_raised)
        }
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}
