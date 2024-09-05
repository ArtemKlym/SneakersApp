package com.artemklymenko.sneakersapp.utils

import com.artemklymenko.sneakersapp.domain.models.DeliveryAddress
import com.artemklymenko.sneakersapp.domain.models.Notification
import com.artemklymenko.sneakersapp.domain.models.PaymentMethod
import com.artemklymenko.sneakersapp.domain.models.Product
import com.artemklymenko.sneakersapp.domain.models.ProductCart
import com.artemklymenko.sneakersapp.domain.models.ProductDetails
import com.artemklymenko.sneakersapp.domain.models.ProductPrices
import com.artemklymenko.sneakersapp.domain.models.User

object MockUtils {

    private const val LOREM_IPSUM_SOURCE =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer sodales laoreet commodo. Phasellus a purus eu risus elementum consequat. Aenean eu elit ut nunc convallis laoreet non ut libero. Suspendisse interdum placerat risus vel ornare. Donec vehicula, turpis sed consectetur ullamcorper, ante nunc egestas quam, ultricies adipiscing velit enim at nunc. Aenean id diam neque. Praesent ut lacus sed justo viverra fermentum et ut sem. Fusce convallis gravida lacinia. Integer semper dolor ut elit sagittis lacinia. Praesent sodales scelerisque eros at rhoncus. Duis posuere sapien vel ipsum ornare interdum at eu quam. Vestibulum vel massa erat. Aenean quis sagittis purus. Phasellus arcu purus, rutrum id consectetur non, bibendum at nibh. Duis nec erat dolor. Nulla vitae consectetur ligula. Quisque nec mi est. Ut quam ante, rutrum at pellentesque gravida, pretium in dui. Cras eget sapien velit. Suspendisse ut sem nec tellus vehicula eleifend sit amet quis velit. Phasellus quis suscipit nisi. Nam elementum malesuada tincidunt. Curabitur iaculis pretium eros, malesuada faucibus leo eleifend a. Curabitur congue orci in neque euismod a blandit libero vehicula."

    fun loadMockNotifications(): List<Notification> {
        return listOf(
            Notification(
                0,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcOTBOIhBGY2JBZKYDcjGelcx-msm7RY0wlQ&s",
                "You got discount for this week",
                "We have a good news! You got discount for this week for -10%. To apply this discount use promo code #LEARN_CARDS",
                false,
            ),
            Notification(
                1,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcOTBOIhBGY2JBZKYDcjGelcx-msm7RY0wlQ&s",
                "Your password has been changed",
                "02.11.2022 you sent a request to change your password. Your password was successfully changed, Please let us know if it was not you.",
                true,
            ),
            Notification(
                2,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcOTBOIhBGY2JBZKYDcjGelcx-msm7RY0wlQ&s",
                "Meet a new category",
                "We're pleased to announce that we added a new category \"For party\". Find it out, we're you would love that.",
                false,
            ),
            Notification(
                3,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcOTBOIhBGY2JBZKYDcjGelcx-msm7RY0wlQ&s",
                "You got discount for this week",
                "We have a good news! You got discount for this week for -10%. To apply this discount use promo code #LEARN_CARDS",
                false,
            ),
            Notification(
                4,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcOTBOIhBGY2JBZKYDcjGelcx-msm7RY0wlQ&s",
                "Your password has been changed",
                "02.11.2022 you sent a request to change your password. Your password was successfully changed, Please let us know if it was not you.",
                true,
            ),
            Notification(
                5,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcOTBOIhBGY2JBZKYDcjGelcx-msm7RY0wlQ&s",
                "Meet a new category",
                "We're pleased to announce that we added a new category \"For party\". Find it out, we're you would love that.",
                false,
            ),
            Notification(
                6,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcOTBOIhBGY2JBZKYDcjGelcx-msm7RY0wlQ&s",
                "You got discount for this week",
                "We have a good news! You got discount for this week for -10%. To apply this discount use promo code #LEARN_CARDS",
                false,
            )
        )
    }

    fun loadMockProducts(): List<Product> {
        return listOf(
            Product(
                1,
                "Product 1",
                39.99,
                "https://as2.ftcdn.net/v2/jpg/02/11/11/15/1000_F_211111574_VLtzH6ORhebXvnJXjlkAkaUuAftnvmJH.jpg",
                false
            ),
            Product(
                2,
                "Product 2",
                39.99,
                "https://as1.ftcdn.net/v2/jpg/02/60/22/68/1000_F_260226888_aZ3EJOTSE2ly5TlRuseWexnVkK67KqBl.jpg",
                true
            ),
            Product(
                3,
                "Product 3",
                52.85,
                "https://as1.ftcdn.net/v2/jpg/03/14/89/16/1000_F_314891647_CVY0iUdvxyJ2D6RfP6jNvktTWYdNvy6y.jpg",
                false
            ),
            Product(
                4,
                "Product 4",
                74.42,
                "https://as1.ftcdn.net/v2/jpg/02/64/98/34/1000_F_264983445_wXVAKMmZe0Q1jJN6R6riVJsajb7fGvx3.jpg",
                true
            ),
            Product(
                5,
                "Product 5",
                95.21,
                "https://as2.ftcdn.net/v2/jpg/02/11/11/15/1000_F_211111574_VLtzH6ORhebXvnJXjlkAkaUuAftnvmJH.jpg",
                false
            ),
            Product(
                6,
                "Product 6",
                11.11,
                "https://as1.ftcdn.net/v2/jpg/02/60/22/68/1000_F_260226888_aZ3EJOTSE2ly5TlRuseWexnVkK67KqBl.jpg",
                true
            ),
            Product(
                7,
                "Product 7",
                22.22,
                "https://as1.ftcdn.net/v2/jpg/02/60/22/68/1000_F_260226888_aZ3EJOTSE2ly5TlRuseWexnVkK67KqBl.jpg",
                false
            ),
            Product(
                8,
                "Product 8",
                33.33,
                "https://as1.ftcdn.net/v2/jpg/03/14/89/16/1000_F_314891647_CVY0iUdvxyJ2D6RfP6jNvktTWYdNvy6y.jpg",
                true
            ),
            Product(
                9,
                "Product 9",
                44.44,
                "https://as1.ftcdn.net/v2/jpg/03/14/89/16/1000_F_314891647_CVY0iUdvxyJ2D6RfP6jNvktTWYdNvy6y.jpg",
                false
            ),
            Product(
                10,
                "Product 10",
                55.55,
                "https://as1.ftcdn.net/v2/jpg/02/64/98/34/1000_F_264983445_wXVAKMmZe0Q1jJN6R6riVJsajb7fGvx3.jpg",
                true
            ),
        )
    }

    fun loadMockProductsDetails(): List<ProductDetails> {
        return listOf(
            ProductDetails(
                id = 1,
                title = "Product 1",
                description = LOREM_IPSUM_SOURCE,
                category = "Sneakers",
                price = 39.99,
                imageUrls = listOf(
                    "https://as2.ftcdn.net/v2/jpg/02/11/11/15/1000_F_211111574_VLtzH6ORhebXvnJXjlkAkaUuAftnvmJH.jpg",
                    "https://as2.ftcdn.net/v2/jpg/02/11/11/15/1000_F_211111574_VLtzH6ORhebXvnJXjlkAkaUuAftnvmJH.jpg",
                    "https://as2.ftcdn.net/v2/jpg/02/11/11/15/1000_F_211111574_VLtzH6ORhebXvnJXjlkAkaUuAftnvmJH.jpg",
                ),
                isFavourite = false
            ),
            ProductDetails(
                id = 2,
                title = "Product 2",
                description = LOREM_IPSUM_SOURCE,
                category = "Sneakers",
                price = 39.99,
                imageUrls = listOf(
                    "https://as1.ftcdn.net/v2/jpg/02/60/22/68/1000_F_260226888_aZ3EJOTSE2ly5TlRuseWexnVkK67KqBl.jpg",
                    "https://as1.ftcdn.net/v2/jpg/02/60/22/68/1000_F_260226888_aZ3EJOTSE2ly5TlRuseWexnVkK67KqBl.jpg",
                    "https://as1.ftcdn.net/v2/jpg/02/60/22/68/1000_F_260226888_aZ3EJOTSE2ly5TlRuseWexnVkK67KqBl.jpg",
                ),
                isFavourite = true
            ),
            ProductDetails(
                id = 3,
                title = "Product 3",
                description = LOREM_IPSUM_SOURCE,
                category = "Sneakers",
                price = 52.85,
                imageUrls = listOf(
                    "https://as1.ftcdn.net/v2/jpg/01/03/97/52/1000_F_103975260_sOa1Cq2QNhenLiovsuFo39qRZbi0eXZM.jpg",
                    "https://as1.ftcdn.net/v2/jpg/01/03/97/52/1000_F_103975260_sOa1Cq2QNhenLiovsuFo39qRZbi0eXZM.jpg",
                    "https://as1.ftcdn.net/v2/jpg/01/03/97/52/1000_F_103975260_sOa1Cq2QNhenLiovsuFo39qRZbi0eXZM.jpg",
                ),
                isFavourite = false
            ),
            ProductDetails(
                id = 4,
                title = "Product 4",
                description = LOREM_IPSUM_SOURCE,
                category = "Sneakers",
                price = 74.42,
                imageUrls = listOf(
                    "https://as1.ftcdn.net/v2/jpg/03/14/89/16/1000_F_314891647_CVY0iUdvxyJ2D6RfP6jNvktTWYdNvy6y.jpg",
                    "https://as1.ftcdn.net/v2/jpg/03/14/89/16/1000_F_314891647_CVY0iUdvxyJ2D6RfP6jNvktTWYdNvy6y.jpg",
                    "https://as1.ftcdn.net/v2/jpg/03/14/89/16/1000_F_314891647_CVY0iUdvxyJ2D6RfP6jNvktTWYdNvy6y.jpg",
                ),
                isFavourite = true
            ),
            ProductDetails(
                id = 5,
                title = "Product 5",
                description = LOREM_IPSUM_SOURCE,
                category = "Sneakers",
                price = 95.21,
                imageUrls = listOf(
                    "https://as1.ftcdn.net/v2/jpg/03/14/89/16/1000_F_314891647_CVY0iUdvxyJ2D6RfP6jNvktTWYdNvy6y.jpg",
                    "https://as1.ftcdn.net/v2/jpg/03/14/89/16/1000_F_314891647_CVY0iUdvxyJ2D6RfP6jNvktTWYdNvy6y.jpg",
                    "https://as1.ftcdn.net/v2/jpg/03/14/89/16/1000_F_314891647_CVY0iUdvxyJ2D6RfP6jNvktTWYdNvy6y.jpg",
                ),
                isFavourite = false
            ),
            ProductDetails(
                id = 6,
                title = "Product 6",
                description = LOREM_IPSUM_SOURCE,
                category = "Sneakers",
                price = 11.11,
                imageUrls = listOf(
                    "https://as1.ftcdn.net/v2/jpg/02/60/22/68/1000_F_260226888_aZ3EJOTSE2ly5TlRuseWexnVkK67KqBl.jpg",
                    "https://as1.ftcdn.net/v2/jpg/02/60/22/68/1000_F_260226888_aZ3EJOTSE2ly5TlRuseWexnVkK67KqBl.jpg",
                    "https://as1.ftcdn.net/v2/jpg/02/60/22/68/1000_F_260226888_aZ3EJOTSE2ly5TlRuseWexnVkK67KqBl.jpg",
                ),
                isFavourite = true
            ),
            ProductDetails(
                id = 7,
                title = "Product 7",
                description = LOREM_IPSUM_SOURCE,
                category = "Sneakers",
                price = 22.22,
                imageUrls = listOf(
                    "https://as1.ftcdn.net/v2/jpg/02/60/22/68/1000_F_260226888_aZ3EJOTSE2ly5TlRuseWexnVkK67KqBl.jpg",
                    "https://as1.ftcdn.net/v2/jpg/02/60/22/68/1000_F_260226888_aZ3EJOTSE2ly5TlRuseWexnVkK67KqBl.jpg",
                    "https://as1.ftcdn.net/v2/jpg/02/60/22/68/1000_F_260226888_aZ3EJOTSE2ly5TlRuseWexnVkK67KqBl.jpg",
                ),
                isFavourite = false
            ),
            ProductDetails(
                id = 8,
                title = "Product 8",
                description = LOREM_IPSUM_SOURCE,
                category = "Sneakers",
                price = 33.33,
                imageUrls = listOf(
                    "https://as1.ftcdn.net/v2/jpg/02/60/22/68/1000_F_260226888_aZ3EJOTSE2ly5TlRuseWexnVkK67KqBl.jpg",
                    "https://as1.ftcdn.net/v2/jpg/02/60/22/68/1000_F_260226888_aZ3EJOTSE2ly5TlRuseWexnVkK67KqBl.jpg",
                    "https://as1.ftcdn.net/v2/jpg/02/60/22/68/1000_F_260226888_aZ3EJOTSE2ly5TlRuseWexnVkK67KqBl.jpg",
                ),
                isFavourite = true
            ),
            ProductDetails(
                id = 9,
                title = "Product 9",
                description = LOREM_IPSUM_SOURCE,
                category = "Sneakers",
                price = 44.44,
                imageUrls = listOf(
                    "https://as1.ftcdn.net/v2/jpg/02/60/22/68/1000_F_260226888_aZ3EJOTSE2ly5TlRuseWexnVkK67KqBl.jpg",
                    "https://as1.ftcdn.net/v2/jpg/02/60/22/68/1000_F_260226888_aZ3EJOTSE2ly5TlRuseWexnVkK67KqBl.jpg",
                    "https://as1.ftcdn.net/v2/jpg/02/60/22/68/1000_F_260226888_aZ3EJOTSE2ly5TlRuseWexnVkK67KqBl.jpg",
                ),
                isFavourite = false
            ),
            ProductDetails(
                id = 10,
                title = "Product 10",
                description = LOREM_IPSUM_SOURCE,
                category = "Sneakers",
                price = 55.55,
                imageUrls = listOf(
                    "https://as1.ftcdn.net/v2/jpg/02/60/22/68/1000_F_260226888_aZ3EJOTSE2ly5TlRuseWexnVkK67KqBl.jpg",
                    "https://as1.ftcdn.net/v2/jpg/02/60/22/68/1000_F_260226888_aZ3EJOTSE2ly5TlRuseWexnVkK67KqBl.jpg",
                    "https://as1.ftcdn.net/v2/jpg/02/60/22/68/1000_F_260226888_aZ3EJOTSE2ly5TlRuseWexnVkK67KqBl.jpg",
                ),
                isFavourite = true
            ),
        )
    }

    fun loadMockSearchCategories(): List<String> {
        return listOf("All", "Top", "Price", "New")
    }

    fun loadMockProductDetails(id: Long): ProductDetails {
        return loadMockProductsDetails().first { it.id == id }
    }

    fun loadMockCart(): List<ProductCart> {
        return listOf(
            ProductCart(
                id = 5,
                title = "Product 5",
                price = 95.21,
                imageUrl = "https://as2.ftcdn.net/v2/jpg/02/11/11/15/1000_F_211111574_VLtzH6ORhebXvnJXjlkAkaUuAftnvmJH.jpg",
                category = "Sneakers",
                quantity = 1
            ),
            ProductCart(
                id = 6,
                title = "Product 6",
                price = 95.21,
                imageUrl = "https://as1.ftcdn.net/v2/jpg/02/60/22/68/1000_F_260226888_aZ3EJOTSE2ly5TlRuseWexnVkK67KqBl.jpg",
                category = "Sneakers",
                quantity = 1
            ),
            ProductCart(
                id = 2,
                title = "Product 2",
                price = 95.21,
                imageUrl = "https://as1.ftcdn.net/v2/jpg/03/14/89/16/1000_F_314891647_CVY0iUdvxyJ2D6RfP6jNvktTWYdNvy6y.jpg",
                category = "Sneakers",
                quantity = 1
            ),
            ProductCart(
                id = 7,
                title = "Product 7",
                price = 95.21,
                imageUrl = "https://as1.ftcdn.net/v2/jpg/03/14/89/16/1000_F_314891647_CVY0iUdvxyJ2D6RfP6jNvktTWYdNvy6y.jpg",
                category = "Sneakers",
                quantity = 1
            ),
            ProductCart(
                id = 4,
                title = "Product 5",
                price = 95.21,
                imageUrl = "https://as1.ftcdn.net/v2/jpg/02/64/98/34/1000_F_264983445_wXVAKMmZe0Q1jJN6R6riVJsajb7fGvx3.jpg",
                category = "Sneakers",
                quantity = 1
            ),
        )
    }

    fun isProductInCart(id: Long): Boolean {
        return loadMockCart().map { it.id }.contains(id)
    }

    fun loadMockFavourites(): List<ProductDetails> {
        return listOf(
            ProductDetails(
                id = 1,
                title = "Product 1",
                description = LOREM_IPSUM_SOURCE,
                category = "Sneakers",
                price = 39.99,
                imageUrls = listOf(
                    "https://as2.ftcdn.net/v2/jpg/02/11/11/15/1000_F_211111574_VLtzH6ORhebXvnJXjlkAkaUuAftnvmJH.jpg",
                    "https://as2.ftcdn.net/v2/jpg/02/11/11/15/1000_F_211111574_VLtzH6ORhebXvnJXjlkAkaUuAftnvmJH.jpg",
                    "https://as2.ftcdn.net/v2/jpg/02/11/11/15/1000_F_211111574_VLtzH6ORhebXvnJXjlkAkaUuAftnvmJH.jpg",
                ),
                isFavourite = true
            ),
            ProductDetails(
                id = 2,
                title = "Product 2",
                description = LOREM_IPSUM_SOURCE,
                category = "Sneakers",
                price = 39.99,
                imageUrls = listOf(
                    "https://as1.ftcdn.net/v2/jpg/02/60/22/68/1000_F_260226888_aZ3EJOTSE2ly5TlRuseWexnVkK67KqBl.jpg",
                    "https://as1.ftcdn.net/v2/jpg/02/60/22/68/1000_F_260226888_aZ3EJOTSE2ly5TlRuseWexnVkK67KqBl.jpg",
                    "https://as1.ftcdn.net/v2/jpg/02/60/22/68/1000_F_260226888_aZ3EJOTSE2ly5TlRuseWexnVkK67KqBl.jpg",
                ),
                isFavourite = true
            ),
            ProductDetails(
                id = 3,
                title = "Product 3",
                description = LOREM_IPSUM_SOURCE,
                category = "Sneakers",
                price = 52.85,
                imageUrls = listOf(
                    "https://as1.ftcdn.net/v2/jpg/01/03/97/52/1000_F_103975260_sOa1Cq2QNhenLiovsuFo39qRZbi0eXZM.jpg",
                    "https://as1.ftcdn.net/v2/jpg/01/03/97/52/1000_F_103975260_sOa1Cq2QNhenLiovsuFo39qRZbi0eXZM.jpg",
                    "https://as1.ftcdn.net/v2/jpg/01/03/97/52/1000_F_103975260_sOa1Cq2QNhenLiovsuFo39qRZbi0eXZM.jpg",
                ),
                isFavourite = true
            ),
            ProductDetails(
                id = 10,
                title = "Product 10",
                description = LOREM_IPSUM_SOURCE,
                category = "Sneakers",
                price = 55.55,
                imageUrls = listOf(
                    "https://as1.ftcdn.net/v2/jpg/01/03/97/52/1000_F_103975260_sOa1Cq2QNhenLiovsuFo39qRZbi0eXZM.jpg",
                    "https://as1.ftcdn.net/v2/jpg/01/03/97/52/1000_F_103975260_sOa1Cq2QNhenLiovsuFo39qRZbi0eXZM.jpg",
                    "https://as1.ftcdn.net/v2/jpg/01/03/97/52/1000_F_103975260_sOa1Cq2QNhenLiovsuFo39qRZbi0eXZM.jpg",
                ),
                isFavourite = true
            ),
            ProductDetails(
                id = 4,
                title = "Product 4",
                description = LOREM_IPSUM_SOURCE,
                category = "Sneakers",
                price = 74.42,
                imageUrls = listOf(
                    "https://as1.ftcdn.net/v2/jpg/01/03/97/52/1000_F_103975260_sOa1Cq2QNhenLiovsuFo39qRZbi0eXZM.jpg",
                    "https://as1.ftcdn.net/v2/jpg/01/03/97/52/1000_F_103975260_sOa1Cq2QNhenLiovsuFo39qRZbi0eXZM.jpg",
                    "https://as1.ftcdn.net/v2/jpg/01/03/97/52/1000_F_103975260_sOa1Cq2QNhenLiovsuFo39qRZbi0eXZM.jpg",
                ),
                isFavourite = true
            ),
            ProductDetails(
                id = 8,
                title = "Product 8",
                description = LOREM_IPSUM_SOURCE,
                category = "Sneakers",
                price = 33.33,
                imageUrls = listOf(
                    "https://as1.ftcdn.net/v2/jpg/01/03/97/52/1000_F_103975260_sOa1Cq2QNhenLiovsuFo39qRZbi0eXZM.jpg",
                    "https://as1.ftcdn.net/v2/jpg/01/03/97/52/1000_F_103975260_sOa1Cq2QNhenLiovsuFo39qRZbi0eXZM.jpg",
                    "https://as1.ftcdn.net/v2/jpg/01/03/97/52/1000_F_103975260_sOa1Cq2QNhenLiovsuFo39qRZbi0eXZM.jpg",
                ),
                isFavourite = true
            ),
            ProductDetails(
                id = 9,
                title = "Product 9",
                description = LOREM_IPSUM_SOURCE,
                category = "Sneakers",
                price = 44.44,
                imageUrls = listOf(
                    "https://as1.ftcdn.net/v2/jpg/01/03/97/52/1000_F_103975260_sOa1Cq2QNhenLiovsuFo39qRZbi0eXZM.jpg",
                    "https://as1.ftcdn.net/v2/jpg/01/03/97/52/1000_F_103975260_sOa1Cq2QNhenLiovsuFo39qRZbi0eXZM.jpg",
                    "https://as1.ftcdn.net/v2/jpg/01/03/97/52/1000_F_103975260_sOa1Cq2QNhenLiovsuFo39qRZbi0eXZM.jpg",
                ),
                isFavourite = true
            ),
        )
    }

    fun loadMockProductPrices(): ProductPrices {
        return ProductPrices(
            subtotal = 119.97,
            shipping = 3.99,
            discount = 23.99,
            total = 99.97
        )
    }

    fun loadMockAddresses(): List<DeliveryAddress> {
        return listOf(
            DeliveryAddress(
                id = 1,
                name = "Home",
                address = "Kyiv, Ukraine, 02000",
                isSelected = true
            ),
            DeliveryAddress(
                id = 2,
                name = "Work",
                address = "Lviv, Ukraine, 03559",
                isSelected = false
            ),
        )
    }

    fun loadMockPaymentMethods(): List<PaymentMethod> {
        return listOf(
            PaymentMethod(
                id = 1,
                name = "Visa",
                number = "**** **** **** 1234",
                isSelected = true
            ),
            PaymentMethod(
                id = 2,
                name = "MasterCard",
                number = "**** **** **** 5678",
                isSelected = false
            )
        )
    }

    fun loadMockUser(): User = User(
        id = 1,
        name = "Jack",
        surname = "London",
        email = "jack.london@gmail.com",
        urlImage = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRDzn36DbfZjqNe6vVsjGJidTdAaMbTWqC6ug&s"
    )
}