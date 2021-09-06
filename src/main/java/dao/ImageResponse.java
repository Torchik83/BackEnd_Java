package dao;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ImageResponse {

    @JsonProperty("data")
    private Data data;
    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("status")
    private Integer status;

    public ImageResponse() {
    }

    public Data getData() {
        return this.data;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public Integer getStatus() {
        return this.status;
    }

    @JsonProperty("data")
    public void setData(Data data) {
        this.data = data;
    }

    @JsonProperty("success")
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @JsonProperty("status")
    public void setStatus(Integer status) {
        this.status = status;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ImageResponse)) return false;
        final ImageResponse other = (ImageResponse) o;
        if (!other.canEqual(this)) return false;
        final Object this$data = this.getData();
        final Object other$data = other.getData();
        if (!Objects.equals(this$data, other$data)) return false;
        final Object this$success = this.getSuccess();
        final Object other$success = other.getSuccess();
        if (!Objects.equals(this$success, other$success)) return false;
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        return Objects.equals(this$status, other$status);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ImageResponse;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $data = this.getData();
        result = result * PRIME + ($data == null ? 43 : $data.hashCode());
        final Object $success = this.getSuccess();
        result = result * PRIME + ($success == null ? 43 : $success.hashCode());
        final Object $status = this.getStatus();
        result = result * PRIME + ($status == null ? 43 : $status.hashCode());
        return result;
    }

    public String toString() {
        return "ImageResponse(data=" + this.getData() + ", success=" + this.getSuccess() + ", status=" + this.getStatus() + ")";
    }
    @lombok.Data
    public static class Data {

        @JsonProperty("id")
        private String id;
        @JsonProperty("title")
        private Object title;
        @JsonProperty("description")
        private Object description;
        @JsonProperty("datetime")
        private Integer datetime;
        @JsonProperty("type")
        private String type;
        @JsonProperty("animated")
        private Boolean animated;
        @JsonProperty("width")
        private Integer width;
        @JsonProperty("height")
        private Integer height;
        @JsonProperty("size")
        private Integer size;
        @JsonProperty("views")
        private Integer views;
        @JsonProperty("bandwidth")
        private Integer bandwidth;
        @JsonProperty("vote")
        private Object vote;
        @JsonProperty("favorite")
        private Boolean favorite;
        @JsonProperty("nsfw")
        private Object nsfw;
        @JsonProperty("section")
        private Object section;
        @JsonProperty("account_url")
        private Object accountUrl;
        @JsonProperty("account_id")
        private Integer accountId;
        @JsonProperty("is_ad")
        private Boolean isAd;
        @JsonProperty("in_most_viral")
        private Boolean inMostViral;
        @JsonProperty("has_sound")
        private Boolean hasSound;
        @JsonProperty("tags")
        private List<Object> tags = new ArrayList<>();
        @JsonProperty("ad_type")
        private Integer adType;
        @JsonProperty("ad_url")
        private String adUrl;
        @JsonProperty("edited")
        private String edited;
        @JsonProperty("in_gallery")
        private Boolean inGallery;
        @JsonProperty("deletehash")
        private String deletehash;
        @JsonProperty("name")
        private String name;
        @JsonProperty("link")
        private String link;

    }
}