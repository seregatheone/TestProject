package com.example.testapplication.ui.jokes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapplication.databinding.FragmentJokesBinding
import com.example.testapplication.utils.Status
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class JokesFragment : DaggerFragment() {

    private var _binding: FragmentJokesBinding? = null
    private val binding get() = _binding!!

    private var _adapter: RecViewAdapter? = null
    private val adapter get() = _adapter!!

    @Inject
    lateinit var jokesViewModelFactory : JokesViewModelFactory

    //Вариант для быстрого написания. Можно делать через отдельный scope на viewModel,
    // но для такой работы запарно

    private val viewModel : JokesViewModel by viewModels{jokesViewModelFactory}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJokesBinding.inflate(inflater, container, false)
        _adapter = RecViewAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recView = binding.recView
        val layoutManager = LinearLayoutManager(requireContext())
        recView.layoutManager = layoutManager
        recView.adapter = adapter


        viewModel.actualData.observe(viewLifecycleOwner){
            Log.i(" viewModel.actualData"," viewModel.actualData")
            val subList = it.subList(0,viewModel.currentNumber.value!!)
            adapter.setRequestsData(subList)
        }

        viewModel.getResponse().observe(viewLifecycleOwner) {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recView.visibility = View.VISIBLE
                        viewModel.actualData.value = it.data!!.body()
                    }
                    Status.ERROR -> {
                        recView.visibility = View.VISIBLE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        recView.visibility = View.GONE
                        adapter.setRequestsData(emptyList())
                    }
                }
            }
        }
        viewModel.currentNumber.observe(viewLifecycleOwner) {
            Log.i("viewModel.currentNumber","viewModel.currentNumber")
            adapter.setRequestsData(viewModel.actualData.value!!.subList(0,it))
        }

        binding.reload.setOnClickListener {
            val text = binding.editTextTextPersonName.text.toString()
            try {
                when(val textInt = text.toInt()){
                    in 0..100 -> viewModel.setCurrentNumber(textInt)
                    else-> Toast.makeText(requireContext(),"Напишите число от 0 до 100",Toast.LENGTH_LONG).show()
                }

            }catch (e : Exception){
                Toast.makeText(requireContext(),e.stackTrace.toString(),Toast.LENGTH_LONG).show()
            }

        }

        viewModel.getResponse()

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _adapter = null
    }
}