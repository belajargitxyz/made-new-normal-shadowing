package alfianyabdullah.submission.games

import alfianyabdullah.submission.base.GameBaseFragment
import alfianyabdullah.submission.base.GamesAdapter
import alfianyabdullah.submission.base.GamesAdapterDecoration
import alfianyabdullah.submission.core.data.Resource
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_games.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.core.qualifier.named

import alfianyabdullah.submission.made.R as mainR

@ExperimentalCoroutinesApi
class GamesFragment : GameBaseFragment(R.layout.fragment_games) {

    private val gamesViewModel: GamesViewModel by viewModel()
    private val gamesAdapter: GamesAdapter by inject()

    override fun modules() = listOf(gamesModule)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gamesAdapter.setOnGameItemClickListener {
            val data = Bundle().apply {
                putParcelable("GAME", it)
            }

            view.findNavController()
                .navigate(mainR.id.action_games_fragment_to_detail_fragment, data)
        }

        rvGames.hasFixedSize()
        rvGames.layoutManager = LinearLayoutManager(requireContext())
        rvGames.addItemDecoration(GamesAdapterDecoration(20))
        rvGames.adapter = gamesAdapter

        gamesViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            loading.visibility = if (it) View.VISIBLE else View.INVISIBLE
        })

        gamesViewModel.games.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { data -> gamesAdapter.submitList(data) }
                }
                is Resource.Error -> {
                }
            }
        })
    }
}