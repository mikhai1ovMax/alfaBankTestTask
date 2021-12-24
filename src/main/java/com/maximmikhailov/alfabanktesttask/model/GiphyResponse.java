package com.maximmikhailov.alfabanktesttask.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class GiphyResponse {
    private Data data;

    @lombok.Data
    private class Data {
        private Images images;

        @lombok.Data
        private class Images{
           private downsized_large downsized_large;

           @lombok.Data
            private class downsized_large{
                private String url;
            }
        }
    }

    public String getUrl(){
        return data.getImages().getDownsized_large().getUrl();
    }
}
