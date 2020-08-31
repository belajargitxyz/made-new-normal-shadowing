package alfianyabdullah.submission.detail

import alfianyabdullah.submission.core.domain.model.GameScreenshot
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_game_screenshot.view.*

class GameScreenshotAdapter(private val screenshots: MutableList<GameScreenshot>) :
    RecyclerView.Adapter<GameScreenshotAdapter.GameScreenshotHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameScreenshotHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_game_screenshot, parent, false)
        return GameScreenshotHolder(view)
    }

    override fun getItemCount() = screenshots.size

    override fun onBindViewHolder(holder: GameScreenshotHolder, position: Int) {
        holder.bind(screenshots[position].image)
    }

    class GameScreenshotHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(image: String) {
            Glide.with(itemView).load(image).apply {
                apply(RequestOptions().format(DecodeFormat.PREFER_RGB_565))
                skipMemoryCache(true)
                override(300)
                into(itemView.itemScreenshot)
            }
        }
    }
}