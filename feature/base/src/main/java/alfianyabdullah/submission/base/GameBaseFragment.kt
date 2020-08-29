package alfianyabdullah.submission.base

import alfianyabdullah.submission.core.domain.model.Game
import android.os.Bundle
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module

abstract class GameBaseFragment(private val resLayout: Int) : Fragment() {

    abstract fun modules(): List<Module>
    abstract fun views(): Map<String, List<View>>
    lateinit var gameBundle: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadKoinModules(modules())

        arguments?.let {
            gameBundle = it.getParcelable<Game>(GAME_BUNDLE_KEY) as Game
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(resLayout, container, false)
    }

    fun updateViewsVisibility(visibility: Int, keyView: String) {
        views()[keyView]?.forEach {
            TransitionManager.beginDelayedTransition(it.parent as ViewGroup)
            it.visibility = visibility
        }
    }
}