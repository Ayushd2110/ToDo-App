package com.ayush.todo

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
class MainActivity : AppCompatActivity() {

private var fbAdd: FloatingActionButton? = null
private var Ltlist: ListView? = null
var arrayList: ArrayList<String>? = null
var adapter: ArrayAdapter<String>? = null


override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    bindID()
    //Adding new todo item
    fbAdd!!.setOnClickListener { addItemDialog() }
}

fun bindID() {
    fbAdd = findViewById<View>(R.id.fbAdd) as FloatingActionButton?
    Ltlist = findViewById<View>(R.id.Ltlist) as ListView?
    arrayList = ArrayList()
    adapter = ArrayAdapter(
        this, R.layout.raw_data_list, R.id.tvListView,
        arrayList!!
    )
    Ltlist!!.adapter = adapter
    Ltlist!!.onItemClickListener =
        OnItemClickListener { parent, view, position, id ->
            val v = Ltlist!!.getChildAt(position)
            val ctv = v.findViewById<View>(R.id.tvListView) as TextView
            val imgDelete = v.findViewById<View>(R.id.imgDelete) as ImageView
            imgDelete.setOnClickListener { //Delete selected item
                val adb = AlertDialog.Builder(this@MainActivity)
                adb.setTitle("Delete?")
                adb.setMessage("Are you sure you want to delete ? ")
                adb.setNegativeButton("Cancel", null)
                adb.setPositiveButton(
                    "Ok"
                ) { dialog, which ->
                    val count = adapter!!.count
                    for (i in arrayList!!.indices) if (i == position) {
                        adapter!!.remove(adapter!!.getItem(position))
                        adapter!!.notifyDataSetChanged()
                        Toast.makeText(
                            this@MainActivity,
                            "Item is deleted successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                adb.show()
            }
        }
}


