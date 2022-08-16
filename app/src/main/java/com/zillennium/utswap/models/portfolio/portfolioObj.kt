package com.zillennium.utswap.models.portfolio

/**
 * Created by Sokheng Chhoeurn on 15/8/22.
 * Build in Mac
 */
object PortfolioGraphObj {
    class portfolioGraphRes{
        var status:Int? = null
        var message:String? = null
        var data:List<portfolioData>? = null
    }
    class portfolioData{
        var x:Double? =null
        var y:String? =null
    }
}