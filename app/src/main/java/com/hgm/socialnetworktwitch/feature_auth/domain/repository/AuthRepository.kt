package com.hgm.socialnetworktwitch.feature_auth.domain.repository

import com.hgm.socialnetworktwitch.core.util.SimpleResource

/**
 * @auth：HGM
 * @date：2023-10-18 18:14
 * @desc：
 */
interface AuthRepository {

      suspend fun register(
            email: String,
            username: String,
            password: String
      ): SimpleResource

      suspend fun login(
            email: String,
            password: String
      ): SimpleResource

      suspend fun authenticate(): SimpleResource
}