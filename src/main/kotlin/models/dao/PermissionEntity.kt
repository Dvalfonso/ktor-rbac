package com.dvalfonso.models.dao

import com.dvalfonso.models.PermissionsTable
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class PermissionEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<PermissionEntity>(PermissionsTable)
    var name by PermissionsTable.name
}