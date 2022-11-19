package com.connor.splashpose.model.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    val total: Int,
    @SerialName("total_pages")
    val totalPages: Int,
    val results: List<UnsplashPhoto>
)

@Serializable
data class UnsplashPhoto(
    val id: String,
//    val created_at: String,
//    val updated_at: String? = "",
//    val width: Int,
//    val height: Int,
//    val color: String? = "#000000",
//    val views: Int? = 0,
//    val downloads: Int? = 0,
//    val likes: Int? = 0,
//    val description: String? = "The artist did not add a description...",
//    val urls: UnsplashUrls,
//    val links: UnsplashLinks,
//    val user: UnsplashUser,
//    val exif: UnsplashExif? = UnsplashExif(),
//    val location: UnsplashLocation?,
//    val tags: List<Tag>? = emptyList(),
)

//@Serializable
//data class UnsplashUrls(
//    val thumb: String?,
//    val small: String,
//    val medium: String? = "",
//    val regular: String? = "",
//    val large: String?,
//    val full: String?,
//    val raw: String?
//)
//
//@Serializable
//data class UnsplashLinks(
//    val self: String,
//    val html: String,
//    val photos: String? = "",
//    val likes: String? = "",
//    val portfolio: String? = "",
//    val download: String? = "",
//    val download_location: String? = ""
//)
//
//@Serializable
//data class UnsplashUser(
//    val id: String,
//    val username: String,
//    val name: String,
//    val portfolio_url: String? = "",
//    val bio: String? = "",
//    val location: String? = "",
//    val total_likes: Int,
//    val total_photos: Int,
//    val total_collection: Int,
//    val profile_image: UnsplashUrls,
//    val links: UnsplashLinks
//)
//
//@Serializable
//data class UnsplashExif(
//    val model: String? = "",
//    val exposure_time: String? = "",
//    val aperture: String? = "",
//    val focal_length: String? = "",
//    val iso: Int? = 0
//)
//
//@Serializable
//data class UnsplashLocation(
//    val city: String? = "",
//    val country: String? = "",
//    val position: Position? = Position(0.0, 0.0)
//)
//
//@Serializable
//data class Position(
//    val latitude: Double? = 0.0,
//    val longitude: Double? = 0.0
//)
//
//@Serializable
//data class Tag(
//    val type: String? = "",
//    val title: String? = ""
//)



