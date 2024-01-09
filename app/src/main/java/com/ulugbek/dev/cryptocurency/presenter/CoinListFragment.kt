package com.ulugbek.dev.cryptocurency.presenter

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ulugbek.dev.cryptocurency.R
import com.ulugbek.dev.cryptocurency.common.UiEvent
import com.ulugbek.dev.cryptocurency.databinding.FragmentCoinListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CoinListFragment : Fragment() {

    private  var _binding:FragmentCoinListBinding?=null
    private  val binding get() = _binding!!
    private val viewModel:CoinListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCoinList()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentCoinListBinding.inflate(layoutInflater,container,false)
        return _binding?.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        lifecycleScope.launch {
            viewModel.coinList.collectLatest {
                when(it){
                    UiEvent.Empty -> Unit
                    is UiEvent.Error -> {

                        binding.progressBar.isVisible=false
                        binding.errorTv.isVisible=true
                        binding.errorTv.text=it.message
                        binding.recyclerView.isVisible=false
                    }
                    UiEvent.Loading -> {
                        binding.progressBar.isVisible=false
                        binding.errorTv.isVisible=false
                        binding.recyclerView.isVisible=true

                    }
                    is UiEvent.Success<*> -> {
                        Log.d("TAG", "${it.data} ")

                    }
                }
            }
        }


    }


}