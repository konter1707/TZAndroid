package com.example.tzandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.tzandroid.comon.BaseView
import kotlinx.android.synthetic.main.working_data_fragment.*
import moxy.MvpAppCompatFragment

class WorkingDateFragment : MvpAppCompatFragment(), BaseView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fName=arguments?.getString("fName")
        val  lName=arguments?.getString("lName")
        val avatarUrl=arguments?.getString("avatar")
        val nameSpecialty=arguments?.getString("nameSpecialty").toString()
        val birthday=arguments?.getString("birthday")
        val age=arguments?.getString("age")

        if (avatarUrl !="-")
            Glide.with(avatar!!.context).load(avatarUrl).centerCrop().placeholder(android.R.drawable.spinner_background).into(avatar as ImageView)
        else avatar?.setImageResource(R.drawable.user48)

        fname_lname.text=fName.plus(" ").plus(lName)
        birthday_age.text=age.plus("\n").plus(birthday)
        specialtty.text=nameSpecialty
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val v=inflater.inflate(R.layout.working_data_fragment,container,false)
        return v
    }

    override fun showToast(text: String) {
        Toast.makeText(context,text,Toast.LENGTH_LONG).show()
    }
}