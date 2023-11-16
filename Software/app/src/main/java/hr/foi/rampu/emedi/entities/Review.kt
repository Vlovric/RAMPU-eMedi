data class Review(
    val grade: Int,
    val description: String,
    var doctor: Doctor? = null
) {
    companion object {
        private val reviews: MutableList<Review> = mutableListOf()

        fun addReview(review: Review) {
            reviews.add(review)
        }

        fun getReviewsForDoctor(doctor: Doctor): List<Review> {
            return reviews.filter { it.doctor == doctor }
        }
    }
}
