package com.example.tzandroid

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tzandroid.adapter.SpecialtyAdapter
import com.example.tzandroid.mvp.MainPresentor
import com.example.tzandroid.mvp.MainView
import com.example.tzandroid.room.specialty.NameSpecialty
import kotlinx.android.synthetic.main.fragment_specialty_list.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class SpecialtyListFragment(c:Context) : MvpAppCompatFragment(), MainView,
SpecialtyAdapter.OnShowListSpecialtyListener {
    @InjectPresenter
    lateinit var mainPresentor: MainPresentor
    val contextt = c
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainPresentor.readSpecialty(contextt)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_specialty_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    override fun setListWorking(nameSpecialty: List<NameSpecialty>) {
        rv.apply {
            layoutManager = LinearLayoutManager(context).apply {
                addItemDecoration(DividerItemDecoration(rv.context, orientation))
                adapter = SpecialtyAdapter(
                    this@SpecialtyListFragment,
                    nameSpecialty.toSet().toList()
                )
            }
        }
    }

    override fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }
    override fun onShow(specialty: NameSpecialty) {
        val mainActivity = activity as MainActivity
        val frans = mainActivity.supportFragmentManager.beginTransaction()
        frans.addToBackStack(null)
        val fragment = ListWorkingFragment(contextt)
        val bundle = Bundle()
        bundle.putString("nameSpecialty", specialty.nameSpecialty)
        bundle.putLong("idSpecialty",specialty.specialtyId)
        fragment.arguments = bundle
        frans.replace(R.id.secondFragment, fragment).commit()
    }

}