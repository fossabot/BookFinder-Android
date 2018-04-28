package suhockii.dev.bookfinder

import android.content.Context
import android.support.annotation.AttrRes
import android.util.TypedValue

fun Context.resolveAttrResource(@AttrRes attribute: Int) : Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(attribute, typedValue, true)
    return typedValue.resourceId
}