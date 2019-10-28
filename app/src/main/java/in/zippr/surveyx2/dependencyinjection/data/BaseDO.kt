package `in`.zippr.surveyx2.dependencyinjection.data

open class BaseDO : Cloneable {
    @Throws(CloneNotSupportedException::class)
    override fun clone(): Any {
        return super.clone()
    }
}