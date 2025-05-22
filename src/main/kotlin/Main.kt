package netology

fun main() {

}

abstract class Attachment(val type: String) {

}

data class Post (
    val postId: Int = 0,
    val ownerId: Int?,
    val fromId: Int,
    val date: Int,
    val text: String?,
    val postType : String,
    val friendsOnly: Boolean = false,
    val canDelete : Boolean = false,
    val canEdit: Boolean = false,
    val markedAsAds: Boolean = false,
    val likes: Likes = Likes(),
    val attachments: Array<Attachment>?
)

data class Likes(
    val count: Int = 0,
    val userLikes: Boolean = false,
    val canLik: Boolean = true,
    val canPublish: Boolean = true
)

data class Photo(
    val id: Int,
    val albumId: Int,
    val ownerId: Int,
    val photo130: String,
    val photo604: String?
)

data class Video(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val description: String,
    val duration: Int,
    val image: String
)

data class Audio(
    val id: Int,
    val ownerId: Int,
    val artist: String,
    val title: String,
    val duration: Int
)

data class Link(
    val url: String,
    val title: String,
    val description: String
)

data class Document(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val size: Int,
    val ext: String
)

class PhotoAttachment(val photo: Photo) : Attachment("photo")
class VideoAttachment(val video: Video) : Attachment("video")
class AudioAttachment(val audio: Audio) : Attachment("audio")
class LinkAttachment(val link: Link) : Attachment("link")
class DocumentAttachment(val document: Document) : Attachment("doc")

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