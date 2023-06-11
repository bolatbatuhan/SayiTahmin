package com.batuhanb.sayitahmin.model

data class TahminModel(var id:Int,var label:String): BaseObject() {

    override fun toString(): String {
        return label;
    }
}