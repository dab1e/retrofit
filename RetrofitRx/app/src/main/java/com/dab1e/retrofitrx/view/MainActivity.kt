package com.dab1e.retrofitrx.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dab1e.retrofitrx.viewmodel.MainViewModel
import com.dab1e.retrofitrx.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getApiZip()
  //      getApiRx()
   //     getApiCoroutine()



    }

    private fun getApiCoroutine() {
        viewModel.getUserCoro().observe(this, Observer { respone->
            for(i in 0..respone.size-1){
                Log.d("COROUTINES TV", respone[i].user)
            }
        })

        viewModel.getDogCoro().observe(this, Observer { respone->
            Log.d("COROUTINES TV", respone.url)
        })

        viewModel.getTVCoro().observe(this, Observer { respone->
            for(i in 0..respone.size-1) Log.d("COROUTINES TV", respone[i].tv)
        })
    }

    private fun getApiZip() {
        viewModel.zip().observe(this, Observer { respone->
            textview.setText("respone")
            textview.append("\n"+respone.dog.fileSize)
            textview.append("\n"+respone.dog.url+"\n")
            for(i in 0..respone.user.size-1) {
                textview.append("\n"+respone.user[i].user)
                textview.append(", "+respone.user[i].age.toString())
                textview.append(", "+respone.user[i].firstname)
                textview.append(", "+respone.user[i].lastname+"\n")
            }
            for(i in 0..respone.tv.size-1) {
                textview.append("\n"+respone.tv[i].tv)
                textview.append(", "+respone.tv[i].kho.toString())
                textview.append(", "+respone.tv[i].soluong+"\n")

            }
        })
    }


    private fun getApiRx() {

        viewModel.getRxTV().observe(this, Observer { respone->
            for(i in 0..respone.size-1){
                Log.d("RX TV", respone!![i].tv)
            }
        })

        viewModel.getRxDog().observe(this, Observer { res->
            Log.d("RX DOG","DOGView")
        })

        viewModel.getRxUser().observe(this, Observer { res->
            for (i in 0..res.size!! -1 ){
                Log.d("RX USER", res!![i].user)
            }
        })

    }
}