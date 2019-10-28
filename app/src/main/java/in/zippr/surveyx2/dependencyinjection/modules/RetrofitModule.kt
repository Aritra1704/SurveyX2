package `in`.zippr.surveyx2.dependencyinjection.modules

import `in`.zippr.surveyx2.dependencyinjection.scopes.ViewModelScope
import `in`.zippr.surveyx2.webservices.APICall
import `in`.zippr.surveyx2.webservices.RetrofitService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class RetrofitModule {

    /**
     * Provides the Post service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Post service implementation.
     */
    @Provides
    @ViewModelScope
    internal fun providePostApi(retrofit: Retrofit): APICall = retrofit.create(APICall::class.java)


    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @ViewModelScope
    internal fun provideRetrofitInterface(): Retrofit {
        return RetrofitService().retrofit
    }
}