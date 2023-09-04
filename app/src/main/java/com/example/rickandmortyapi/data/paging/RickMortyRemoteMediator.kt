package com.example.rickandmortyapi.data.paging

//import androidx.paging.ExperimentalPagingApi
//import androidx.paging.LoadType
//import androidx.paging.PagingState
//import androidx.paging.RemoteMediator
//import androidx.room.withTransaction
//import com.example.rickandmortyapi.data.local.RickAndMortyDataBase
//import com.example.rickandmortyapi.data.local.model.CharacterEntity
//import com.example.rickandmortyapi.data.model.RemoteKeys
//import com.example.rickandmortyapi.data.remote.ApiService
//import retrofit2.HttpException
//import java.io.IOException
//import javax.inject.Inject
//
//@OptIn(ExperimentalPagingApi::class)
//class RickMortyRemoteMediator @Inject constructor(
//    private val dataBase: RickAndMortyDataBase,
//    private val apiService: ApiService
//) :RemoteMediator<Int, CharacterEntity> (){
//
//    private val rickMortyDao = dataBase.rickMortyDao()
//    private val remoteKeysDao = dataBase.remoteKeysDao()
//
//    override suspend fun load(
//        loadType: LoadType,
//        state: PagingState<Int, CharacterEntity>
//    ): MediatorResult {
//        return try {
//            val currPage = when(loadType){
//                LoadType.REFRESH -> {
//                    val remoteKeys = getRemoteKeyCloseToCurrPosition(state)
//                    remoteKeys?.nextPage?.minus(1)?:1
//                }
//                LoadType.PREPEND -> {
//                    val remoteKeys = getRemoteKeyForFirstItem(state)
//                    val prevPage = remoteKeys?.prevPage
//                        ?:return MediatorResult.Success(
//                            endOfPaginationReached = remoteKeys != null
//                        )
//                    prevPage
//                }
//                LoadType.APPEND ->{
//                    val remoteKeys = getRemoteKeyForLastItem(state)
//                    val nextPage = remoteKeys?.nextPage
//                        ?:return MediatorResult.Success(
//                            endOfPaginationReached = remoteKeys!=null
//                        )
//                    nextPage
//                }
//            }
//            val responce = apiService.getAllCharacters(page = currPage, pageCount =  10)
//            val endOfPagingReached = responce.isEmpty()
//
//            val prevPage = if (currPage==1) null else currPage -1
//            val nextPage = if (endOfPagingReached) null else currPage +1
//
//            dataBase.withTransaction {
//                if (loadType == LoadType.REFRESH){
//                    rickMortyDao.deleteCharacters()
//                    remoteKeysDao.deleteAllRemoteKeys()
//                }
//                val keys = responce.map { characterEntity ->
//                    RemoteKeys(
//                        id = characterEntity.id,
//                        prevPage = prevPage,
//                        nextPage = nextPage
//                    )
//                }
//                remoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
//                rickMortyDao.addCharacters(charactersEntity = responce)
//            }
//            MediatorResult.Success(endOfPaginationReached = endOfPagingReached)
//
////            val loadKey = when(loadType){
////                LoadType.REFRESH ->1
////                LoadType.PREPEND ->return  MediatorResult.Success(
////                    endOfPaginationReached = true
////                )
////                LoadType.APPEND -> {
////                    val lastItem = state.lastItemOrNull()
////                    if (lastItem==null){
////                        1
////                    }else{
////                        (lastItem.id / state.config.pageSize)+1
////                    }
////                }
////            }
////            delay(2000L)
////            val characters = apiService.getAllCharacters(
////                page = loadKey,
////                pageCount = state.config.pageSize
////            )
////            dataBase.withTransaction {
////                if (loadType==LoadType.REFRESH){
////                    dataBase.dao.clearAll()
////                }
////                val characterEntities =characters.map {it.toCharacterEntity()}
////                dataBase.dao.upsertAll(characterEntities)
////            }
////            MediatorResult.Success(endOfPaginationReached = characters.isEmpty())
//
//        }catch (e:IOException){
//            MediatorResult.Error(e)
//        }catch (e:HttpException){
//            MediatorResult.Error(e)
//        }
//    }
//
//    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, CharacterEntity>): RemoteKeys?
//    =state.pages.lastOrNull(){it.data.isNotEmpty()}?.data?.lastOrNull()
//        ?.let { characterEntity ->
//            remoteKeysDao.getRemoteKeys(id = characterEntity.id)
//        }
//
//    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, CharacterEntity>): RemoteKeys?
//    =state.pages.firstOrNull(){ it.data.isNullOrEmpty()}?.data?.firstOrNull()
//        ?.let { characterEntity ->
//            remoteKeysDao.getRemoteKeys(id = characterEntity.id)
//        }
//
//    private suspend fun getRemoteKeyCloseToCurrPosition(state: PagingState<Int, CharacterEntity>): RemoteKeys?
//    = state.anchorPosition?.let { position ->
//        state.closestItemToPosition(position)?.id?.let { id ->
//            remoteKeysDao.getRemoteKeys(id =id)
//        }
//    }
//}