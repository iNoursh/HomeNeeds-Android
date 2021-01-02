package com.inour.homeneeds.fragments

import android.os.Bundle
import android.view.View
import android.widget.NumberPicker
import androidx.fragment.app.Fragment
import com.inour.homeneeds.R


class AddNewItem : Fragment(R.layout.activity_add_new_item){
    var numberPicker: NumberPicker? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        numberPicker = requireView().findViewById(R.id.editCount)
        numberPicker?.maxValue = 10
        numberPicker?.minValue = 0

    }
}