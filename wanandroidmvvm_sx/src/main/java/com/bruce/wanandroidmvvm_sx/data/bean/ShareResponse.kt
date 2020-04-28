package com.bruce.wanandroidmvvm_sx.data.bean

import com.bruce.wanandroidmvvm_sx.data.ApiPagerResponse
import java.io.Serializable

data class ShareResponse(var coinInfo: CoinInfo,
                         var shareArticles: ApiPagerResponse<ArrayList<AriticleResponse>>
):Serializable