package dev.suhockii.bookfinder.data.xls.entity

import dev.suhockii.bookfinder.data.local.entity.BookEntity
import dev.suhockii.bookfinder.domain.model.XlsDocument

data class XlsDocumentEntity(
    override val title: String,
    override val creationDate: String,
    override val columnNames: List<String>,
    override val data: Map<String, Collection<BookEntity>>
) : XlsDocument