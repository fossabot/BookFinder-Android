package suhockii.dev.bookfinder

import android.app.ActivityManager
import android.content.Context
import android.net.Uri
import android.support.annotation.AttrRes
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.res.ResourcesCompat
import android.util.TypedValue
import android.view.View
import android.view.ViewManager
import org.jetbrains.anko.custom.ankoView


fun Context.attrResource(@AttrRes attribute: Int): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(attribute, typedValue, true)
    return typedValue.resourceId
}

fun Context.isAppOnForeground(): Boolean {
    val activityManager = this.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager?
    val appProcesses = activityManager?.runningAppProcesses ?: return false
    val packageName = this.packageName
    for (appProcess in appProcesses) {
        if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND &&
            appProcess.processName == packageName) {
            return true
        }
    }
    return false
}

fun Context.openLink(link: String) {
    CustomTabsIntent.Builder()
        .setToolbarColor(ResourcesCompat.getColor(resources, R.color.colorPrimary, theme))
        .build()
        .launchUrl(this, Uri.parse(link))
}

inline fun ViewManager.collapsingToolbarLayout2(init: suhockii.dev.bookfinder.CollapsingToolbarLayout2.() -> Unit): net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout {
    return ankoView({ ctx: Context -> suhockii.dev.bookfinder.CollapsingToolbarLayout2(ctx) }, theme = 0) { init.invoke(this) }
}

open class CollapsingToolbarLayout2(ctx: Context): net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout(ctx) {
    inline fun <T: View> T.lparams(
        width: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
        height: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
        init: net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout.LayoutParams.() -> Unit
    ): T {
        val layoutParams = net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout.LayoutParams(width, height)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }
}

