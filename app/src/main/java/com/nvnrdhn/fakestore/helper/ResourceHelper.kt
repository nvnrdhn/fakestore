package com.nvnrdhn.fakestore.helper

import android.content.Context
import javax.inject.Inject

class ResourceHelper @Inject constructor(
    private val context: Context
) {
    fun getStringResource(
        resId: Int,
        vararg formatArgs: Any
    ) = context.getString(resId, formatArgs)
}