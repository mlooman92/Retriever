package com.matthewlooman.retriever.rest.network;

import com.matthewlooman.retriever.rest.resource.ServerRegistration;
import com.matthewlooman.retriever.rest.resource.UserConnectionValidation;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;

public interface RegistrationService {

  @GET("/pls/apex/sc/log/checkconnection")
  Call<UserConnectionValidation> validateConnection();

  @PUT("/pls/apex/sc/log/register")
  Call<ServerRegistration> registerDevice(@Header("device_name") String deviceName
          , @Header("device_reregister_flag") String registerOnDuplicateName
  );

  @DELETE("/pls/apex/sc/log/register")
  Call<ServerRegistration> deRegisterDeviceById(@Header("device_identifier") String deviceIdentifier);

  @DELETE("/pls/apex/sc/log/register")
  Call<ServerRegistration> deRegisterDeviceByName(@Header("device_name") String deviceName);

}
