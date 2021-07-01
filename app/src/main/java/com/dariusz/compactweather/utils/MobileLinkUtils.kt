package com.dariusz.compactweather.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

object MobileLinkUtils {

    fun openLinkInBrowser(context: Context, link: String){
        val defaultBrowser =
            Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER)
        defaultBrowser.data = Uri.parse(link)
        return context.startActivity(defaultBrowser)
    }

}