package `in`.zippr.surveyx2.models.data

open class BaseDO : Cloneable {
    @Throws(CloneNotSupportedException::class)
    override fun clone(): Any {
        return super.clone()
    }
}