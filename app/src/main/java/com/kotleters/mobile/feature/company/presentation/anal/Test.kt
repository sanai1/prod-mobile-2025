package com.kotleters.mobile.feature.company.presentation.anal

import com.kotleters.mobile.common.domain.Lacuna
import com.kotleters.mobile.feature.company.domain.entity.Statistic
import java.time.LocalDate

val lacunaData = listOf(
    Lacuna(13122.16, "Оплата кредита съедает весь бюджет"),
    Lacuna(26500.30, "Слишком большие траты на косметику и уход за собой"),
    Lacuna(22211.56, "Сложно экономить на продуктах"),
    Lacuna(11833.74, "Еда в кафе слишком дорогая"),
    Lacuna(17279.14, "Слишком большие траты на косметику и уход за собой"),
    Lacuna(14045.80, "Трачу слишком много на рестораны"),
    Lacuna(25678.90, "Жилье в Москве слишком дорогое"),
    Lacuna(19755.42, "Постоянно беру такси, уходит много денег"),
    Lacuna(8900.65, "Высокие расходы на детей (школа, кружки, одежда)"),
    Lacuna(15678.32, "Много денег уходит на развлечения"),
    Lacuna(14200.50, "Дорогие подписки на стриминговые сервисы"),
    Lacuna(17450.72, "Затраты на фитнес и спортзал слишком большие"),
    Lacuna(13255.90, "Не могу контролировать покупки в интернете"),
    Lacuna(10230.40, "Постоянно трачу деньги на кофе и снеки"),
    Lacuna(28765.11, "Счета за коммунальные услуги очень высокие"),
    Lacuna(19422.37, "Оплата кредита съедает весь бюджет"),
    Lacuna(9500.25, "Слишком большие расходы на аренду"),
    Lacuna(16877.49, "Много денег уходит на подарки друзьям и родственникам"),
    Lacuna(21990.67, "Не могу отказаться от дорогого отдыха"),
    Lacuna(20555.38, "Регулярные медицинские расходы очень высокие")
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
