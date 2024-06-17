package com.example.waterusage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.waterusage.ui.theme.WaterUsageTheme
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WaterUsageTheme {
                WaterUsageApp()
            }
        }
    }
}

@Composable
fun WaterUsageApp() {
    var showBarChart by remember { mutableStateOf(false) }
    val dailyUsage = listOf(2f, 3f, 1.5f, 4f, 3.5f, 2f, 5f)
    val last24HoursUsage = 3.5f

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressBar(usage = last24HoursUsage)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { showBarChart = !showBarChart }) {
            Text(text = if (showBarChart) "Hide Usage" else "Show Usage")
        }
        if (showBarChart) {
            Spacer(modifier = Modifier.height(16.dp))
            BarChartView(dailyUsage = dailyUsage)
        }
    }
}

@Composable
fun CircularProgressBar(usage: Float) {
    val maxUsage = 5f
    val progress = usage / maxUsage

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(200.dp)
    ) {
        Canvas(modifier = Modifier.size(200.dp)) {
            drawCircle(
                color = Color.LightGray,
                style = Stroke(width = 16.dp.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = Color.Green,
                startAngle = -90f,
                sweepAngle = 360 * progress,
                useCenter = false,
                style = Stroke(width = 16.dp.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(text = "${(progress * 100).toInt()}%", style = MaterialTheme.typography.h4)
    }
}

@Composable
fun BarChartView(dailyUsage: List<Float>) {
    AndroidView(
        factory = { context ->
            com.github.mikephil.charting.charts.BarChart(context).apply {
                val entries = dailyUsage.mapIndexed { index, usage -> BarEntry(index.toFloat(), usage) }
                val dataSet = BarDataSet(entries, "Water Usage")
                dataSet.colors = ColorTemplate.MATERIAL_COLORS.toList()
                val data = BarData(dataSet)
                this.data = data
                this.invalidate() // refresh the chart
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    )
}

