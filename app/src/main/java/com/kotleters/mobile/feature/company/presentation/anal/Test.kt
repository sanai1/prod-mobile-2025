package com.kotleters.mobile.feature.company.presentation.anal

import com.kotleters.mobile.common.domain.Lacuna
import com.kotleters.mobile.feature.company.domain.entity.Statistic
import java.time.LocalDate

val lacunaData = listOf(
    Lacuna(15220.45, "Часто хожу в рестораны, уходит много денег"),
    Lacuna(13280.75, "Люблю заказывать еду на дом, это выходит дорого"),
    Lacuna(18760.30, "Регулярно ужинаю в кафе, слишком большие траты"),
    Lacuna(9200.50, "Кофе и десерты в кофейнях съедают бюджет"),
    Lacuna(16850.90, "Каждый день беру обед в ресторане, выходит дорого"),
    Lacuna(14440.20, "Часто хожу в бары с друзьями, трачу слишком много"),
    Lacuna(21120.10, "Люблю пробовать новые заведения, но это дорого"),
    Lacuna(18900.80, "Слишком часто ем фастфуд, но это бьёт по кошельку"),
    Lacuna(12230.35, "Обычный ужин в ресторане стоит слишком дорого"),
    Lacuna(17890.60, "Трачу слишком много на дорогие рестораны"),
    Lacuna(13450.45, "Люблю гастрономические туры, но это очень затратно"),
    Lacuna(15830.75, "Слишком много денег уходит на бизнес-ланчи"),
    Lacuna(19320.50, "Каждую неделю пробую новые блюда, выходит дорого"),
    Lacuna(9200.10, "Постоянно покупаю завтраки в кафе, лучше готовить дома"),
    Lacuna(24800.60, "Часто заказываю еду в премиум-заведениях"),
    Lacuna(18450.40, "Люблю японскую кухню, но суши и роллы очень дорогие"),
    Lacuna(11200.30, "Привык ходить в рестораны на свидания, но это затратно"),
    Lacuna(15400.70, "Люблю авторскую кухню, но цены кусаются"),
    Lacuna(20750.20, "Трачу много денег на дегустационные сеты в ресторанах"),
    Lacuna(13990.80, "Хожу в кафе ради атмосферы, но это сказывается на бюджете")
)



val testData = listOf(
    Statistic(
        month = Statistic.Month(
            month = LocalDate.of(2024, 1, 1),
            allAmount = 1145L,
            maleAmount = 152L,
            femaleAmount = 325L,
            kidsAmount = 263L,
            youngAmount = 197L,
            middleAmount = 293L,
            oldAmount = 392L
        ),
        quarter = Statistic.Quarter(
            monthStart = LocalDate.of(2023, 12, 2),
            monthFinish = LocalDate.of(2024, 1, 31),
            allAmount = 3435L,
            maleAmount = 456L,
            femaleAmount = 975L,
            kidsAmount = 789L,
            youngAmount = 591L,
            middleAmount = 879L,
            oldAmount = 1176L
        ),
        year = Statistic.Year(
            monthStart = LocalDate.of(2023, 7, 5),
            monthFinish = LocalDate.of(2024, 6, 29),
            allAmount = 13740L,
            maleAmount = 1824L,
            femaleAmount = 3900L,
            kidsAmount = 3156L,
            youngAmount = 2364L,
            middleAmount = 3516L,
            oldAmount = 4704L
        )
    ),
    Statistic(
        month = Statistic.Month(
            month = LocalDate.of(2024, 1, 31),
            allAmount = 713L,
            maleAmount = 230L,
            femaleAmount = 286L,
            kidsAmount = 170L,
            youngAmount = 141L,
            middleAmount = 199L,
            oldAmount = 203L
        ),
        quarter = Statistic.Quarter(
            monthStart = LocalDate.of(2024, 1, 1),
            monthFinish = LocalDate.of(2024, 3, 1),
            allAmount = 2139L,
            maleAmount = 690L,
            femaleAmount = 858L,
            kidsAmount = 510L,
            youngAmount = 423L,
            middleAmount = 597L,
            oldAmount = 609L
        ),
        year = Statistic.Year(
            monthStart = LocalDate.of(2023, 8, 4),
            monthFinish = LocalDate.of(2024, 7, 29),
            allAmount = 8556L,
            maleAmount = 2760L,
            femaleAmount = 3432L,
            kidsAmount = 2040L,
            youngAmount = 1692L,
            middleAmount = 2388L,
            oldAmount = 2436L
        )
    ),
    Statistic(
        month = Statistic.Month(
            month = LocalDate.of(2024, 3, 1),
            allAmount = 1242L,
            maleAmount = 233L,
            femaleAmount = 570L,
            kidsAmount = 187L,
            youngAmount = 394L,
            middleAmount = 274L,
            oldAmount = 387L
        ),
        quarter = Statistic.Quarter(
            monthStart = LocalDate.of(2024, 1, 31),
            monthFinish = LocalDate.of(2024, 3, 31),
            allAmount = 3726L,
            maleAmount = 699L,
            femaleAmount = 1710L,
            kidsAmount = 561L,
            youngAmount = 1182L,
            middleAmount = 822L,
            oldAmount = 1161L
        ),
        year = Statistic.Year(
            monthStart = LocalDate.of(2023, 9, 3),
            monthFinish = LocalDate.of(2024, 8, 28),
            allAmount = 14904L,
            maleAmount = 2796L,
            femaleAmount = 6840L,
            kidsAmount = 2244L,
            youngAmount = 4728L,
            middleAmount = 3288L,
            oldAmount = 4644L
        )
    ),
    Statistic(
        month = Statistic.Month(
            month = LocalDate.of(2024, 3, 31),
            allAmount = 548L,
            maleAmount = 196L,
            femaleAmount = 172L,
            kidsAmount = 122L,
            youngAmount = 160L,
            middleAmount = 159L,
            oldAmount = 107L
        ),
        quarter = Statistic.Quarter(
            monthStart = LocalDate.of(2024, 3, 1),
            monthFinish = LocalDate.of(2024, 4, 30),
            allAmount = 1644L,
            maleAmount = 588L,
            femaleAmount = 516L,
            kidsAmount = 366L,
            youngAmount = 480L,
            middleAmount = 477L,
            oldAmount = 321L
        ),
        year = Statistic.Year(
            monthStart = LocalDate.of(2023, 10, 3),
            monthFinish = LocalDate.of(2024, 9, 27),
            allAmount = 6576L,
            maleAmount = 2352L,
            femaleAmount = 2064L,
            kidsAmount = 1464L,
            youngAmount = 1920L,
            middleAmount = 1908L,
            oldAmount = 1284L
        )
    ),
    Statistic(
        month = Statistic.Month(
            month = LocalDate.of(2024, 4, 30),
            allAmount = 1473L,
            maleAmount = 258L,
            femaleAmount = 455L,
            kidsAmount = 245L,
            youngAmount = 297L,
            middleAmount = 305L,
            oldAmount = 626L
        ),
        quarter = Statistic.Quarter(
            monthStart = LocalDate.of(2024, 3, 31),
            monthFinish = LocalDate.of(2024, 5, 30),
            allAmount = 4419L,
            maleAmount = 774L,
            femaleAmount = 1365L,
            kidsAmount = 735L,
            youngAmount = 891L,
            middleAmount = 915L,
            oldAmount = 1878L
        ),
        year = Statistic.Year(
            monthStart = LocalDate.of(2023, 11, 2),
            monthFinish = LocalDate.of(2024, 10, 27),
            allAmount = 17676L,
            maleAmount = 3096L,
            femaleAmount = 5460L,
            kidsAmount = 2940L,
            youngAmount = 3564L,
            middleAmount = 3660L,
            oldAmount = 7512L
        )
    ),
    Statistic(
        month = Statistic.Month(
            month = LocalDate.of(2024, 5, 30),
            allAmount = 1033L,
            maleAmount = 459L,
            femaleAmount = 248L,
            kidsAmount = 203L,
            youngAmount = 140L,
            middleAmount = 288L,
            oldAmount = 402L
        ),
        quarter = Statistic.Quarter(
            monthStart = LocalDate.of(2024, 4, 30),
            monthFinish = LocalDate.of(2024, 6, 29),
            allAmount = 3099L,
            maleAmount = 1377L,
            femaleAmount = 744L,
            kidsAmount = 609L,
            youngAmount = 420L,
            middleAmount = 864L,
            oldAmount = 1206L
        ),
        year = Statistic.Year(
            monthStart = LocalDate.of(2023, 12, 2),
            monthFinish = LocalDate.of(2024, 11, 26),
            allAmount = 12396L,
            maleAmount = 5508L,
            femaleAmount = 2976L,
            kidsAmount = 2436L,
            youngAmount = 1680L,
            middleAmount = 3456L,
            oldAmount = 4824L
        )
    )
)
