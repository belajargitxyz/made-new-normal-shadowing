package alfianyabdullah.submission.base

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GamesAdapterDecoration(private val size: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = size
            }

            bottom = if ((parent.adapter as GamesAdapter).data().lastIndex ==
                parent.getChildAdapterPosition(view)
            ) {
                size * 10
            } else {

                size
            }
        }
    }
}