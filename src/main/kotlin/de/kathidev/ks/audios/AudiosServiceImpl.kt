package de.kathidev.ks.audios

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*


@Service
class AudiosServiceImpl(private val audiosRepo: AudiosRepo): AudiosService {
    override fun save(audio: Audio): Audio {
        audiosRepo.save(audio)
        return audio
    }

    override fun getAll(): List<Audio> {
        return audiosRepo.findAll().toList()
    }

    override fun getByIdOrNull(id: UUID): Audio? {
        return audiosRepo.findByIdOrNull(id)
    }
}