package com.avery.newsplus.api.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * 新闻列表Item
 *
 * @author Avery
 */

data class NewsMetaItem(
    val aid: String?,
    val title: String?,
    @SerializedName("headpic")
    val headPic: String?,
    val writer: String?,
    val source: String?,
    @SerializedName("source_url")
    val sourceUrl: String?,
    @SerializedName("reply_count")
    val replyCount: Int = 0,
    @SerializedName("click_count")
    val clickCount: Int = 0,
    @SerializedName("pub_time")
    val publishTime: Long = 0

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readLong()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(aid)
        parcel.writeString(title)
        parcel.writeString(headPic)
        parcel.writeString(writer)
        parcel.writeString(source)
        parcel.writeString(sourceUrl)
        parcel.writeInt(replyCount)
        parcel.writeInt(clickCount)
        parcel.writeLong(publishTime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NewsMetaItem> {
        override fun createFromParcel(parcel: Parcel): NewsMetaItem {
            return NewsMetaItem(parcel)
        }

        override fun newArray(size: Int): Array<NewsMetaItem?> {
            return arrayOfNulls(size)
        }
    }
}
