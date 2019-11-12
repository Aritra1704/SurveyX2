package `in`.zippr.surveyx2.dependencyinjection.modules

import `in`.zippr.surveyx2.dependencyinjection.scopes.ViewModelKey
import `in`.zippr.surveyx2.dependencyinjection.scopes.ViewModelScope
import `in`.zippr.surveyx2.repositories.BaseRepo
import `in`.zippr.surveyx2.viewmodel.LoginVM
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ViewModelModule {
//    @Binds
//    @IntoMap
//    @ViewModelKey(LoginVM::class)
//    abstract fun  bindLoginVM(loginVM: LoginVM): ViewModel

    @Provides
    @ViewModelScope
    fun provideRepo(): BaseRepo {
        return BaseRepo()
    }
}