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

        var list = root.findViewById<RecyclerView>(R.id.creatureRecyclerView)
        list.layoutManager = LinearLayoutManager(context)
        val itemAdapter = CreatureAdapter(creaturesViewModel.getCreatures())
        list.adapter = itemAdapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

/*

     val retrofit: Retrofit =  Retrofit.Builder()
         .baseUrl("http://restapi.alexvoyt.com:13342/")
         .addConverterFactory(GsonConverterFactory.create())
         .build()

     val hommAPI: HommAPI = retrofit.create(HommAPI::class.java)


     hommAPI.listCreatures().enqueue(object: Callback<List<Creature>> {
         override fun onFailure(call: Call<List<Creature>>, t: Throwable) {
             Toast.makeText(context, "REQUEST FAILED", Toast.LENGTH_SHORT)
         }

         override fun onResponse(call: Call<List<Creature>>, response: Response<List<Creature>>) {
             Toast.makeText(context, "REQUEST SUCCESS", Toast.LENGTH_SHORT)
             val creatureList = response.body()

             var list = root.findViewById<RecyclerView>(R.id.creatureRecyclerView)

             // Set the LayoutManager that this RecyclerView will use.
             list.layoutManager = LinearLayoutManager(context)
             // Adapter class is initialized and list is passed in the param.
             val itemAdapter = CreatureAdapter(creatureList!!)
             // adapter instance is set to the recyclerview to inflate the items.
             list.adapter = itemAdapter

             RetrofitObservable.getInstance()?.notifyObserverWithResponse(creatureList)
         }
     })

     val thread = Thread {
         try {
             val creatureList = hommAPI.listCreatures().execute().body()

             var list = root.findViewById<RecyclerView>(R.id.creatureRecyclerView)

             // Set the LayoutManager that this RecyclerView will use.
             list.layoutManager = LinearLayoutManager(context)
             // Adapter class is initialized and list is passed in the param.
             val itemAdapter = CreatureAdapter(creatureList!!)
             // adapter instance is set to the recyclerview to inflate the items.
             list.adapter = itemAdapter
         } catch (e: Exception) {
             e.printStackTrace()
         }
     }

     thread.start()
     thread.join()
     */