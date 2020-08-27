package com.example.tzandroid

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tzandroid.adapter.WorkesAdapter
import com.example.tzandroid.comon.EditingData
import com.example.tzandroid.mvp.WorkingPresenter
import com.example.tzandroid.mvp.WorkingView
import com.example.tzandroid.room.workes.WorkesModel
import kotlinx.android.synthetic.main.fragment_list_working.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class ListWorkingFragment(c: Context) : MvpAppCompatFragment(), WorkingView,
    WorkesAdapter.OnListWorkesListener {
    @InjectPresenter
    lateinit var workingPresenter: WorkingPresenter
    lateinit var nameSpecialty:String
    val contextt = c
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nameSpecialty = arguments?.getString("nameSpecialty").toString()
        val idSpecialty: Long = arguments?.getLong("idSpecialty")!!.toLong()
        workingPresenter.readWorking(contextt, idSpecialty)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_working, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun getListWorking(workes: List<WorkesModel>) {
        recicler.apply {
            layoutManager = LinearLayoutManager(context).apply {
                addItemDecoration(DividerItemDecoration(context, orientation))
                adapter = WorkesAdapter(this@ListWorkingFragment,workes)
            }
        }
    }

    override fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }
    override fun onWorkes(workesModel: WorkesModel) {
        val mainActivity = activity as MainActivity
        val frans = mainActivity.supportFragmentManager.beginTransaction()
        frans.addToBackStack(null)
        val fragment = WorkingDateFragment()
        val bundle = Bundle()
        bundle.putString("avatar",workesModel.avatr_url)
        bundle.putString("fName",workesModel.f_name)
        bundle.putString("lName",workesModel.l_name)
        bundle.putString("nameSpecialty", nameSpecialty)
        bundle.putString("birthday",workesModel.birthday)
        bundle.putString("age",EditingData().getYear(workesModel.birthday))
        fragment.arguments = bundle
        frans.replace(R.id.secondFragment, fragment).commit()
    }
}