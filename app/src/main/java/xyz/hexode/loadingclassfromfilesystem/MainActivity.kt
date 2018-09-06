package xyz.hexode.loadingclassfromfilesystem

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import xyz.hexode.Messager
import xyz.hexode.link
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun getMessage(@Suppress("UNUSED_PARAMETER") view: View) {
        val dexFile = File(getDir("dex", Context.MODE_PRIVATE), "messager_dex")
        resources.openRawResource(R.raw.dex1).use { input ->
            FileOutputStream(dexFile).use {
                val copiedBytes = input.copyTo(it)
                Log.d(TAG, "Copied $copiedBytes bytes from resource dex file")
            }
        }
        try {
            val messager = link<Messager>("xyz.hexode.Messager1",
                    dexFile,
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) codeCacheDir else cacheDir,
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
