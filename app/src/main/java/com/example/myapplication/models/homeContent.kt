data class HomeContent(
    val suggested_questions: List<SuggestedQuestion>,
    val suggested_video:List<SuggestedVideo>
)

data class  SuggestedQuestion(
    val id: String,
    val hint_and_solution: String?,
    val is_attempted: Boolean,
    val should_load_with_html:Boolean,
    val is_bookmarked: Boolean,
    val question: String,
    val unique_question_id: String,
    val options: List<Option>
)
data class Option(
    val id: String,
    val is_correct_answer: Boolean,
    val selected: Boolean,
    val option: String,
    val slwh: Boolean,
    val user_selection_percentage: String,
)

data class SuggestedVideo(
    val id: Int,
    val is_accessible: Boolean,
    val status: String,
    val is_bookmarked: Boolean,
    val apsara_id: String?,
    val total_view_count: Int,
    val title: String,
    val video_type: String,
    val video_id: String,
    val thumbnail: String,
    val date: String,
    val order: Int,
    val is_free: Boolean,
)

