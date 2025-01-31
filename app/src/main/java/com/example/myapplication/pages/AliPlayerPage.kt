package com.example.myapplication.pages

import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.aliyun.player.AliPlayerFactory

@Composable
fun AliPlayerPage() {
    val context = LocalContext.current
    val aliPlayer = AliPlayerFactory.createAliPlayer(context)
    AndroidView(
        factory = { context ->
            SurfaceView(context).apply {
                holder.addCallback(object : SurfaceHolder.Callback {
                    override fun surfaceCreated(holder: SurfaceHolder) {
                        aliPlayer.setSurface(holder.surface)
                    }
                    override fun surfaceChanged(
                        holder: SurfaceHolder,
                        format: Int,
                        width: Int,
                        height: Int
                    ) {
                        aliPlayer.surfaceChanged()
                    }

                    override fun surfaceDestroyed(holder: SurfaceHolder) {
                        aliPlayer.setSurface(null)
                    }
                })
            }
        },
        modifier = Modifier.fillMaxSize() // Adjust the size as needed
    )
}
