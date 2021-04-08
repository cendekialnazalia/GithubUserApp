package com.cendekia.githubapp.repositories.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
    var name_events: String = "",
    var location_events: String = "",
    var photo_events: Int = 0,
    var headline_detail_events: String = "",
    var author_detail_events: String = "",
    var date_detail_event: String = "",
    var content_detail_event: String = ""
) : Parcelable