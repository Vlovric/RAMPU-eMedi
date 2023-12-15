package hr.foi.rampu.emedi.entities

import android.os.Parcel
import android.os.Parcelable

data class ColorPalette(
    val color1: String,
    val color2: String,
    val color3: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(color1)
        parcel.writeString(color2)
        parcel.writeString(color3)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ColorPalette> {
        override fun createFromParcel(parcel: Parcel): ColorPalette {
            return ColorPalette(parcel)
        }

        override fun newArray(size: Int): Array<ColorPalette?> {
            return arrayOfNulls(size)
        }
    }
}
