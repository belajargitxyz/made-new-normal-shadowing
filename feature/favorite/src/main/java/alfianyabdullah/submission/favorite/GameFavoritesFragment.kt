package alfianyabdullah.submission.favorite

import alfianyabdullah.submission.base.*
import alfianyabdullah.submission.made.AppAnalytics
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.android.inject
import alfianyabdullah.submission.made.R as mainR

@ExperimentalCoroutinesApi
class GameFavoritesFragment : GameBaseFragment(R.layout.fragment_favorites) {

    private val gameFavoritesViewModel: GameFavoritesViewModel by inject()
//    private val gameAdapter: GamesAdapter by inject(named(GAME_FAVORITE_QUALIFIER))

    override fun modules() = listOf(gameFavoritesModule)
    override fun views(): Map<String, List<View>> {
        return mapOf(
            KEY_FAVORITE to listOf(
                ivFavorite, tvFavorite
            )
        )
    }

    override fun onViewReady(view: View) {
        rvFavoriteGames.hasFixedSize()
        rvFavoriteGames.layoutManager = LinearLayoutManager(requireContext())
        rvFavoriteGames.addItemDecoration(GamesAdapterDecoration(20))
        rvFavoriteGames.adapter = GamesAdapter(mutableListOf())

        (rvFavoriteGames.adapter as GamesAdapter).setOnGameItemClickListener {
            val data = Bundle().apply {
                putParcelable(GAME_BUNDLE_KEY, it)
            }

            AppAnalytics.pushEvent("GAME_FAVORITE_CLICK") {
                param(FirebaseAnalytics.Param.ITEM_NAME, "Interested favorite game")
                param(FirebaseAnalytics.Param.ITEMS, data)
            }

            view.findNavController()
                .navigate(mainR.id.action_favorite_fragment_to_detail_fragment, data)
        }

        observe(gameFavoritesViewModel.loadAllFavoriteGame()) {
            if (it.isNotEmpty()) {
                (rvFavoriteGames.adapter as GamesAdapter).submitList(it)
                updateViewsVisibility(View.INVISIBLE, KEY_FAVORITE)
            } else {
                rvFavoriteGames.visibility = View.INVISIBLE
                updateViewsVisibility(View.VISIBLE, KEY_FAVORITE)
            }
        }
    }

    companion object {
        private const val KEY_FAVORITE = "info"
    }
}