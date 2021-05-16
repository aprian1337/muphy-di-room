package com.aprian1337.movie_catalogue.utils

import com.aprian1337.movie_catalogue.data.local.FavoriteEntity
import com.aprian1337.movie_catalogue.data.models.DetailMovieTv
import com.aprian1337.movie_catalogue.data.models.MovieTv
import com.aprian1337.movie_catalogue.data.network.response.*

object DummyData {
    val movies = listOf(
        MovieTv(
            460465,
            "Mortal Kombat",
            listOf(28, 14, 12, 878),
            "2021-04-07",
            "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
            "/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
            "7.8"
        )
    )

    val tvShow = listOf(
        MovieTv(
            95557,
            "Invincible",
            listOf(16, 10759, 18, 10765),
            "2021-03-19",
            "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
            "/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
            "8.9"
        )
    )

    val detailData = DetailMovieTv(
        1,
        "Manusia Hebat",
        "Comedy",
        "11/1/1112",
        "lorem ipsum",
        "/diasjdoiasjd.jpg",
        "9.9"
    )

    val genres = listOf(
        GenresItem(
            "Action",
            28
        )
    )

    val moviesResponse =
        listOf(
            MoviesResponse(
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "en",
                "Mortal Kombat",
                false,
                "Mortal Kombat",
                listOf(28, 14, 12, 878),
                "/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
                "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                "2021-04-07",
                4870.197,
                7.8,
                460465,
                false,
                2145
            )
        )

    val tvShowsResponse = listOf(
        TvShowsResponse(
            "/6UH52Fmau8RPsMAbQbjwN3wJSCj.jpg",
            "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
            "en",
            listOf(16, 10579, 18),
            "/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
            listOf("US"),
            "/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
            "Invincible",
            1744.687,
            8.9,
            "Invincible",
            95557,
            1416,
        )
    )

    val detailTvShowResponse = DetailTvShowsResponse(
        listOf(
            GenresItem("Documentary", 99)
        ),
        15550,
        "2008-01-03",
        "So what actually happens after you drink a glass of water? How does it come out the other end?\\n\\nWe follow liquid through peristalsis all the way to the bladder. We see a remarkable X-Ray of a bladder emptying and amazing footage of the urinary tract. Adam visits a work site to show a Urine Colour Chart - the chart is used so you can tell if you're dehydrated by the colour of your wee.\\n\\nWe demythologise the must-drink-eight-glasses-of-water-a-day belief and discuss the existence of embedded water in food we eat. We also explore the myths that you should wee on a jellyfish sting and that there is a burgeoning market for stolen kidneys. We discuss breaking the seal and stopping midstream, exploding kidneys and how much elephants can wee.\\n\\nKarl claims men have to splatter, and that the same reason men splatter is the same basis for the technology that creates ink jet printing. Ruben uses drain cleaner to make flammable bubbles, it's just up to Adam to set them alight before they blow away. Adam explains why some people's wee smells of asparagus while others doesn't.\\n\\nA group of Australia's premier performers, including Deni Hines, Iva Davies and The Chaser's Andrew Hansen and Chris Taylor collaborate to produce a moving tribute to micturation 'Wee Across the World'.",
        "/kMvN5R8g6L0SY5r9YZw9foYGQr0.png",
        "Sleek Geeks",
        5.2,
    )

    val detailTvShow = DetailMovieTv(
        15550,
        "Sleek Geeks",
        "Documentary",
        "2008-01-03",
        "So what actually happens after you drink a glass of water? How does it come out the other end?\\n\\nWe follow liquid through peristalsis all the way to the bladder. We see a remarkable X-Ray of a bladder emptying and amazing footage of the urinary tract. Adam visits a work site to show a Urine Colour Chart - the chart is used so you can tell if you're dehydrated by the colour of your wee.\\n\\nWe demythologise the must-drink-eight-glasses-of-water-a-day belief and discuss the existence of embedded water in food we eat. We also explore the myths that you should wee on a jellyfish sting and that there is a burgeoning market for stolen kidneys. We discuss breaking the seal and stopping midstream, exploding kidneys and how much elephants can wee.\\n\\nKarl claims men have to splatter, and that the same reason men splatter is the same basis for the technology that creates ink jet printing. Ruben uses drain cleaner to make flammable bubbles, it's just up to Adam to set them alight before they blow away. Adam explains why some people's wee smells of asparagus while others doesn't.\\n\\nA group of Australia's premier performers, including Deni Hines, Iva Davies and The Chaser's Andrew Hansen and Chris Taylor collaborate to produce a moving tribute to micturation 'Wee Across the World'.",
        "/kMvN5R8g6L0SY5r9YZw9foYGQr0.png",
        "5.2",
    )

    val detailMovieResponse = DetailMoviesResponse(
        "The Village Barbershop",
        listOf(
            GenresItem("Comedy", 35),
            GenresItem("Drama", 18)
        ),
        15911,
        "A fading smalltime barber is forced to hire the last person on earth he'd want working in his shop - a woman.",
        "The Village Barbershop",
        "/hGSIprpDM2Udi8Jic3nnMZhbNiD.jpg",
        "2008-01-01",
        7.5,
    )

    val detailMovie = DetailMovieTv(
        15911,
        "The Village Barbershop",
        "Comedy, Drama",
        "2008-01-01",
        "A fading smalltime barber is forced to hire the last person on earth he'd want working in his shop - a woman.",
        "/hGSIprpDM2Udi8Jic3nnMZhbNiD.jpg",
        "7.5",
    )

    val genreResponse = listOf(
        GenresItem("Comedy", 35),
        GenresItem("Drama", 18),
        GenresItem("Documentary", 99),
        GenresItem("Animation", 16)
    )

    val favorite = listOf(
        FavoriteEntity(
            1,
            "Batman",
            "Action",
            "8.9",
            "/dsiajdoiasdjioas.jpg",
            "1/1/2020",
            "MOVIES",
        ),
        FavoriteEntity(
            1,
            "Batman Series",
            "Action",
            "8.2",
            "/dsiasdazq.jpg",
            "1/1/2021",
            "TVSHOWS",
        ),
    )

}