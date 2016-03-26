package com.thoughtworks.startup.data.remote;

import com.thoughtworks.startup.data.model.SignInResponse;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
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
