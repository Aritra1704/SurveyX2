package `in`.zippr.surveyx2.models.response

import `in`.zippr.surveyx2.models.data.BaseDO

data class UserResponse(val _id: String,
                        val first_name: String,
                        val user_type: String,
                        val username: String,
                        val x_zippr_sessiontoken: String,
                        val project_ids: ArrayList<String>,
                        val project_style_ids: ArrayList<Project_Style_Id>): BaseDO() {
}

data class Project_Style_Id(val project_name: String,
                            val road_map_style_id: String,
                            val street_map_style_id: String): BaseDO()