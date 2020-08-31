package alfianyabdullah.submission.base

import alfianyabdullah.submission.core.domain.model.Game
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.item_game.view.*
import java.text.DecimalFormat

class GamesAdapter(
    private val games: MutableList<Game>
) :
    RecyclerView.Adapter<GamesAdapter.GameHolder>() {

    private var actionCLick: ((Game) -> Unit)? = null

    fun setOnGameItemClickListener(actionCLick: (Game) -> Unit) {
        this.actionCLick = actionCLick
    }

    fun submitList(gamesNew: List<Game>) {
        this.games.clear()
        this.games.addAll(gamesNew)

        notifyDataSetChanged()
    }

    fun data() = games

    override fun onViewRecycled(holder: GameHolder) {
        super.onViewRecycled(holder)

        Glide.with(holder.itemView.context.applicationContext).clear(holder.itemView.itemBackground)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return GameHolder(view)
    }

    override fun getItemCount() = games.size

    override fun onBindViewHolder(holder: GameHolder, position: Int) {
        holder.bind(games[position], actionCLick)
    }

    class GameHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(game: Game, actionCLick: ((Game) -> Unit)?) {
            itemView.itemRating.text = DecimalFormat("#.0").format(game.rating)
            itemView.itemName.text = game.name

            val genres = game.genres
            val genresSize = genres.size

            if (genres.isEmpty()) {
                itemView.mainChip.text = "-"
                itemView.otherChip.visibility = View.INVISIBLE
            } else {
                if (genresSize > 1) {
                    val other = "+${genresSize - 1}"
                    itemView.otherChip.text = other
                    itemView.otherChip.visibility = View.VISIBLE
                    itemView.mainChip.text = genres[0].name
                } else {
                    itemView.mainChip.text = genres[0].name
                    itemView.otherChip.visibility = View.INVISIBLE
                }
            }

            itemView.itemBackground.loadImageFromNetwork(game.poster) {
                transform(BlurTransformation(6, 1))
            }

            itemView.setOnClickListener {
                actionCLick?.invoke(game)
            }
        }
    }
}