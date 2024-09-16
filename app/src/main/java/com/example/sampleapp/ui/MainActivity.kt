package com.example.sampleapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.sampleapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null

    private val mBinding get() = _binding!!

    private val mViewModel: MainActivityViewModel by viewModels()

    @Inject
    lateinit var mAdapter: ImageRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mBinding.root)

        mBinding.recyclerView.adapter = mAdapter

        mViewModel.getImageData()

        addDataObserver()

        addOnClickListener()

    }

    private fun addOnClickListener() {

        mBinding.actionRetry.setOnClickListener {

            mViewModel.getImageData()

        }

    }

    private fun addDataObserver() {

        mViewModel.isLoading.observe(this) { loadingState ->

            mBinding.progressBar.visibility = if (loadingState) View.VISIBLE else View.GONE
        }

        mViewModel.imageList.observe(this) { imageList ->

            mBinding.noDataView.visibility = View.GONE

            mAdapter.setData(imageList)

        }

        mViewModel.errorFeedback.observe(this) { msg ->

            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

            mBinding.noDataView.visibility = View.VISIBLE

        }

    }


}