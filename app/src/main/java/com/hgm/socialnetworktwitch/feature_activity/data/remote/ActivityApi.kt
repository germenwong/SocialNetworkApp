package com.hgm.socialnetworktwitch.feature_activity.data.remote

import com.hgm.socialnetworktwitch.feature_activity.data.dto.ActivityDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ActivityApi {

      @GET("/api/activity/get")
      suspend fun getActivities(
            @Query("page") page:Int,
            @Query("pageSize") pageSize:Int,
      ):List<ActivityDto>
}