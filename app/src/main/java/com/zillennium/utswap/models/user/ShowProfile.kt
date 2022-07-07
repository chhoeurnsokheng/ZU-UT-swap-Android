package com.zillennium.utswap.models.user

import android.media.Image

/**
 * @author chhoeurnsokheng
 * Created 7/7/22 at 4:54 PM
 * By Mac
 */

class ShowProfile {
    var id: Int? = 0
    var first_name: String? = null
    var last_name: String? = null
    var email: String? = null
    var phone: String? = null
    var phone_2: String? = null
    var phone_3: String? = null
    var phone_4: String? = null
    var street_no: String? = null
    var house_no: String? = null
    var address: String? = null
    var gender: String? = null
    var date_of_birth: String? = null
    var is_verified: Boolean? = false
    var profile: Image? =  null
}