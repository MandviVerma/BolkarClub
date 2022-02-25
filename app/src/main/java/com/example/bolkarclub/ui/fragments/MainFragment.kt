package com.example.bolkarclub.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bolkarclub.ui.decoration.GridSpacingItemDecoration
import com.example.bolkarclub.R
import com.example.bolkarclub.model.CategoryType
import com.example.bolkarclub.model.DataModel
import com.example.bolkarclub.response.MainResponse
import com.example.bolkarclub.ui.actitivies.MainActivity
import com.example.bolkarclub.ui.adapter.MainAdapter
import com.example.bolkarclub.ui.adapter.MemberAdapter
import com.example.bolkarclub.viewmodel.MainViewModel


class MainFragment : Fragment(), MemberAdapter.OnItemClickListener,
    MainAdapter.OnItemClickListener {
    lateinit var progress: ProgressBar
    lateinit var dataHostModel: DataModel
    var mainResponse: MainResponse? = null
    var newDataList = ArrayList<DataModel>()
    var newMemberList = ArrayList<DataModel>()
    lateinit var exit: ConstraintLayout


    lateinit var mainAdapter: MainAdapter
    lateinit var memberAdapter: MemberAdapter
    lateinit var mainFragmentViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainFragmentViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        progress = requireActivity().findViewById<ProgressBar>(R.id.progress)
        exit = requireActivity().findViewById(R.id.clExit)
        exit.setOnClickListener {
            activity?.onBackPressed()
        }

        observeLiveData()

        mainFragmentViewModel.getData()

    }


    private fun setUpRecyclerView() {
        mainAdapter = MainAdapter(context, newDataList, this)
        val rvMain = requireActivity().findViewById<RecyclerView>(R.id.rvMain)

        rvMain.apply {
            adapter = mainAdapter
            val spanCount = 3 // 3 columns

            val spacing = 50 // 50px

            val includeEdge = true
            rvMain.addItemDecoration(
                GridSpacingItemDecoration(
                    spanCount,
                    spacing,
                    includeEdge
                )
            )
            layoutManager = GridLayoutManager(context, spanCount)
        }
    }

    private fun setUpMemberRecyclerView() {
        memberAdapter = MemberAdapter(context, newMemberList, this)
        val rvMember = requireActivity().findViewById<RecyclerView>(R.id.rvMember)
        rvMember.apply {
            adapter = memberAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }
    }

    private fun observeLiveData() {
        mainFragmentViewModel.mainResponse.observe(viewLifecycleOwner, { mainResponse ->

            mainResponse?.let {
                dataHostModel = DataModel(
                    it.data.host.n,
                    CategoryType.Host,
                    "https://cdn1.bolkarapp.com/uploads/dp/" + it.data.host.u + ".jpg",
                    false, true
                )
                newDataList.add(dataHostModel!!)
                for (i in it.data.moderators.indices) {
                    val dataModeratorModel = DataModel(
                        it.data.moderators[i].n,
                        CategoryType.Moderator,
                        "https://cdn1.bolkarapp.com/uploads/dp/" + it.data.moderators[i].u + ".jpg",
                        false, false
                    )
                    newDataList.add(dataModeratorModel)
                }
                for (i in it.data.speakers.indices) {
                    val dataSpeakerModel = DataModel(
                        it.data.speakers[i].n,
                        CategoryType.Speaker,
                        "https://cdn1.bolkarapp.com/uploads/dp/" + it.data.speakers[i].u + ".jpg",
                        false, false
                    )
                    newDataList.add(dataSpeakerModel)
                }
                for (i in it.data.members.indices) {
                    val dataMemberModel = DataModel(
                        it.data.members[i].n,
                        CategoryType.Member,
                        "https://cdn1.bolkarapp.com/uploads/dp/" + it.data.members[i].u + ".jpg",
                        false, false
                    )
                    newMemberList.add(dataMemberModel)
                }

            }

            setUpRecyclerView()
            mainAdapter.notifyDataSetChanged()

            (activity as MainActivity?)?.fragmentMethod(newDataList.size, dataHostModel)

            setUpMemberRecyclerView()
            memberAdapter.notifyDataSetChanged()
            progress.visibility = View.GONE


        })

    }


    override fun onItemClick(dataModel: ArrayList<DataModel>, position: Int, view: View) {


    }


}


