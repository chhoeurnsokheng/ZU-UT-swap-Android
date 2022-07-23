package com.gis.z1android.api.errorhandler

import java.io.IOException

class NoConnectivityException : IOException() {
    override val message: String?
        get() = "No connectivity exception"
}
