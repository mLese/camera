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

        original_video_view.setMediaController(MediaController(this))
        original_video_view.setVideoPath(getOriginalFilePath(this))

        val original_info = FFmpeg.getMediaInformation(getOriginalFilePath(this))
        val original_file = File(getOriginalFilePath(this))

        original_file_size.text = "${original_file.length() / 1024} kB"
        original_bitrate.text = "${original_info.bitrate / 8} kBs"
        original_length.text = "${original_info.duration / 1000} sec"

        compressed_video_view.setMediaController(MediaController(this))
        compressed_video_view.setVideoPath(getCompressedFilePath(this))

        val compressed_info = FFmpeg.getMediaInformation(getCompressedFilePath(this))
        val compressed_file = File(getCompressedFilePath(this))

        compressed_file_size.text = "${compressed_file.length() / 1024} kB"
        compressed_bitrate.text = "${compressed_info.bitrate / 8} kBs"
        compressed_length.text = "${compressed_info.duration / 1000} sec"
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

    private fun getOriginalFilePath(context: Context?): String {
        val filename = "original.mp4"
        val dir = context?.getExternalFilesDir(null)

        return if (dir == null) {
            filename
        } else {
            "${dir.absolutePath}/$filename"
        }
    }
}
