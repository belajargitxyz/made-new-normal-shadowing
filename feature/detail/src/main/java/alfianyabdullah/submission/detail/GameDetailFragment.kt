package alfianyabdullah.submission.detail

import alfianyabdullah.submission.base.GameBaseFragment
import alfianyabdullah.submission.core.data.Resource
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_game_detail.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class GameDetailFragment : GameBaseFragment(R.layout.fragment_game_detail) {

    private val gameDetailViewModel: GameDetailViewModel by viewModel()
    private val gameScreenshotAdapter: GameScreenshotAdapter by inject()

    override fun modules() = listOf(gamesDetailModule)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rating = "${gameBundle.rating}/5"
        itemDetailRating.text = rating
        itemDetailName.text = gameBundle.name

        itemDetailScreenshot.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        itemDetailScreenshot.offscreenPageLimit = 4
        itemDetailScreenshot.adapter = gameScreenshotAdapter

        gameScreenshotAdapter.submit(gameBundle.screenshots)

        TabLayoutMediator(itemDetailScreenshotIndicator, itemDetailScreenshot) { _, _ -> }.attach()

        gameDetailViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            loading.visibility = if (it) View.VISIBLE else View.INVISIBLE
            detailView.visibility = if (it) View.INVISIBLE else View.VISIBLE
        })

        gameDetailViewModel.findGameById(gameBundle.id)
        gameDetailViewModel.game.observe(viewLifecycleOwner, Observer {
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
                    }
                }
                is Resource.Error -> {
                }
            }
        })
    }
}