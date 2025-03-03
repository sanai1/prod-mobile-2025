package com.kotleters.mobile.feature.company.presentation.anal

import com.kotleters.mobile.feature.company.domain.entity.Statistic

fun generateStatisticsPrompt(statistics: List<Statistic>): String {
    if (statistics.isEmpty()) return "Нет данных для анализа."

    val totalStats = statistics.fold(
        Statistic.Year(
            monthStart = statistics.first().year.monthStart,
            monthFinish = statistics.last().year.monthFinish,
            allAmount = 0,
            maleAmount = 0,
            femaleAmount = 0,
            kidsAmount = 0,
            youngAmount = 0,
            middleAmount = 0,
            oldAmount = 0
        )
    ) { acc, stat ->
        acc.copy(
            allAmount = acc.allAmount + stat.year.allAmount,
            maleAmount = acc.maleAmount + stat.year.maleAmount,
            femaleAmount = acc.femaleAmount + stat.year.femaleAmount,
            kidsAmount = acc.kidsAmount + stat.year.kidsAmount,
            youngAmount = acc.youngAmount + stat.year.youngAmount,
            middleAmount = acc.middleAmount + stat.year.middleAmount,
            oldAmount = acc.oldAmount + stat.year.oldAmount
        )
    }

    return """
        Анализ успешности акций компании:
        
        📅 Период: ${totalStats.monthStart} – ${totalStats.monthFinish}
        🔢 Всего активаций: ${totalStats.allAmount}
        👨 Мужчины: ${totalStats.maleAmount} (${(totalStats.maleAmount * 100.0 / totalStats.allAmount).toInt()}%)
        👩 Женщины: ${totalStats.femaleAmount} (${(totalStats.femaleAmount * 100.0 / totalStats.allAmount).toInt()}%)
        👶 Дети: ${totalStats.kidsAmount} (${(totalStats.kidsAmount * 100.0 / totalStats.allAmount).toInt()}%)
        🧑 Молодежь: ${totalStats.youngAmount} (${(totalStats.youngAmount * 100.0 / totalStats.allAmount).toInt()}%)
        👨‍🦳 Средний возраст: ${totalStats.middleAmount} (${(totalStats.middleAmount * 100.0 / totalStats.allAmount).toInt()}%)
        🧓 Пожилые: ${totalStats.oldAmount} (${(totalStats.oldAmount * 100.0 / totalStats.allAmount).toInt()}%)

        Выявите ключевые тренды, выявите слабые и сильные стороны акций. Дайте рекомендации по улучшению.
    """.trimIndent()
}
