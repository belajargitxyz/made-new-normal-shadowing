package alfianyabdullah.submission.games

import alfianyabdullah.submission.base.*
import alfianyabdullah.submission.core.data.Resource
import alfianyabdullah.submission.made.AppAnalytics
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.content_games.*
import kotlinx.android.synthetic.main.fragment_games.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.ext.android.viewModel
import alfianyabdullah.submission.made.R as mainR

@ExperimentalCoroutinesApi
class GamesFragment : GameBaseFragment(R.layout.fragment_games) {

    private val gamesViewModel: GamesViewModel by viewModel()
    //private val gamesAdapter: GamesAdapter by inject(named(GAME_QUALIFIER))

    override fun modules() = listOf(gamesModule)
    override fun views() = mapOf(
        KEY_INFO to listOf(
            tvInfo, ivInfo, tvOtherInfo, btnReloadGames
        )
    )

    override fun onViewReady(view: View) {
        updateViewsVisibility(View.INVISIBLE, KEY_INFO)

        rvGames.hasFixedSize()
        rvGames.layoutManager = LinearLayoutManager(requireContext())
        rvGames.addItemDecoration(GamesAdapterDecoration(20))
        rvGames.adapter = GamesAdapter(mutableListOf())

        (rvGames.adapter as GamesAdapter).setOnGameItemClickListener {

            val data = Bundle().apply {
                putParcelable(GAME_BUNDLE_KEY, it)
            }

            AppAnalytics.pushEvent("GAME_CLICK") {
                param(FirebaseAnalytics.Param.ITEM_NAME, "Interested game")
                param(FirebaseAnalytics.Param.ITEMS, data)
            }

            view.findNavController()
                .navigate(mainR.id.action_games_fragment_to_detail_fragment, data)
        }

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
                        (rvGames.adapter as GamesAdapter).submitList(data)
                    }
                }
                is Resource.Error -> {
                    if ((rvGames.adapter as GamesAdapter).data().isEmpty()) {
                        updateViewsVisibility(View.VISIBLE, KEY_INFO)
                        tvInfo.text = it.message
                    }
                }
            }
        }
    }

    companion object {
        private const val KEY_INFO = "info"
    }
}