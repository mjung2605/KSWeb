package de.kathidev.ks.audios

import java.util.*


interface AudiosService {
    fun save(audio: Audio): Audio

    fun getAll(): List<Audio>

    fun getByIdOrNull(id: UUID): Audio?
}