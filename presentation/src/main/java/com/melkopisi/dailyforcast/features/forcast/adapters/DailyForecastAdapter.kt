package com.melkopisi.dailyforcast.features.forcast.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.melkopisi.dailyforcast.databinding.ItemForecastBinding
import com.melkopisi.dailyforcast.features.forcast.adapters.DailyForecastAdapter.ForecastViewHolder
import com.melkopisi.dailyforcast.features.forcast.models.DailyForecastUiModel

class DailyForecastAdapter : RecyclerView.Adapter<ForecastViewHolder>() {

  private val diffCallback =
    object : DiffUtil.ItemCallback<DailyForecastUiModel.Forecast>() {
      override fun areItemsTheSame(
        oldItem: DailyForecastUiModel.Forecast,
        newItem: DailyForecastUiModel.Forecast
      ): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
      }

      override fun areContentsTheSame(
        oldItem: DailyForecastUiModel.Forecast,
        newItem: DailyForecastUiModel.Forecast
      ): Boolean {
        return oldItem == newItem
      }
    }
  private val differ = AsyncListDiffer(this, diffCallback)

  inner class ForecastViewHolder(
    private val binding: ItemForecastBinding
  ) :
    ViewHolder(binding.root) {

    fun bind(item: DailyForecastUiModel.Forecast) {
      with(binding) {
        item.weather.map {
        tvWeatherTitle.text = it.main
        tvWeatherDesc.text = it.description
        }
      }
    }

  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
    return ForecastViewHolder(
      ItemForecastBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
      )
    )
  }

  override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
    holder.bind(differ.currentList[position])
  }

  override fun getItemCount(): Int = differ.currentList.size

  fun setData(reminderList: List<DailyForecastUiModel.Forecast>) {
    differ.submitList(reminderList)
  }
}