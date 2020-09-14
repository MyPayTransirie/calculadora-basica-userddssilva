package br.com.maypay.calculadora

import android.os.Parcel
import android.os.Parcelable


class History (var expression_calc: String, var result_calc: String): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()?:"",
        parcel.readString()?:""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(expression_calc)
        parcel.writeString(result_calc)
    }

    private fun readFromParcel(parcel: Parcel){
        expression_calc = parcel.readString().toString()
        result_calc = parcel.readString().toString()
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<History> {
        override fun createFromParcel(parcel: Parcel): History {
            return History(parcel)
        }

        override fun newArray(size: Int): Array<History?> {
            return arrayOfNulls(size)
        }
    }

}