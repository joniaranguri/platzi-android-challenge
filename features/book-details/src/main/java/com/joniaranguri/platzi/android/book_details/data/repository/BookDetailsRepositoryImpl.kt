package com.joniaranguri.platzi.android.book_details.data.repository

import com.joniaranguri.platzi.android.book_details.domain.model.Author
import com.joniaranguri.platzi.android.book_details.domain.model.BookDetails

val mockedDetails = BookDetails(
    description = "This is the story of the seige of Troy from the perspective of Achilles best-friend Patroclus.  Although Patroclus is outcast from his home for disappointing his father he manages to be the only mortal who can keep up with the half-God Archilles.  Even though many will know the facts behind the story the telling is fresh and engaging.This is the story of the seige of Troy from the perspective of Achilles best-friend Patroclus.  Although Patroclus is outcast from his home for disappointing his father he manages to be the only mortal who can keep up with the half-God Archilles.  Even though many will know the facts behind the story the telling is fresh and engaging.",
    alreadyRead = 1,
    currentlyReading = 2,
    wantToRead = 4,
    ratingCount = 55,
    ratingAverage = 3F,
    bookId = "sdf",
    subjects = listOf(
        "Trojan War",
        "Achilles (Greek mythology)",
        "Male friendship",
        "Fiction",
        "Thetis",
        "Greeks",
        "History"
    ),
    authors = listOf(
        Author(
            id = "sdsf",
            biography = "Madeline Miller was born in Boston and grew up in New York City and Philadelphia. She attended Brown University, where she earned her BA and MA in Classics. For the last ten years she has been teaching and tutoring Latin, Greek and Shakespeare to high school students. She also studied in the Dramaturgy department at Yale School of Drama, where she focused on the adaptation of classical texts to modern forms. She currently lives near Philadelphia, PA. The Song of Achilles is her first novel. Her second novel, Circe, will be published in April 2018. Visit her website at: www.madelinemiller.com",
            name = "Madeline Miller",
            photo = "https://covers.openlibrary.org/b/id/8283886-L.jpg"
        )/*,
        Author(
            id = "sdsf",
            biography = "Roald Dahl was a British novelist, short story writer, and screenwriter.\r\n\r\nBorn in north Cardiff, Wales, to Norwegian parents, Dahl served in the Royal Air Force during the Second World War, in which he became a flying ace and intelligence agent. He rose to prominence in the 1940s with works for both children and adults, and became one of the world's bestselling authors. His short stories are known for their unexpected endings, and his children's books for their unsentimental, often very dark humour. ([Source][1].)\r\n\r\n\r\n  [1]: http://en.wikipedia.org/wiki/Roald_Dahl",
            name = "Roald Dahl",
            photo = "https://covers.openlibrary.org/b/id/9395323-L.jpg"
        )*/
    )
)