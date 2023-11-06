package hr.foi.rampu.emedi.entities

data class Review(
    val grade: Int,
    val description: String
) {
    companion object {
        private val reviews: MutableList<Review> = mutableListOf()

        fun addReview(review: Review) {
            reviews.add(review)
        }

        fun getAllReviews(): List<Review> {
            return reviews
        }
    }
}