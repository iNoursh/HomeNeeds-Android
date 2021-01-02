package com.inour.homeneeds

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.inour.homeneeds.fragments.AddNewItemFragment

class MainActivity : AppCompatActivity(), AddNewItemFragment.OnFragmentInteractionListener {
    var addNewItem: FrameLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addNewItem = findViewById(R.id.addNewItem)
    }

    override fun onFragmentInteractionListener(type: String) {
        if (type == "cancel") {
            hideAddNewItem()
        }
    }

    fun hideAddNewItem() {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment = supportFragmentManager.fragments[0]
        transaction.setCustomAnimations(R.anim.fragment_fade_enter, R.anim.fragment_fade_exit)
        transaction.remove(fragment).commit()
    }

    fun showAddNewItem(view: View) {
        val fragment: AddNewItemFragment = AddNewItemFragment.newInstance()
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.fragment_fade_enter, R.anim.fragment_fade_exit)
        transaction.add(R.id.addNewItem, fragment, "Add_New_Item").commit()
    }


}