package com.example.android.camera2video

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import com.arthenica.mobileffmpeg.FFmpeg
import kotlinx.android.synthetic.main.activity_preview.*
import java.io.File

class PreviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)

        video_view.setMediaController(MediaController(this))
        video_view.setVideoPath(getCompressedFilePath(this))

        val info = FFmpeg.getMediaInformation(getCompressedFilePath(this))
        val file = File(getCompressedFilePath(this))

        file_size.text = "${file.length() / 1024} kB"
        bitrate.text = "${info.bitrate / 8} kBs"
        length.text = "${info.duration / 1000} sec"
    }

    private fun getCompressedFilePath(context: Context?): String {
        val filename = "compressed.mp4"
        val dir = context?.getExternalFilesDir(null)

        return if (dir == null) {
            filename
        } else {
            "${dir.absolutePath}/$filename"
        }
    }
}
