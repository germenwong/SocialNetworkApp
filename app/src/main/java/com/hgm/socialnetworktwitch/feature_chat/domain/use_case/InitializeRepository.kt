package com.hgm.socialnetworktwitch.feature_chat.domain.use_case

import com.hgm.socialnetworktwitch.feature_chat.domain.repository.ChatRepository

/**
 * @auth：HGM
 * @date：2023-11-07 17:29
 * @desc：初始化Repository
 */
class InitializeRepository(
      private val chatRepository: ChatRepository
) {
      operator fun invoke(){
            chatRepository.initialize()
      }
}