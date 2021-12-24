package com.maximmikhailov.alfabanktesttask.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class GiphyResponseData {
    String type;
    String id;
    String url;
    String source;
    String content_url;

}
