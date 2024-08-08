package com.artemklymenko.sneakersapp.utils

import com.artemklymenko.sneakersapp.domain.models.Notification

object MockUtils {
    fun loadMockNotifications(): List<Notification> {
        return listOf(
            Notification(
                0,
                "https://ggsc.s3.amazonaws.com/images/uploads/The_Science-Backed_Benefits_of_Being_a_Dog_Owner.jpg",
                "You got discount for this week",
                "We have a good news! You got discount for this week for -10%. To apply this discount use promo code #LEARN_CARDS",
                false,
            ),
            Notification(
                1,
                "https://ggsc.s3.amazonaws.com/images/uploads/The_Science-Backed_Benefits_of_Being_a_Dog_Owner.jpg",
                "Your password has been changed",
                "02.11.2022 you sent a request to change your password. Your password was successfully changed, Please let us know if it was not you.",
                true,
            ),
            Notification(
                2,
                "https://ggsc.s3.amazonaws.com/images/uploads/The_Science-Backed_Benefits_of_Being_a_Dog_Owner.jpg",
                "Meet a new category",
                "We're pleased to announce that we added a new category \"For party\". Find it out, we're you would love that.",
                false,
            ),
            Notification(
                3,
                "https://ggsc.s3.amazonaws.com/images/uploads/The_Science-Backed_Benefits_of_Being_a_Dog_Owner.jpg",
                "You got discount for this week",
                "We have a good news! You got discount for this week for -10%. To apply this discount use promo code #LEARN_CARDS",
                false,
            ),
            Notification(
                4,
                "https://ggsc.s3.amazonaws.com/images/uploads/The_Science-Backed_Benefits_of_Being_a_Dog_Owner.jpg",
                "Your password has been changed",
                "02.11.2022 you sent a request to change your password. Your password was successfully changed, Please let us know if it was not you.",
                true,
            ),
            Notification(
                5,
                "https://ggsc.s3.amazonaws.com/images/uploads/The_Science-Backed_Benefits_of_Being_a_Dog_Owner.jpg",
                "Meet a new category",
                "We're pleased to announce that we added a new category \"For party\". Find it out, we're you would love that.",
                false,
            ),
            Notification(
                6,
                "https://ggsc.s3.amazonaws.com/images/uploads/The_Science-Backed_Benefits_of_Being_a_Dog_Owner.jpg",
                "You got discount for this week",
                "We have a good news! You got discount for this week for -10%. To apply this discount use promo code #LEARN_CARDS",
                false,
            )
        )

    }
}