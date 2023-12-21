package de.kathidev.ks.audios

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface AudiosRepo: CrudRepository<Audio, UUID> {
}