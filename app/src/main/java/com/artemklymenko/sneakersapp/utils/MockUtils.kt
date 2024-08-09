package com.artemklymenko.sneakersapp.utils

import com.artemklymenko.sneakersapp.domain.models.Notification
import com.artemklymenko.sneakersapp.domain.models.Product

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

        fun loadMockProducts(): List<Product> {
            return listOf(
                Product(1, "Product 1", 39.99, "https://as2.ftcdn.net/v2/jpg/02/11/11/15/1000_F_211111574_VLtzH6ORhebXvnJXjlkAkaUuAftnvmJH.jpg", false),
                Product(2, "Product 2", 39.99, "https://as1.ftcdn.net/v2/jpg/02/60/22/68/1000_F_260226888_aZ3EJOTSE2ly5TlRuseWexnVkK67KqBl.jpg", true),
                Product(3, "Product 3", 52.85, "https://as1.ftcdn.net/v2/jpg/03/14/89/16/1000_F_314891647_CVY0iUdvxyJ2D6RfP6jNvktTWYdNvy6y.jpg", false),
                Product(4, "Product 4", 74.42, "https://as1.ftcdn.net/v2/jpg/02/64/98/34/1000_F_264983445_wXVAKMmZe0Q1jJN6R6riVJsajb7fGvx3.jpg", true),
                Product(5, "Product 5", 95.21, "https://as2.ftcdn.net/v2/jpg/02/11/11/15/1000_F_211111574_VLtzH6ORhebXvnJXjlkAkaUuAftnvmJH.jpg", false),
                Product(6, "Product 6", 11.11, "https://as1.ftcdn.net/v2/jpg/02/60/22/68/1000_F_260226888_aZ3EJOTSE2ly5TlRuseWexnVkK67KqBl.jpg", true),
                Product(7, "Product 7", 22.22, "https://as1.ftcdn.net/v2/jpg/02/60/22/68/1000_F_260226888_aZ3EJOTSE2ly5TlRuseWexnVkK67KqBl.jpg", false),
                Product(8, "Product 8", 33.33, "https://as1.ftcdn.net/v2/jpg/03/14/89/16/1000_F_314891647_CVY0iUdvxyJ2D6RfP6jNvktTWYdNvy6y.jpg", true),
                Product(9, "Product 9", 44.44, "https://as1.ftcdn.net/v2/jpg/03/14/89/16/1000_F_314891647_CVY0iUdvxyJ2D6RfP6jNvktTWYdNvy6y.jpg", false),
                Product(10, "Product 10", 55.55, "https://as1.ftcdn.net/v2/jpg/02/64/98/34/1000_F_264983445_wXVAKMmZe0Q1jJN6R6riVJsajb7fGvx3.jpg", true),
            )
        }

        fun loadMockSearchCategories(): List<String> {
            return listOf("All", "Top", "Price", "New")
        }
    }