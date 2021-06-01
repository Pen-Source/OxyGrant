package com.pensource.oxygrant.util

import java.util.*
import javax.inject.Inject

interface TimeUtil {
    fun getCurrentTime(): Long
}

class DefaultTimeUtil @Inject constructor() : TimeUtil {

    override fun getCurrentTime(): Long {
        return Date().time
    }
}