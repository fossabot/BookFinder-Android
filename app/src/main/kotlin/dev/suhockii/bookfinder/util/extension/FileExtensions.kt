package dev.suhockii.bookfinder.util.extension

import java.io.File

fun File.unZip(zipFile: File, targetDirectory: File) = Utils.unZip(zipFile, targetDirectory)
