package com.example.practiceproject

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    lateinit var addPhoneBookButton : Button
    lateinit var recyclerView : RecyclerView
    var arrayList = ArrayList<PhoneBookEntities>()
    val adapter = PhoneBookAdapter(arrayList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerview)
        addPhoneBookButton = findViewById(R.id.button)
        recyclerView.adapter =adapter
        recyclerView.layoutManager = GridLayoutManager(this, 1)

        selectPhoneNumber()

        addPhoneBookButton.setOnClickListener {
            dialogShow()
        }


    }

    fun dialogShow(){
        val dialog = AlertDialog.Builder(this)
        dialog.setIcon(R.drawable.ic_launcher_foreground)
        dialog.setTitle("전화번호 추가")

        val numberEditText : EditText = EditText(this)
        numberEditText.setHint("전화번호를 입력해주세요.")
        dialog.setView(numberEditText)



        // Dialog 에 확인, 취소 Button 추가
        dialog.setPositiveButton("확인") { dialog, _ ->
            var appDatabase: AppDatabase = AppDatabase.getInstance(this)
            var phoneNumber:String = numberEditText.text.toString()
            var phoneBookEntities = PhoneBookEntities(phoneNumber)
            GlobalScope.launch {
                withContext(Dispatchers.IO){

                    appDatabase.getPhoneBookDAO().insertPhoneBook(phoneBookEntities)
                }
            }
            arrayList.add(phoneBookEntities)
            adapter.notifyDataSetChanged()
            dialog.dismiss()
        }
        dialog.setNegativeButton("취소"){ dialog,_ ->
            dialog.dismiss()
        }
        dialog.show()
    }

    fun selectPhoneNumber(){
        var appDatabase: AppDatabase = AppDatabase.getInstance(this)
        GlobalScope.launch {
            withContext(Dispatchers.IO){
                for(i in appDatabase.getPhoneBookDAO().getPhoneBook()){
                    arrayList.add(i)
                }
            }
        }
    }
}