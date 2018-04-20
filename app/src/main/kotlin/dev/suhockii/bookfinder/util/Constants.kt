package dev.suhockii.bookfinder.util

object RequestUrl {
    const val USER_CONTEXT: String = "/uc"
}

object HttpHeader {
    const val ID: String = "id"
    const val EXPORT: String = "export"
    const val CONTENT_DISPOSITION: String = "Content-Disposition"
}

object ConstantValue {
    const val DOWNLOAD: String = "download"
}

object Regex {
    const val FILE_NAME: String = "(?<=filename=\")(.*\\n?)(?=\")"
}
