package com.inour.homeneeds.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import com.inour.homeneeds.R

class AddNewItemFragment : Fragment() {
    var numberPicker: NumberPicker? = null
    private var mListener: OnFragmentInteractionListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(requireContext().toString() + " must implement OnFragmentInteractionListener")
        }
    }
    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_new_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        numberPicker = view.findViewById(R.id.editCount)
        numberPicker?.maxValue = 10
        numberPicker?.minValue = 1
        val cancelBtn: Button = view.findViewById(R.id.btnCancel)
        cancelBtn.setOnClickListener {
            hideAddNewItem()
        }
    }

    private fun hideAddNewItem() {
        if(mListener != null) {
            mListener?.onFragmentInteractionListener("cancel")
        }
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteractionListener(type: String)
    }
    companion object {
        @JvmStatic
        fun newInstance() =
            AddNewItemFragment().apply {}
    }
}