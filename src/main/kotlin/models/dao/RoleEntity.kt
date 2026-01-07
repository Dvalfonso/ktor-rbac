package com.dvalfonso.models.dao

import com.dvalfonso.models.RolePermissionsTable
import com.dvalfonso.models.RolesTable
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class RoleEntity(id: EntityID<Long>): LongEntity(id) {
    companion object : LongEntityClass<RoleEntity>(RolesTable)

    var rolename by RolesTable.name
    var permissions by PermissionEntity via RolePermissionsTable
}