package alfianyabdullah.submission.detail

import alfianyabdullah.submission.base.GameBaseFragment
import alfianyabdullah.submission.base.attachToViewPager
import alfianyabdullah.submission.base.observe
import alfianyabdullah.submission.core.data.Resource
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_game_detail.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class GameDetailFragment : GameBaseFragment(R.layout.fragment_game_detail) {

    private val gameDetailViewModel: GameDetailViewModel by viewModel()
    private val gameScreenshotAdapter: GameScreenshotAdapter by inject()
    private var isFavorite = false

    override fun modules() = listOf(gamesDetailModule)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabFavorite.hide()

        val rating = "${gameBundle.rating}/5"
        itemDetailRating.text = rating
        itemDetailName.text = gameBundle.name

        itemDetailScreenshot.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        itemDetailScreenshot.offscreenPageLimit = 4
        itemDetailScreenshot.adapter = gameScreenshotAdapter
        itemDetailScreenshot.attachToViewPager(itemDetailScreenshotIndicator)

        gameScreenshotAdapter.submit(gameBundle.screenshots)

        fabFavorite.setOnClickListener {
            gameDetailViewModel.updateGamesFavorite(isFavorite, gameBundle)
        }

        gameDetailViewModel.loadFavoriteStatus(gameBundle)
        gameDetailViewModel.findGameById(gameBundle.id)

        observe(gameDetailViewModel.game) {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { game ->
                        itemDetailDescription.text = game.desc

                        game.publishers.forEach { pub ->
                            val publisher = Chip(requireContext())
                            publisher.text = pub.name

                            chipPublisher.addView(publisher)
                        }

                        gameBundle.genres.forEach { genre ->
                            val genreChip = Chip(requireContext())
                            genreChip.text = genre.name

                            chipGenres.addView(genreChip)
                        }

                        fabFavorite.show()
                    }
                }
                is Resource.Error -> {

                }
            }
        }

        observe(gameDetailViewModel.isLoading) {
            loading.visibility = if (it) View.VISIBLE else View.INVISIBLE
            detailView.visibility = if (it) View.INVISIBLE else View.VISIBLE
        }

        observe(gameDetailViewModel.favorite) {
            isFavorite = it
            if (it) {
                fabFavorite.setImageResource(R.drawable.ic_favorite)
            } else {
                fabFavorite.setImageResource(R.drawable.ic_unfavorite)
            }
        }
    }
}