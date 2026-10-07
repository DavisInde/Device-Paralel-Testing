package org.example.test.service

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import org.example.test.base.Config
import org.example.test.base.ConfigConsumer
import org.example.test.models.Device
import org.springframework.context.annotation.Configuration
import java.io.IOException
import java.net.MalformedURLException
import java.net.URI
import java.net.URL
import java.time.Duration

/**
 * 
 */
@Configuration
class DeviceFarmService(config: Config) : ConfigConsumer(config) {
    val availableDevices: Array<Device?>
        get() {
            val devicesUrl: URL?
            try {
                devicesUrl = URI.create(config?.baseUrl + "device-farm/api/device").toURL()
            } catch (e: MalformedURLException) {
                return arrayOfNulls(0)
            }

            val request = Request.Builder()
                .get()
                .url(devicesUrl)
                .build()

            val client = OkHttpClient.Builder()
                .callTimeout(Duration.ofSeconds(15L))
                .build()

            try {
                client.newCall(request).execute().use { res ->
                    val gson = GsonBuilder().create()
                    val body = res.body?.string() ?: ""
                    val converted = gson.fromJson<Array<Device?>?>(body, Array<Device>::class.java)
                    return converted ?: arrayOfNulls(0)
                }
            } catch (e: IOException) {
                return arrayOfNulls(0)
            }
        }
}
