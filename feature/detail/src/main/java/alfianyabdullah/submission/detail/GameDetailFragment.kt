package alfianyabdullah.submission.detail

import alfianyabdullah.submission.base.GameBaseFragment
import alfianyabdullah.submission.base.attachToViewPager
import alfianyabdullah.submission.base.observe
import alfianyabdullah.submission.core.data.Resource
import alfianyabdullah.submission.made.AppAnalytics
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.content_game_detail.*
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
    override fun views() = mapOf(
        KEY_INFO to listOf(
            tvInfo, ivInfo, tvOtherInfo, btnReloadGames
        ),
        KEY_CONTENT to listOf(
            itemDetailName,
            icRating,
            itemDetailRating,
            tvTextPublisher,
            chipPublisher,
            tvTextGenres,
            chipGenres,
            tvTextDescription,
            itemDetailDescription
        )
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateViewsVisibility(View.INVISIBLE, KEY_INFO)

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

        btnReloadGames.setOnClickListener {
            gameDetailViewModel.findGameById(gameBundle.id)
        }

        gameDetailViewModel.loadFavoriteStatus(gameBundle)
        gameDetailViewModel.findGameById(gameBundle.id)

        observe(gameDetailViewModel.game) {
            fun ChipGroup.addChild(text: String) {
                val child = Chip(this.context)
                child.text = text

                this.addView(child)
            }

            when (it) {
                is Resource.Success -> {
                    it.data?.let { game ->
                        itemDetailDescription.text = game.desc

                        chipPublisher.removeAllViews()
                        game.publishers.forEach { pub ->
                            chipPublisher.addChild(pub.name)
                        }

                        chipGenres.removeAllViews()
                        gameBundle.genres.forEach { genre ->
                            chipGenres.addChild(genre.name)
                        }

                        fabFavorite.show()

                        updateViewsVisibility(View.INVISIBLE, KEY_INFO)
                        updateViewsVisibility(View.VISIBLE, KEY_CONTENT)
                    }
                }
                is Resource.Error -> {
                    updateViewsVisibility(View.VISIBLE, KEY_INFO)
                    tvInfo.text = it.message
                }
            }
        }

        observe(gameDetailViewModel.isLoading) {
            loading.visibility = if (it) View.VISIBLE else View.INVISIBLE
            if (it) {
                updateViewsVisibility(View.INVISIBLE, KEY_INFO)
                updateViewsVisibility(View.INVISIBLE, KEY_CONTENT)
            }
        }

        observe(gameDetailViewModel.favorite) {
            isFavorite = it
            if (it) {
                AppAnalytics.pushEvent("GAME_FAVORITE") {
                    param(FirebaseAnalytics.Param.ITEM_NAME, "Favorite game")
                    param(FirebaseAnalytics.Param.ITEMS, gameBundle.name)
                }
                fabFavorite.setImageResource(R.drawable.ic_favorite)
            } else {
                fabFavorite.setImageResource(R.drawable.ic_unfavorite)
            }
        }
    }

    companion object {
        private const val KEY_INFO = "info"
        private const val KEY_CONTENT = "content"
    }
}