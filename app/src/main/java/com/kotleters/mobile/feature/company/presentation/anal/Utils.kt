package com.kotleters.mobile.feature.company.presentation.anal

import com.kotleters.mobile.common.domain.Lacuna
import com.kotleters.mobile.feature.company.domain.entity.Statistic
import com.kotleters.mobile.feature.company.presentation.anal.model.LacunaUI
import kotlinx.serialization.json.Json

fun parseResponse(responseText: String): List<LacunaUI> {
    return Json.decodeFromString(responseText)
}

fun buildPrompt(lacunas: List<Lacuna>): String {
    val problems = lacunas.joinToString("\n") {
        "- Проблема: ${it.text}, средние траты: ${it.averageSpent} руб."
    }
    return """
            У меня есть список проблем пользователей с их средними ежемесячными тратами. 
            Нужно сгруппировать похожие проблемы, создать их краткое описание и определить средний доход для группы.
            
            Вот список проблем:
            $problems

            Верни JSON массив объектов с полями:
            - headline (кратко как можно это использовать в своем бизнесе чтоб закрыть эту дыру потребительскую)
            - description (краткое описание)
            - income (сумма средних трат всех пользователей с этой проблемой).
            
            Пример JSON-ответа:
            [
                {
                    "headline": "Сделать цены пониже",
                    "description": "Пользователи жалуются, что у них уходит слишком много денег на питание.",
                    "income": 15000.0
                }
            ]
            ПИШИ СРАЗУ ОБЪЕКТ JSON
            НАЧИНАЙ С [ ЗАКАНЧИВАЙ НА ]
        """.trimIndent()
}

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
