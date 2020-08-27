package alfianyabdullah.submission.detail

import alfianyabdullah.submission.base.GameBaseFragment
import alfianyabdullah.submission.core.data.Resource
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_game_detail.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named

@ExperimentalCoroutinesApi
class GameDetailFragment : GameBaseFragment(R.layout.fragment_game_detail) {

    private val gamesDetailViewModel: GameDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(gamesDetailModule)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(view)
            .load(gameBundle.poster)
            .transition(DrawableTransitionOptions.withCrossFade())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(itemDetailPoster)


        itemDetailName.text = gameBundle.name
        itemDetailRating.text = "${gameBundle.rating}/5"

        gamesDetailViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            loading.visibility = if (it) View.VISIBLE else View.INVISIBLE
            detailView.visibility = if (it) View.INVISIBLE else View.VISIBLE
        })

        gamesDetailViewModel.findGameById(gameBundle.id)
        gamesDetailViewModel.game.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { game ->
                        itemDetailDescription.text = game.desc.replace("\r\n", "\n\n")

                        game.publishers.forEach { pub ->
                            val publisher = Chip(requireContext())
                            publisher.text = pub.name

                            chipPublisher.addView(publisher)
                        }
                    }
                }
                is Resource.Error -> {
                }
            }
        })
    }
}