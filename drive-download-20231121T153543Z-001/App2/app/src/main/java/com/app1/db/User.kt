package com.app1.db

/**
 * Created by Mohamed Fadel
 * Date: 3/19/2020.
 * email: mohamedfadel91@gmail.com.
 */
data class User(val id: Long? = -1,
                val userName: String? = null,
                val password: String? = null,
                val email: String? = null,
                val phoneNum: String? = null,
                val region: String? = null,
                val street: String? = null)
