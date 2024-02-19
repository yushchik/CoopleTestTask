package com.example.coopletesttask.presentation.weatherForecast

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.coopletesttask.data.model.weatherResponse.WeatherItem
import com.example.coopletesttask.databinding.ItemWeeklyWeatherBinding
import com.example.coopletesttask.util.formatDate
import com.example.coopletesttask.util.formatDecimals
import com.example.coopletesttask.util.getWeatherIconUrl
import com.squareup.picasso.Picasso

class WeeklyWeatherAdapter(
    private val onClick: (WeatherItem) -> Unit
) : ListAdapter<WeatherItem, WeeklyWeatherAdapter.WeeklyWeatherViewHolder>
    (WeeklyWeatherDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeeklyWeatherViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemWeeklyWeatherBinding.inflate(inflater, parent, false)
        return WeeklyWeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeeklyWeatherViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener { onClick(item) }
    }

    class WeeklyWeatherViewHolder(private val binding: ItemWeeklyWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: WeatherItem) {
            binding.tvWeatherDate.text = formatDate(item.dt)
            binding.tvWeatherTemp.text = "${formatDecimals(item.temp.day)}º"
            binding.tvWeatherFeels.text = item.weather[0].description
            Picasso.get()
                .load(getWeatherIconUrl(item.weather[0].icon))
                .into(binding.imgWeather)
        }
    }
}

private class WeeklyWeatherDiffCallback : DiffUtil.ItemCallback<WeatherItem>() {

    override fun areItemsTheSame(oldItem: WeatherItem, newItem: WeatherItem): Boolean {
        return oldItem.dt == newItem.dt
    }

    override fun areContentsTheSame(
        oldItem: WeatherItem,
        newItem: WeatherItem
    ): Boolean {
        return oldItem == newItem
    }
}