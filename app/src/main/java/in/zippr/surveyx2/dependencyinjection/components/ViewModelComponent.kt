package `in`.zippr.surveyx2.dependencyinjection.components

import `in`.zippr.surveyx2.dependencyinjection.modules.ViewModelModule
import `in`.zippr.surveyx2.dependencyinjection.scopes.ViewModelScope
import `in`.zippr.surveyx2.repositories.BaseRepo
import `in`.zippr.surveyx2.viewmodel.BaseVM
import dagger.Component
import dagger.Subcomponent

@ViewModelScope
@Subcomponent(
    modules = arrayOf(ViewModelModule::class)
//    dependencies = [AppComponent::class]
)
interface ViewModelComponent {
    abstract fun inject(baseVM: BaseVM)

    fun getRepo(): BaseRepo
}