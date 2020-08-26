package alfianyabdullah.submission.games

import alfianyabdullah.submission.core.data.Resource
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_games.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

@ExperimentalCoroutinesApi
class GamesActivity : AppCompatActivity() {

    private val gamesViewModel: GamesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadKoinModules(gamesModule)
        setContentView(R.layout.activity_games)

        gamesViewModel.games.observe(this, Observer {
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(
                    Intent(this, Class.forName("alfianyabdullah.submission.detail.GameDetailActivity"))
                )
            }, 5000)
            when(it){
                is Resource.Success -> {
                    maintext.text = it.data?.size.toString()
                }
                is Resource.Error -> {
                    maintext.text = it.message
                }
            }
        })
    }
}