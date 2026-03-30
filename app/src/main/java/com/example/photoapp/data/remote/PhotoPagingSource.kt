package com.example.photoapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.photoapp.model.Photo

class PhotoPagingSource(
    private val api: ApiService
) : PagingSource<Int, Photo>() {

    private val MAX_PAGE = 10

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo>{
        val page = params.key ?: 1

        return try {

            if(page > MAX_PAGE){
                return LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }

            val response = api.getPhotos(page, 5)

            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if(page == MAX_PAGE) null else page + 1
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let{ position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }
}