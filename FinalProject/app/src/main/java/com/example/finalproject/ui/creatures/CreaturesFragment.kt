package com.example.finalproject.ui.creatures

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.adapter.CreatureAdapter
import com.example.finalproject.data.Creature
import com.example.finalproject.databinding.FragmentCreaturesBinding
import com.example.finalproject.network.HommAPI
import com.example.finalproject.network.RetrofitObservable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import androidx.lifecycle.LiveData




class CreaturesFragment : Fragment() {

    private lateinit var creaturesViewModel: CreaturesViewModel
    private var _binding: FragmentCreaturesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        creaturesViewModel =
            ViewModelProvider(this).get(CreaturesViewModel::class.java)

        _binding = FragmentCreaturesBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data: LiveData<List<Creature>> = creaturesViewModel.getCreatures()!!
        data.observe(viewLifecycleOwner, {
            var list = view.findViewById<RecyclerView>(R.id.creatureRecyclerView)
            list.layoutManager = LinearLayoutManager(context)

            val itemAdapter = CreatureAdapter(creaturesViewModel.getCreatures()!!.value!!)
            list.adapter = itemAdapter
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
