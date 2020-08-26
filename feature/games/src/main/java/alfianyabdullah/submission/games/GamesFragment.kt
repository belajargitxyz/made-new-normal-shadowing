package alfianyabdullah.submission.games

import alfianyabdullah.submission.base.KoinFragment
import alfianyabdullah.submission.core.data.Resource
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_games.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.ext.android.viewModel

import alfianyabdullah.submission.made.R as mainR

@ExperimentalCoroutinesApi
class GamesFragment : KoinFragment() {

    private val gamesViewModel: GamesViewModel by viewModel()

    override fun modules() = listOf(gamesModule)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_games, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gamesViewModel.games.observe(viewLifecycleOwner, Observer {
            Handler(Looper.getMainLooper()).postDelayed({
                view.findNavController().navigate(mainR.id.action_games_fragment_to_detail_fragment)
            }, 5000)
            when (it) {
                is Resource.Success -> {
                    maintext.text = it.data?.size.toString()
                }
                is Resource.Error -> {
                    maintext.text = it.message
                }
            }
        })
    }
}