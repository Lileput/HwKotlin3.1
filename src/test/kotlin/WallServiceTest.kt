import netology.Comment
import netology.Post
import netology.PostNotFoundException
import netology.WallService
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class WallServiceTest {
    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun add() {
        val service = WallService
        val post =
            Post(ownerId = 1, fromId = 1, date = 13052025, text = "First post", postType = "post", attachments = null)

        val addedPost = service.add(post)

        assertNotEquals(0, addedPost.postId)
    }

    @Test
    fun updateIsTrue() {
        val service = WallService
        val post1 =
            Post(ownerId = 1, fromId = 1, date = 13052025, text = "Second post", postType = "post", attachments = null)
        val addedPost = service.add(post1)
        val updatePost = addedPost.copy(text = "Update post")
        val result = service.update(updatePost)

        assertEquals(true, result)
    }

    @Test
    fun updateIsFalse() {
        val service = WallService
        val post = Post(
            postId = 200,
            ownerId = 1,
            fromId = 1,
            date = 13052025,
            text = "Second post",
            postType = "post",
            attachments = null
        )

        val result = service.update(post)

        assertEquals(false, result)
    }

    @Test
    fun addCommentIsTrue() {
        val post1 =
            Post(
                ownerId = 1,
                fromId = 1,
                date = 13052025,
                text = "Second post",
                postType = "post",
                attachments = null
            )
        val service = WallService
        service.add(post1)
        val comment1 = Comment(0, 20, 1, "Первый коммент")
        val addedComment = service.createComment(1, comment1)

        assertEquals(1, addedComment.id)
        assertEquals("Первый коммент", addedComment.text)
    }

    @Test(expected = PostNotFoundException::class)
    fun addCommentIsException() {
        val post1 =
            Post(
                ownerId = 1,
                fromId = 1,
                date = 13052025,
                text = "Second post",
                postType = "post",
                attachments = null
            )
        val service = WallService
        service.add(post1)
        val comment1 = Comment(0, 20, 1, "Первый коммент")
        val addedComment = service.createComment(20, comment1)

    }
}