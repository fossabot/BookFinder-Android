package suhockii.dev.bookfinder.data.parser

import suhockii.dev.bookfinder.data.database.entity.BookEntity
import suhockii.dev.bookfinder.data.parser.entity.XlsDocumentEntity
import java.io.BufferedReader
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*
import java.util.regex.Pattern


class XlsParser {

    fun parseXlsDocument(xlsFile: File): XlsDocumentEntity {
        val contentString = getStringFromFile(xlsFile)
        val allMatches = ArrayList<String>()
        val matcher = Pattern
            .compile(REGEX_XLS_DATA)
            .matcher(contentString)
        while (matcher.find()) {
            allMatches.add(matcher.group())
        }
        allMatches.forEachIndexed { index, s ->
            allMatches[index] = s.removeSurrounding(
                REGEX_XLS_CDATA_START,
                REGEX_XLS_CDATA_END
            )
        }

        val booksData = mutableMapOf<String, MutableList<BookEntity>>()
        var categoryNamePosition = -1
        val objectFieldsQueue = ArrayDeque<String>()
        var currentCategory: String? = null

        for (index in POSITION_COLUMN_NAMES_END + 1 until allMatches.size) {
            if (index == categoryNamePosition) continue

            if (allMatches[index] == DATA_DELIMETER) {
                objectFieldsQueue.clear()
                categoryNamePosition = index + 1
                currentCategory = allMatches[categoryNamePosition]
                booksData[currentCategory] = mutableListOf()
                continue
            }

            objectFieldsQueue.add(allMatches[index])
            if (objectFieldsQueue.size == OBJECT_FIELD_COUNT) {
                booksData[currentCategory]!!.add(
                    BookEntity(
                        currentCategory!!,
                        objectFieldsQueue.pop(),
                        objectFieldsQueue.pop(),
                        objectFieldsQueue.pop(),
                        objectFieldsQueue.pop(),
                        objectFieldsQueue.pop().toDouble(),
                        objectFieldsQueue.pop(),
                        objectFieldsQueue.pop(),
                        objectFieldsQueue.pop(),
                        objectFieldsQueue.pop(),
                        objectFieldsQueue.pop()
                    )
                )
            }
        }

        booksData.size
        return XlsDocumentEntity(
            title = allMatches[POSITION_TITLE],
            creationDate = allMatches[POSITION_CREATION_DATE],
            columnNames = allMatches.subList(
                POSITION_COLUMN_NAMES_START,
                POSITION_COLUMN_NAMES_END
            ),
            data = booksData
        )
    }

    private fun getStringFromFile(file: File): String {
        val fin = file.inputStream()
        val ret = convertStreamToString(fin)
        fin.close()
        return ret
    }

    private fun convertStreamToString(inputStream: InputStream): String {
        val reader = BufferedReader(InputStreamReader(inputStream,
            ENCODING
        ))
        val stringBuilder = StringBuilder()
        while (reader.readLine()?.let { stringBuilder.append(it).append("\n") } != null) {
        }
        reader.close()
        return stringBuilder.toString()
    }

    companion object {
        private const val REGEX_XLS_DATA =
            "(?s)(?<=<Data ss:Type=\"String\">|<Data ss:Type=\"Number\">).*?(?=<\\/Data>)|section_title_1"
        private const val REGEX_XLS_CDATA_START = "<![CDATA["
        private const val REGEX_XLS_CDATA_END = "]]>"
        private const val ENCODING = "windows-1251"
        private const val POSITION_TITLE = 1
        private const val POSITION_CREATION_DATE = 2
        private const val POSITION_COLUMN_NAMES_START = 3
        private const val POSITION_COLUMN_NAMES_END = 12
        private const val DATA_DELIMETER = "section_title_1"
        private const val OBJECT_FIELD_COUNT = 10
    }
}