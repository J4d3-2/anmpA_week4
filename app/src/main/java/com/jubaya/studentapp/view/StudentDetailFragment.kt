package com.jubaya.studentapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jubaya.studentapp.R
import com.jubaya.studentapp.databinding.FragmentStudentDetailBinding
import com.jubaya.studentapp.model.Student
import com.jubaya.studentapp.viewmodel.DetailViewModel
import com.jubaya.studentapp.viewmodel.ListViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment(), ButtonUpdateClickListener {
    private lateinit var binding:FragmentStudentDetailBinding
    private lateinit var viewModel:DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStudentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var nrp = ""
        if(arguments != null){
            nrp = StudentDetailFragmentArgs.fromBundle(requireArguments()).nrp
        }

        binding.student = Student("", "", "", "", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRG3GC5yabmHrqQH1XDx9x5OTF6M9xCTioE5A&s")

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.refresh(nrp)

        binding.listener = this

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            /*var student = it

            binding.txtID.setText(it.id)
            binding.txtName.setText(it.name)
            binding.txtBOD.setText(it.dob)
            binding.txtPhone.setText(it.phone)

            binding.btnUpdate?.setOnClickListener {
                Observable.timer(5, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Log.d("Messages", "five seconds")
                        MainActivity.showNotification(student.name.toString(),
                            "A new notification created",
                            R.drawable.baseline_person_add_24)
                    }
            }

            val picasso = Picasso.Builder(requireContext())
            picasso.listener { picasso, uri, exception ->
                exception.printStackTrace()
            }
            picasso.build().load(it.photoUrl).into(binding.imageView3, object:
                Callback {
                override fun onSuccess() {
                    //binding.progressImage.visibility = View.INVISIBLE
                    binding.imageView3.visibility = View.VISIBLE
                }

                override fun onError(e: Exception?) {
                    Log.e("picasso_error", e.toString())
                }
            }
            )*/
            binding.student = it
        })
        }

    override fun onButtonUpdateClick(v: View) {
        Toast.makeText(context, "Updated?", Toast.LENGTH_SHORT).show()
    }
}