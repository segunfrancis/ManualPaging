package com.segunfrancis.manualpaging.util

import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel

val mockEngine = MockEngine {
    respond(
        content = ByteReadChannel(mockResponse),
        status = HttpStatusCode.OK,
        headers = headersOf(HttpHeaders.ContentType, "application/json")
    )
}

val mockResponse = """
    {
      "dates": {
        "maximum": "2022-01-03",
        "minimum": "2021-11-16"
      },
      "page": 1,
      "results": [
        {
          "adult": false,
          "backdrop_path": "/1Rr5SrvHxMXHu5RjKpaMba8VTzi.jpg",
          "genre_ids": [
            28,
            12,
            878
          ],
          "id": 634649,
          "original_language": "en",
          "original_title": "Spider-Man: No Way Home",
          "overview": "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
          "popularity": 8817.063,
          "poster_path": "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
          "release_date": "2021-12-15",
          "title": "Spider-Man: No Way Home",
          "video": false,
          "vote_average": 8.4,
          "vote_count": 3427
        },
        {
          "adult": false,
          "backdrop_path": "/o76ZDm8PS9791XiuieNB93UZcRV.jpg",
          "genre_ids": [
            27,
            28,
            878
          ],
          "id": 460458,
          "original_language": "en",
          "original_title": "Resident Evil: Welcome to Raccoon City",
          "overview": "Once the booming home of pharmaceutical giant Umbrella Corporation, Raccoon City is now a dying Midwestern town. The company’s exodus left the city a wasteland…with great evil brewing below the surface. When that evil is unleashed, the townspeople are forever…changed…and a small group of survivors must work together to uncover the truth behind Umbrella and make it through the night.",
          "popularity": 7329.17,
          "poster_path": "/6WR7wLCX0PGLhj51qyvK8MIxtT5.jpg",
          "release_date": "2021-11-24",
          "title": "Resident Evil: Welcome to Raccoon City",
          "video": false,
          "vote_average": 6.1,
          "vote_count": 663
        },
        {
          "adult": false,
          "backdrop_path": "/3G1Q5xF40HkUBJXxt2DQgQzKTp5.jpg",
          "genre_ids": [
            16,
            35,
            10751,
            14
          ],
          "id": 568124,
          "original_language": "en",
          "original_title": "Encanto",
          "overview": "The tale of an extraordinary family, the Madrigals, who live hidden in the mountains of Colombia, in a magical house, in a vibrant town, in a wondrous, charmed place called an Encanto. The magic of the Encanto has blessed every child in the family with a unique gift from super strength to the power to heal—every child except one, Mirabel. But when she discovers that the magic surrounding the Encanto is in danger, Mirabel decides that she, the only ordinary Madrigal, might just be her exceptional family's last hope.",
          "popularity": 7600.359,
          "poster_path": "/4j0PNHkMr5ax3IA8tjtxcmPU3QT.jpg",
          "release_date": "2021-11-24",
          "title": "Encanto",
          "video": false,
          "vote_average": 7.8,
          "vote_count": 1888
        },
        {
          "adult": false,
          "backdrop_path": "/hv7o3VgfsairBoQFAawgaQ4cR1m.jpg",
          "genre_ids": [
            28,
            878
          ],
          "id": 624860,
          "original_language": "en",
          "original_title": "The Matrix Resurrections",
          "overview": "Plagued by strange memories, Neo's life takes an unexpected turn when he finds himself back inside the Matrix.",
          "popularity": 6917.303,
          "poster_path": "/8c4a8kE7PizaGQQnditMmI1xbRp.jpg",
          "release_date": "2021-12-16",
          "title": "The Matrix Resurrections",
          "video": false,
          "vote_average": 7.1,
          "vote_count": 1634
        },
        {
          "adult": false,
          "backdrop_path": "/vIgyYkXkg6NC2whRbYjBD7eb3Er.jpg",
          "genre_ids": [
            878,
            28,
            12
          ],
          "id": 580489,
          "original_language": "en",
          "original_title": "Venom: Let There Be Carnage",
          "overview": "After finding a host body in investigative reporter Eddie Brock, the alien symbiote must face a new enemy, Carnage, the alter ego of serial killer Cletus Kasady.",
          "popularity": 5011.79,
          "poster_path": "/rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg",
          "release_date": "2021-09-30",
          "title": "Venom: Let There Be Carnage",
          "video": false,
          "vote_average": 7.2,
          "vote_count": 5505
        },
        {
          "adult": false,
          "backdrop_path": "/1BqX34aJS5J8PefVnQSfQIEPfkl.jpg",
          "genre_ids": [
            80,
            28,
            53
          ],
          "id": 826749,
          "original_language": "en",
          "original_title": "Fortress",
          "overview": "The story revolves around a top-secret resort for retired U.S. intelligence officers. A group of criminals led by Balzary breach the compound, hellbent on revenge on Robert, forcing the retired officer and his son to save the day.",
          "popularity": 2137.569,
          "poster_path": "/m76LAg0MchIcIW9i4yXsQPGQJJF.jpg",
          "release_date": "2021-12-17",
          "title": "Fortress",
          "video": false,
          "vote_average": 6.4,
          "vote_count": 73
        }
      ],
      "total_pages": 98,
      "total_results": 1958
    }
""".trimIndent()
