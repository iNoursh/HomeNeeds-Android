package com.inour.homeneeds.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.inour.homeneeds.R
import com.inour.homeneeds.adapters.GroupAdapter
import com.inour.homeneeds.classes.ItemList
import com.inour.homeneeds.databinding.ActivityGroupsListBinding

class GroupsList:AppCompatActivity(), GroupAdapter.OnItemClickListener {
    private val db = Firebase.firestore
    var recyclerView: RecyclerView? = null
    lateinit var groups: List<ItemList>
    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPref = this.getSharedPreferences(getString(R.string.shared_file), Context.MODE_PRIVATE)
        title = "Ento Min?"
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_groups_list)
        recyclerView = findViewById(R.id.rv_groups)
        getGroupNames()
    }

    override fun onBackPressed() {
    }


    private fun getGroupNames() {
        db.collection("groups-names")
            .get()
            .addOnSuccessListener { result ->
                groups = result.map { ItemList(it.data.getValue("label") as String,it.data.getValue("name") as String) }
                recyclerView?.adapter = GroupAdapter(groups, this)
                recyclerView?.setHasFixedSize(true)
            }
            .addOnFailureListener { exception ->
                Log.w("", "Error getting documents.", exception)
            }
        return
    }

    override fun onItemClick(position: Int) {
        //Toast.makeText(this, "Item $position ahlen", Toast.LENGTH_SHORT).show()
        val clickItem : ItemList = groups[position]
        recyclerView?.adapter?.notifyItemChanged(position)
        with (sharedPref.edit()) {
            putString("group", clickItem.name)
            apply()
        }
        val intent = Intent(this, UsersList::class.java)
        startActivity(intent)
    }
}