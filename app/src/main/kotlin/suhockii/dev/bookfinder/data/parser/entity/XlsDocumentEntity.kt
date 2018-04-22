package suhockii.dev.bookfinder.data.parser.entity

import suhockii.dev.bookfinder.data.database.entity.BookEntity
import suhockii.dev.bookfinder.domain.model.XlsDocument

data class XlsDocumentEntity(
    override val title: String,
    override val creationDate: String,
    override val columnNames: List<String>,
    override val data: Map<String, Collection<BookEntity>>
) : XlsDocument