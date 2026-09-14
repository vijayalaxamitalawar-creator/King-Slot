package com.kingslot.app.utils

import android.content.Context
import android.media.MediaPlayer

class SoundManager(private val context: Context) {

    private var backgroundMusicPlayer: MediaPlayer? = null
    private val soundEffects = mutableMapOf<String, MediaPlayer>()

    fun playBackgroundMusic() {
        if (backgroundMusicPlayer == null) {
            backgroundMusicPlayer = MediaPlayer.create(context, com.kingslot.app.R.raw.background_music)
            backgroundMusicPlayer?.isLooping = true
            backgroundMusicPlayer?.start()
        }
    }

    fun stopBackgroundMusic() {
        backgroundMusicPlayer?.stop()
        backgroundMusicPlayer?.release()
        backgroundMusicPlayer = null
    }

    fun playSoundEffect(soundName: String) {
        val soundId = when (soundName) {
            "spin" -> com.kingslot.app.R.raw.spin_sound
            "win" -> com.kingslot.app.R.raw.win_sound
            "megawin" -> com.kingslot.app.R.raw.megawin_sound
            "free_game" -> com.kingslot.app.R.raw.free_game_sound
            else -> return
        }

        val mediaPlayer = MediaPlayer.create(context, soundId)
        mediaPlayer.setOnCompletionListener {
            it.release()
            soundEffects.remove(soundName)
        }
        mediaPlayer.start()
        soundEffects[soundName] = mediaPlayer
    }

    fun release() {
        stopBackgroundMusic()
        for ((_, player) in soundEffects) {
            player.release()
        }
        soundEffects.clear()
    }
}
