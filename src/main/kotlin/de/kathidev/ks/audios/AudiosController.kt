package de.kathidev.ks.audios

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.server.ResponseStatusException
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.*

@RestController
@RequestMapping("/api/audios")
class AudiosController(private val audiosService: AudiosService) {
    // ...
    // POST: Hochladen einer Audiodatei auf dem Dateisystem
    @PostMapping("/uploads")
    fun uploadAudioFile(
        @RequestParam("file") file: MultipartFile,
        @RequestParam("title") title: String?,
        @RequestParam("description") description: String?
    ): ResponseEntity<Audio> {
        return try {
            val audio = Audio()

            if (title == null || description == null) ResponseEntity.status(HttpStatus.BAD_REQUEST) //????

            audio.title = title!!
            audio.description = description!!

            // Speichern der Datei im Dateisystem
            // Beispiel: Ordner "uploads" im aktuellen Verzeichnis

            val uploadDir = "./uploads/"
            val filePath: Path = Paths.get(uploadDir + file.originalFilename)
            Files.copy(file.inputStream, filePath, StandardCopyOption.REPLACE_EXISTING)

            audio.filename = filePath.toString()

            val savedFile: Audio = audiosService.save(audio)
            ResponseEntity.status(HttpStatus.CREATED).body(savedFile)
        } catch (e: IOException) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    // GET: Alle Audiodateien abrufen
    @GetMapping
    fun getAllAudios(): ResponseEntity<List<Audio>> {
        val allAudios = audiosService.getAll()
        return if (allAudios.isNotEmpty()) {
            ResponseEntity.ok(allAudios)
        } else {
            ResponseEntity.noContent().build()
        }
    }

    @GetMapping("/{audioId}")
    fun getAudioById(@PathVariable audioId: UUID): ResponseEntity<Audio> {
        val audio: Audio = audiosService.getByIdOrNull(audioId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        return ResponseEntity.ok().body(audio)
    }


}