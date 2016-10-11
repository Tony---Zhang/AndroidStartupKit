package com.thoughtworks.startup.data.remote;

import com.thoughtworks.startup.data.model.SignInResponse;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface SignInService {
    
    String ENDPOINT = "http://10.29.2.74:3000"; // 10.29.2.74

    int SUCCESS_CODE = 0;

    @FormUrlEncoded
    @POST("/login")
    Observable<SignInResponse> signIn(@Field("email") String email, @Field("password") String password);

    class Creator {

        public static SignInService newSignInService() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(SignInService.class);
        }
    }
}
