package cn.heckler.stukotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.heckler.stukotlin._20200902.study1
import cn.heckler.stukotlin._20200902.study2
import cn.heckler.stukotlin._20200902.study4
import cn.heckler.stukotlin._20200902.study5

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        study1()
        study2()
        study4()
        study5()
    }
}