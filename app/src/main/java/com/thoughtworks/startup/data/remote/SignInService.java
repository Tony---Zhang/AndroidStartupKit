package com.thoughtworks.startup.data.remote;

import com.thoughtworks.startup.data.ServerConfig;
import com.thoughtworks.startup.data.model.SignInResponse;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import rx.Observable;

public interface SignInService {

    int SUCCESS_CODE = 0;

    @FormUrlEncoded
    @POST(ServerConfig.SIGNIN)
    Observable<SignInResponse> signIn(@Field("email") String email, @Field("password") String password);

    class Creator {

        public static SignInService newSignInService() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ServerConfig.SERVER)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(SignInService.class);
        }
    }
}
