package alfianyabdullah.submission.games

import alfianyabdullah.submission.base.*
import alfianyabdullah.submission.core.data.Resource
import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.content_games.*
import kotlinx.android.synthetic.main.fragment_games.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import alfianyabdullah.submission.made.R as mainR

@ExperimentalCoroutinesApi
class GamesFragment : GameBaseFragment(R.layout.fragment_games) {

    private val gamesViewModel: GamesViewModel by viewModel()
    private val gamesAdapter: GamesAdapter by inject(named(GAME_QUALIFIER))

    override fun modules() = listOf(gamesModule)
    override fun views() = mapOf(
        KEY_INFO to listOf(
            tvInfo, ivInfo, tvOtherInfo, btnReloadGames
        )
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateViewsVisibility(View.INVISIBLE, "INFO")

        gamesAdapter.setOnGameItemClickListener {
            val data = Bundle().apply {
                putParcelable(GAME_BUNDLE_KEY, it)
            }

            view.findNavController()
                .navigate(mainR.id.action_games_fragment_to_detail_fragment, data)
        }

        rvGames.hasFixedSize()
        rvGames.layoutManager = LinearLayoutManager(requireContext())
        rvGames.addItemDecoration(GamesAdapterDecoration(20))
        rvGames.adapter = gamesAdapter

        fabFavoritePage.setOnClickListener {
            view.findNavController()
                .navigate(mainR.id.action_games_fragment_to_favorite_fragment)
        }

        btnReloadGames.setOnClickListener {
            gamesViewModel.findAllGames()
        }

        observe(gamesViewModel.isLoading) {
            loading.visibility = if (it) View.VISIBLE else View.INVISIBLE
            if (it) updateViewsVisibility(View.GONE, KEY_INFO)
        }

        observe(gamesViewModel.games) {
            when (it) {
                is Resource.Success -> {
                    updateViewsVisibility(View.INVISIBLE, KEY_INFO)
                    it.data?.let { data ->
                        gamesAdapter.submitList(data.shuffled())
                    }
                }
                is Resource.Error -> {
                    updateViewsVisibility(View.VISIBLE, KEY_INFO)
                    tvInfo.text = it.message
                }
            }
        }
    }

    companion object {
        private const val KEY_INFO = "info"
    }
}