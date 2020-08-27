package com.example.tzandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.replace
import com.example.tzandroid.mvp.MainPresentor
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class MainActivity : MvpAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        val frans = supportFragmentManager.beginTransaction()
        frans.addToBackStack(null)
        val fragment = SpecialtyListFragment(this)
        frans.replace(R.id.secondFragment, fragment).commit()
    }
}