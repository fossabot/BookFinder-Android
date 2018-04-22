package suhockii.dev.bookfinder.domain.model

interface XlsDocument {
    val title: String
    val creationDate: String
    val columnNames: List<String>
    val data: Map<String, Collection<Book>>
}