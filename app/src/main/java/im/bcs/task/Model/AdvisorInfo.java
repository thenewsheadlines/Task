package im.bcs.task.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class AdvisorInfo {

    @SerializedName("result")
    @Expose
    private Integer result;
    @SerializedName("promotion")
    @Expose
    private String promotion;
    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("bio_data")
    @Expose
    private String bioData;
    @SerializedName("display_picture")
    @Expose
    private String displayPicture;
    @SerializedName("profile_picture")
    @Expose
    private String profilePicture;
    @SerializedName("intro_video")
    @Expose
    private String introVideo;
    @SerializedName("video_thumb")
    @Expose
    private String videoThumb;
    @SerializedName("live_status")
    @Expose
    private String liveStatus;
    @SerializedName("live_rate")
    @Expose
    private String liveRate;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("deactivated")
    @Expose
    private String deactivated;
    @SerializedName("is_psychic")
    @Expose
    private String isPsychic;
    @SerializedName("short_description")
    @Expose
    private String shortDescription;
    @SerializedName("instructions")
    @Expose
    private String instructions;
    @SerializedName("display_order")
    @Expose
    private String displayOrder;
    @SerializedName("reviews")
    @Expose
    private List<Object> reviews = null;
    @SerializedName("display_mri")
    @Expose
    private String displayMri;
    @SerializedName("is_featured")
    @Expose
    private String isFeatured;
    @SerializedName("is_highly_rated")
    @Expose
    private String isHighlyRated;
    @SerializedName("featured_text")
    @Expose
    private String featuredText;
    @SerializedName("ratings")
    @Expose
    private String ratings;
    @SerializedName("review_count")
    @Expose
    private String reviewCount;
    private String subscribeType;



    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBioData() {
        return bioData;
    }

    public void setBioData(String bioData) {
        this.bioData = bioData;
    }

    public String getDisplayPicture() {
        return displayPicture;
    }

    public void setDisplayPicture(String displayPicture) {
        this.displayPicture = displayPicture;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getIntroVideo() {
        return introVideo;
    }

    public void setIntroVideo(String introVideo) {
        this.introVideo = introVideo;
    }

    public String getVideoThumb() {
        return videoThumb;
    }

    public void setVideoThumb(String videoThumb) {
        this.videoThumb = videoThumb;
    }

    public String getLiveStatus() {
        return liveStatus;
    }

    public void setLiveStatus(String liveStatus) {
        this.liveStatus = liveStatus;
    }

    public String getLiveRate() {
        return liveRate;
    }

    public void setLiveRate(String liveRate) {
        this.liveRate = liveRate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeactivated() {
        return deactivated;
    }

    public void setDeactivated(String deactivated) {
        this.deactivated = deactivated;
    }

    public String getIsPsychic() {
        return isPsychic;
    }

    public void setIsPsychic(String isPsychic) {
        this.isPsychic = isPsychic;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(String displayOrder) {
        this.displayOrder = displayOrder;
    }

    public List<Object> getReviews() {
        return reviews;
    }

    public void setReviews(List<Object> reviews) {
        this.reviews = reviews;
    }

    public String getDisplayMri() {
        return displayMri;
    }

    public void setDisplayMri(String displayMri) {
        this.displayMri = displayMri;
    }

    public String getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(String isFeatured) {
        this.isFeatured = isFeatured;
    }

    public String getIsHighlyRated() {
        return isHighlyRated;
    }

    public void setIsHighlyRated(String isHighlyRated) {
        this.isHighlyRated = isHighlyRated;
    }

    public String getFeaturedText() {
        return featuredText;
    }

    public void setFeaturedText(String featuredText) {
        this.featuredText = featuredText;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public String getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(String reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getSubscribeType() {
        return subscribeType;
    }

    public void setSubscribeType(String subscribeType) {
        this.subscribeType = subscribeType;
    }

}