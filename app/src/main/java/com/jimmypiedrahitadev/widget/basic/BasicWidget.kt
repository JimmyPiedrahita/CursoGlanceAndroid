package com.jimmypiedrahitadev.widget.basic

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.Column
import androidx.glance.text.Text

class BasicWidget: GlanceAppWidget() {
    override suspend fun provideGlance(
        context: Context,
        id: GlanceId
    ) {
        provideContent {
            MyContent()
        }
    }

    @Composable
    private fun MyContent() {
        Column {
            Text("Soy Jimmy Piedrahita")
            Text("Desarrollador de software")
        }
    }

}