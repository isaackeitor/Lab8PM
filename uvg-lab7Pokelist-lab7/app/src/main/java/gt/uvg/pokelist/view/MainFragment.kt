package gt.uvg.pokelist.view

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gt.uvg.pokelist.databinding.FragmentMainBinding
import gt.uvg.pokelist.model.PokemonResponse
import gt.uvg.pokelist.repository.PokemonRepository
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback


class MainFragment: Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val client = PokemonRepository.apiService.fetchPokemon("100")
        client.enqueue(object : Callback<PokemonResponse> {

            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>
            ){
                if (response.isSuccessful){
                    //Log.d("Pokemon",""+response.body())
                    val pokemonList = response.body()?.result
                    recyclerView = binding.recyclerView
                    recyclerView.layoutManager = LinearLayoutManager(context)
                    //recyclerView.layoutManager = GridLayoutManager(context, 2)
                    recyclerView.adapter = PokemonListAdapter(pokemonList!!)
                    Toast.makeText(requireContext(), "FETCHED: " +pokemonList.size, Toast.LENGTH_LONG).show()
                }

            }
            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "ERROR", Toast.LENGTH_LONG).show()
                //Log.e("failed",""+t.message)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}