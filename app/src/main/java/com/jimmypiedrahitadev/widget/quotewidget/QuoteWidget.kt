package com.jimmypiedrahitadev.widget.quotewidget

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.action.ActionParameters
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.color.ColorProvider
import androidx.glance.currentState
import androidx.glance.layout.Box
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import com.jimmypiedrahitadev.widget.quotewidget.data.QuoteRepository

object QuotePref {
    val quote = stringPreferencesKey("quote")
}
class QuoteWidget: GlanceAppWidget() {
    override suspend fun provideGlance(
        context: Context,
        id: GlanceId
    ) {
        provideContent {
            val state = currentState<Preferences>()
            val currentQuote = state[QuotePref. quote]
            Box (modifier = GlanceModifier.clickable( actionRunCallback<GetQuoteCallback>() ).padding(4.dp)) {
                Text(currentQuote ?: "No hay frases disponibles", style = TextStyle(
                    color = ColorProvider(Color.White, Color.White),
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center


                ))
            }
        }
    }

}

class GetQuoteCallback: ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        val repository = QuoteRepository()
        val quote = repository.getRandomQuote()

        updateAppWidgetState(context, glanceId) { pref: MutablePreferences ->
            pref [QuotePref.quote] = quote
        }

        QuoteWidget().update(context, glanceId)
    }

}