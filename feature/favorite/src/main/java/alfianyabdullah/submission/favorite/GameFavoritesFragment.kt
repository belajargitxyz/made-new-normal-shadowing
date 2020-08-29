package alfianyabdullah.submission.favorite

import alfianyabdullah.submission.base.GameBaseFragment
import alfianyabdullah.submission.base.GamesAdapter
import alfianyabdullah.submission.base.GamesAdapterDecoration
import alfianyabdullah.submission.base.observe
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

import alfianyabdullah.submission.made.R as mainR

@ExperimentalCoroutinesApi
class GameFavoritesFragment : GameBaseFragment(R.layout.fragment_favorites) {

    private val gameFavoritesViewModel: GameFavoritesViewModel by inject()
    private val gameAdapter: GamesAdapter by inject(named("gamesfavoritelocal"))

    override fun modules() = listOf(gameFavoritesModule)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gameAdapter.setOnGameItemClickListener {
            val data = Bundle().apply {
                putParcelable("GAME", it)
            }

            view.findNavController()
                .navigate(mainR.id.action_favorite_fragment_to_detail_fragment, data)
        }

        rvFavoriteGames.hasFixedSize()
        rvFavoriteGames.layoutManager = LinearLayoutManager(requireContext())
        rvFavoriteGames.addItemDecoration(GamesAdapterDecoration(20))
        rvFavoriteGames.adapter = gameAdapter

        observe(gameFavoritesViewModel.loadAllFavoriteGame()) {
            gameAdapter.submitList(it)
        }
    }
}