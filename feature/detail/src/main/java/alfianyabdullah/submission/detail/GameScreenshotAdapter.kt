package alfianyabdullah.submission.detail

import alfianyabdullah.submission.base.loadImageFromNetwork
import alfianyabdullah.submission.core.domain.model.GameScreenshot
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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

    fun submit(screenshots: List<GameScreenshot>) {
        this.screenshots.clear()
        this.screenshots.addAll(screenshots)

        notifyDataSetChanged()
    }

    class GameScreenshotHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(image: String) {
            itemView.itemScreenshot.loadImageFromNetwork(image)
        }
    }
}