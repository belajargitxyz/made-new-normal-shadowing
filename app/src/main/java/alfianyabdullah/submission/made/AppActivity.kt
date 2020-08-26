package alfianyabdullah.submission.made

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class AppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(
                Intent(this, Class.forName("alfianyabdullah.submission.games.GamesActivity"))
            )
        }, 5000)
    }
}