package `in`.zippr.surveyx2.dependencyinjection.components

import `in`.zippr.surveyx2.dependencyinjection.modules.RetrofitModule
import `in`.zippr.surveyx2.dependencyinjection.scopes.RepositoryScope
import `in`.zippr.surveyx2.repositories.BaseRepo
import `in`.zippr.surveyx2.webservices.APICall
import dagger.Component
import dagger.Subcomponent

@Subcomponent(
//    dependencies = arrayOf(AppComponent::class),
    modules = [RetrofitModule::class])
@RepositoryScope
interface RetrofitComponent {
    abstract fun inject(baseRepo: BaseRepo)

    fun getApiCalls(): APICall
}