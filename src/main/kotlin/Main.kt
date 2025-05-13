package netology

fun main() {

}

data class Post (
    val postId: Int = 0,
    val ownerId: Int,
    val fromId: Int,
    val date: Int,
    val text: String,
    val postType : String,
    val friendsOnly: Boolean = false,
    val canDelete : Boolean = false,
    val canEdit: Boolean = false,
    val markedAsAds: Boolean = false,
    val likes: Likes = Likes()
)

data class Likes(
    val count: Int = 0,
    val userLikes: Boolean = false,
    val canLik: Boolean = true,
    val canPublish: Boolean = true
)

object WallService {

    private var posts = emptyArray<Post>()
    private var nextId = 1

    fun add(post: Post): Post {
        val newPost = post.copy(postId = nextId++)
        posts += newPost
        return newPost
    }

    fun update(post: Post): Boolean {
        for((index, existingPost) in posts.withIndex())
            if (existingPost.postId == post.postId) {
                posts[index] = post
                return true
            }

        return false
    }

    fun clear() {
        posts = emptyArray()
        nextId = 1
    }
}