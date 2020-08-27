package alfianyabdullah.submission.base

import alfianyabdullah.submission.core.domain.model.Game
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.item_game.view.*

class GamesAdapter(private val games: MutableList<Game>) :
    RecyclerView.Adapter<GamesAdapter.GameHolder>() {

    fun submitList(gamesNew: List<Game>) {
        this.games.clear()
        this.games.addAll(gamesNew)

        notifyDataSetChanged()
    }

    fun data() = games

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return GameHolder(view)
    }

    override fun getItemCount() = games.size

    override fun onBindViewHolder(holder: GameHolder, position: Int) {
        holder.bind(games[position])
    }

    inner class GameHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(game: Game) {
            itemView.itemName.text = game.name
            itemView.itemRating.text = "${game.rating}/5"

            Glide.with(itemView)
                .load(game.poster)
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.drawable.ic_vr_gaming_item)
                .centerCrop()
                .into(itemView.itemPoster)
        }
    }
}