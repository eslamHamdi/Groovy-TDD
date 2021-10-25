package petros.efthymiou.groovy.utils

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("ProgressState")
fun toggleLoading(progressBar: View, state: Boolean) {
    if (state)
        progressBar.visibility = View.VISIBLE
    else
        progressBar.visibility = View.GONE
}
