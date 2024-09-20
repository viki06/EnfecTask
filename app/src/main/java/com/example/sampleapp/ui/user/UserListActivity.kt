package com.example.sampleapp.ui.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.sampleapp.R
import com.example.sampleapp.databinding.ActivityUserListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserListActivity : AppCompatActivity() {

    private var _binding: ActivityUserListBinding? = null

    private val mBinding get() = _binding!!

    private val mViewModel: UserListViewModel by viewModels()

    @Inject
    lateinit var userListAdapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        _binding = ActivityUserListBinding.inflate(layoutInflater)

        setContentView(mBinding.root)

        mBinding.recyclerView.adapter = userListAdapter

        mViewModel.getUserData()

        addDataObserver()

        addOnClickListener()

    }

    private fun addOnClickListener() {

        mBinding.noDataView.actionRetry.setOnClickListener {

            mViewModel.getUserData()

        }

    }

    private fun addDataObserver() {

        mViewModel.isLoading.observe(this) { showLoader ->

            mBinding.progressBarView.progressBar.visibility = if (showLoader) View.VISIBLE else View.GONE

        }

        mViewModel.userData.observe(this){ userList ->

            mBinding.noDataView.noData.visibility = View.GONE

            userListAdapter.setData(userList)

        }

        mViewModel.errorFeedback.observe(this) { msg ->

            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

            mBinding.noDataView.noData.visibility = View.VISIBLE

        }

    }
}