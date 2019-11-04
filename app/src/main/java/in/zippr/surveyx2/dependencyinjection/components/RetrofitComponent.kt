package `in`.zippr.surveyx2.dependencyinjection.components

import `in`.zippr.surveyx2.dependencyinjection.modules.RetrofitModule
import `in`.zippr.surveyx2.dependencyinjection.scopes.ViewModelScope
import `in`.zippr.surveyx2.repositories.BaseRepo
import `in`.zippr.surveyx2.viewmodel.BaseVM
import `in`.zippr.surveyx2.webservices.APICall
import dagger.Component

@ViewModelScope
@Component(modules = [RetrofitModule::class], dependencies = [AppComponent::class])
interface RetrofitComponent {
    abstract fun inject(baseRepo: BaseRepo)

    fun getApiCalls(): APICall
}