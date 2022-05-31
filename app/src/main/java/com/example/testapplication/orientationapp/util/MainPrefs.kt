package com.example.orientation.orientationapp.util

import android.content.Context
import hu.autsoft.krate.SimpleKrate
import hu.autsoft.krate.booleanPref
import hu.autsoft.krate.default.withDefault

class MainPrefs(context: Context) : SimpleKrate(context) {
    var checkBio by booleanPref("false").withDefault<Boolean>(false)
    var isTouchId by booleanPref("false").withDefault<Boolean>(false)
    var isTouchIdCancelled by booleanPref("false").withDefault<Boolean>(false)
    var resultReturn by booleanPref("returnResult").withDefault<Boolean>(false)
    var enablePin by booleanPref("enablePin").withDefault<Boolean>(false)


}