package com.jubaya.studentapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jubaya.studentapp.R
import com.jubaya.studentapp.databinding.FragmentClothListBinding
import com.jubaya.studentapp.databinding.FragmentStudentListBinding
import com.jubaya.studentapp.viewmodel.ClothViewModel
import com.jubaya.studentapp.viewmodel.ListViewModel

class ClothListFragment : Fragment() {
    private lateinit var binding:FragmentClothListBinding
    private lateinit var viewModel: ClothViewModel
    private val clothListAdapter = ClothListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentClothListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ClothViewModel::class.java)
        viewModel.refresh()

        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = clothListAdapter

        observeViewModel()

        binding.refreshLayout.setOnRefreshListener {
            viewModel.refresh()
            binding.recView.visibility = View.GONE
            binding.txtError.visibility = View.GONE
            binding.progressLoad.visibility = View.VISIBLE
            binding.refreshLayout.isRefreshing = false
        }

    }

    fun observeViewModel(){
        viewModel.clothesLD.observe(viewLifecycleOwner, Observer {
            clothListAdapter.updateClothList(it)
        })

        viewModel.clothLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if (it == true){
                binding.txtError.visibility = View.VISIBLE
            }else{
                binding.txtError.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if (it == true){
                binding.progressLoad.visibility = View.VISIBLE
                binding.recView.visibility = View.GONE
            } else{
                binding.progressLoad.visibility = View.GONE
                binding.recView.visibility = View.VISIBLE
            }
        })
    }
}