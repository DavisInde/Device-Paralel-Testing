package org.example.test.service;

import org.example.test.base.Config;
import org.example.test.base.ConfigConsumer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.test.models.Device;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;

/**
 *
 * */
@Configuration
public class DeviceFarmService extends ConfigConsumer {

    public DeviceFarmService(Config config) {super(config);}

    public Device[] getAvailableDevices() {
        URL devicesUrl;
        try {
            devicesUrl = URI.create(config.baseUrl + "device-farm/api/device").toURL();
        } catch (MalformedURLException e) {
            return new Device[0];
        }

        Request request = new Request
                .Builder()
                .get()
                .url(devicesUrl)
                .build();

        OkHttpClient client = new OkHttpClient
                .Builder()
                .callTimeout(Duration.ofSeconds(15L))
                .build();

        try (Response res = client.newCall(request).execute()){

            Gson gson = new GsonBuilder().create() ;
            assert res.body() != null;

            return gson.fromJson(res.body().string(), Device[].class);
        } catch (IOException e) {
            System.out.println("YAHHH " + e) ;
            return new Device[0];
        }
    }
}
